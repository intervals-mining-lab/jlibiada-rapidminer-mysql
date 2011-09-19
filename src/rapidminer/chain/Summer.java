package rapidminer.chain;

import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 19.09.11
 * Time: 15:30
 */
public class Summer extends Operator {
    protected OutputPort outValue = getOutputPorts().createPort("values");
    protected InputPort inChain = getInputPorts().createPort("g");

    public Summer(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet examples = inChain.getData();

        double[][] values = new double[examples.size()][1];
        int i = 0;
        for (Example example : examples) {
            double g = example.getValue(examples.getAttributes().get("g"));
            values[i][0] = 1.0;
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);
        outValue.deliver(outSet);
    }
}
