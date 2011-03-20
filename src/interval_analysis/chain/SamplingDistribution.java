package interval_analysis.chain;

import com.rapidminer.example.*;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.*;

import java.util.Iterator;

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
        Attributes attributes = sample.getAttributes();

        for (Example example : sample) {
            Iterator<Attribute> attrIter = attributes.allAttributes();
            while (attrIter.hasNext()) {
                example.setValue(attrIter.next(), 15.0f);
            }
        }

        outPort.deliver(sample);
    }
}
