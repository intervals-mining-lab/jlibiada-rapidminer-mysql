package interval_analysis.chain;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;

/**
 * Created by IntelliJ IDEA.
 * User: $Alex Semenov
 * Date: 2/25/11
 * Time: 9:23 PM
 */
public class SamplingDistribution extends Operator {
    private OutputPort outPort = getOutputPorts().createPort("sample");
    private InputPort inPort = getInputPorts().createPort("distribution");

    public SamplingDistribution(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet sample = inPort.getData();

        outPort.deliver(sample);
    }
}
