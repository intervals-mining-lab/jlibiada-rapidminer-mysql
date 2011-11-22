package rapidminer.chain.Intervals;

import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import rapidminer.chain.Intervals.ChainModelRM;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 21.10.11
 * Time: 0:43
 */
public class ChainModelOp extends Operator{
    private InputPort inChainString = getInputPorts().createPort("chain", ExampleSet.class);
    private OutputPort outChainModel = getOutputPorts().createPort("model");

    public ChainModelOp(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet inputChain = inChainString.getData();
        ChainModelRM model = null;
        for (Example example : inputChain) {
            try {
                model = new ChainModelRM(example.getValueAsString(example.getAttributes().get("Chain")));
            } catch (Exception e) {
                e.printStackTrace();  //TODO: "Write code"
            }
        }
        outChainModel.deliver(model);
    }
}
