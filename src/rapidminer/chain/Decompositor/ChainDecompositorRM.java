package rapidminer.chain.Decompositor;


import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeInt;
import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.ChainSet;
import libiada.IntervalAnalysis.ChainWithCharacteristicSet;
import libiada.IntervalAnalysis.MixedChain;
import libiada.Statistics.Picks.Picks;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 24.05.11
 * Time: 20:45
 */
public class ChainDecompositorRM extends Operator {
    public InputPort inChains = getInputPorts().createPort("chain", ExampleSet.class);
    public OutputPort outChain = getOutputPorts().createPort("mixed");
    private static final String PARAMETER_WORD_LENGTH = "Word length: ";
    private static final String PARAMETER_STEP = "Step: ";

    public ChainDecompositorRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet inputChainsSet = inChains.getData();
        Attributes attributes = inputChainsSet.getAttributes();

        ArrayList<String> words = new ArrayList<String>();
        int j = 0;
        for (Example example : inputChainsSet) {
            ChainWithCharacteristicSet chSet = null;
            try {
                Chain chain = new Chain(example.getValueAsString(attributes.get("Chain")));
                chSet = chain.getDecomposition(getParameterAsInt(PARAMETER_WORD_LENGTH), getParameterAsInt(PARAMETER_STEP));
            } catch (Exception e) {
                System.err.print("Error of creating mixed chain");
            }

            for (int i = 0; i < chSet.size(); i++) {
                words.add(chSet.get(i).toString());
                i++;
            }
        }
        String data[][] = new String[words.size()][1];
        for (int i = 0; i < words.size(); i++) {
            data[i][0] = words.get(i);
        }

        ExampleSet outChainsSet = ExampleSetFactory.createExampleSet(data);
        outChainsSet.getAttributes().get("att1").setName("Chain");

        outChain.deliver(outChainsSet);
    }

    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_WORD_LENGTH, "Word length: ", 1, 100000));
        types.add(new ParameterTypeInt(PARAMETER_STEP, "Step: ", 1, 100000));
        return types;
    }
}
