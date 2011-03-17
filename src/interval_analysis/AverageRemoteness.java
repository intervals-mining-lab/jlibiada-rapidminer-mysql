package interval_analysis;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.tools.Ontology;
import interval_analysis.chain.RMChainSet;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/12/11
 * Time: 2:28 AM
 */
public class AverageRemoteness extends Operator {

    private OutputPort outValue = getOutputPorts().createPort("g");
    private InputPort inChain = getInputPorts().createPort("chain");

    public AverageRemoteness(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        RMChainSet chains = inChain.getData(RMChainSet.class);

        double[][] values = new double[chains.getCount()][1];
        for (int i = 0; i < chains.getCount(); i++) {
            try {
                values[i][0] = chains.get(i).getCharacteristic(LinkUp.Start, CharacteristicsFactory.getDeltaG());
            } catch (Exception e) {
                Logger.getLogger(AverageRemoteness.class.getName()).log(Level.SEVERE, "It is not impossible to recive average remoteness", e);
            }
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);
        Attribute attrChain = AttributeFactory.createAttribute("Chain", Ontology.STRING);
        outSet.getAttributes().get("att1").setName("g");
        outSet.getAttributes().addRegular(attrChain);
        /*
        int i = 0;
        for (Example example: outSet) {
            //example.setValue(attrChain, chains.get(i).toString());
            i++;
        }
        */
        outValue.deliver(outSet);
    }
}
