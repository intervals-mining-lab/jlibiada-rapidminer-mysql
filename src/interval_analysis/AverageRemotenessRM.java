package interval_analysis;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.features.construction.AttributeGenerator;
import com.rapidminer.operator.nio.model.DataResultSet;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.*;
import interval_analysis.chain.RMChainSet;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;

import java.io.Console;
import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;
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

    public static final String PARAMETER_LINK_UP = "Link up";

    private String[] items = new String[] {"Start", "End", "Both", "Circle"};

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
                values[i][2] = chains.get(i).getCharacteristic(getLinkUp(), CharacteristicsFactory.getAverageRemoteness());
            } catch (Exception e) {
                Logger.getLogger(AverageRemotenessRM.class.getName()).log(Level.SEVERE, "It is not impossible to receive average remoteness", e);
            }
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);

        outSet.getAttributes().get("att1").setName("Number");
        outSet.getAttributes().get("att2").setName("Chain");
        outSet.getAttributes().get("att3").setName("g");

        outValue.deliver(outSet);
    }

    public LinkUp getLinkUp() throws UndefinedParameterError {
        String item = getParameterAsString(PARAMETER_LINK_UP);

        Logger.getLogger(AverageRemotenessRM.class.getName()).log(Level.SEVERE, item + "++++++++++++++++++++++++++++++++++++++++");

        if ("Start" == item) {
            return LinkUp.Start;
        } else if ("End" == item) {
            return LinkUp.End;
        } else if ("Both" == item) {
            return LinkUp.Both;
        } else if ("Circle" == item) {
            return LinkUp.Circle;
        } else {
            return LinkUp.Start;
        }
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeCategory(PARAMETER_LINK_UP, "Link up", items, 1));
        return types;
    }
}
