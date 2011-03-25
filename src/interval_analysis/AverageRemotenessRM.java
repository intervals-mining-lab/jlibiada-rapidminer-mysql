package interval_analysis;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
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
public class AverageRemotenessRM extends Operator {

    private OutputPort outValue = getOutputPorts().createPort("g");
    private InputPort inChain = getInputPorts().createPort("chain");

    public AverageRemotenessRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        RMChainSet chains = inChain.getData(RMChainSet.class);

        double[][] values = new double[chains.getCount()][3];
        for (int i = 0; i < chains.getCount(); i++) {
            try {
                values[i][0] = (double)i;
                values[i][1] = (double)Integer.parseInt(chains.get(i).toString());
                values[i][2] = chains.get(i).getCharacteristic(LinkUp.Start, CharacteristicsFactory.getAverageRemoteness());
            } catch (Exception e) {
                Logger.getLogger(AverageRemotenessRM.class.getName()).log(Level.SEVERE, "It is not impossible to recive average remoteness", e);
            }
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);

        outSet.getAttributes().get("att1").setName("Number");
        outSet.getAttributes().get("att2").setName("Chain");
        outSet.getAttributes().get("att3").setName("g");

        outValue.deliver(outSet);
    }
}
