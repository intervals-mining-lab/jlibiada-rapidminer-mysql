package interval_analysis.chain;

import com.rapidminer.example.*;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.*;
import libiada.IntervalAnalysis.MixedChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 22:27
 */
public class MixedConverterRM extends Operator {
    public InputPort inChains = getInputPorts().createPort("chain", ExampleSet.class);
    public OutputPort outChain = getOutputPorts().createPort("mixed");

    public MixedConverterRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet inputChainsSet = inChains.getData();
        Attributes attributes = inputChainsSet.getAttributes();

        String data[][] = new String[inputChainsSet.size()][1];
        int i = 0;
        for (Example example : inputChainsSet) {
            String strChain = "Error";
            try {
                MixedChain chain = new MixedChain(example.getValueAsString(attributes.get("Chain")));
                strChain = chain.toString();
            } catch (Exception e) {
                System.err.print("Error of creating mixed chain");
            }
            data[i][0] = strChain;
            i++;
        }

        ExampleSet outChainsSet = ExampleSetFactory.createExampleSet(data);
        outChainsSet.getAttributes().get("att1").setName("Chain");

        outChain.deliver(outChainsSet);
    }
}
