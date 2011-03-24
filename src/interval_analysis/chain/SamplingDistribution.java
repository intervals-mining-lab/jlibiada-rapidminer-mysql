package interval_analysis.chain;

import com.rapidminer.example.*;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.*;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeInt;
import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: $Alex Semenov
 * Date: 2/25/11
 * Time: 9:23 PM
 */
public class SamplingDistribution extends Operator {
    private OutputPort outPort = getOutputPorts().createPort("sample");
    private InputPort inPort = getInputPorts().createPort("distribution");

    public static final String PARAMETER_INTERVALS_COUNT = "intervals count";

    public SamplingDistribution(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet sample = inPort.getData();
        Attributes attributes = sample.getAttributes();

        Iterator<Attribute> attrIter = attributes.allAttributes();
        double outValues[][] = new double[getParameterAsInt(PARAMETER_INTERVALS_COUNT)][attributes.size()];
        int attrNum = 0;
        while (attrIter.hasNext()) {
            Attribute attribute = attrIter.next();
            Picks picks = new Picks();
            for (Example example : sample) {
                picks.add(example.getValue(attribute));
            }
            Picks sampling = picks.calculatePicks(CalculatorFactory.getSampling(getParameterAsInt(PARAMETER_INTERVALS_COUNT)));
            sampling.resetIterator();
            while (sampling.hasNext()) {
                outValues[sampling.getIndex()][attrNum] = sampling.next();
            }
            attrNum++;
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(outValues);

        attrIter = attributes.allAttributes();
        int curIndex = 1;
        while (attrIter.hasNext()){
            outSet.getAttributes().get("att" + curIndex).setName(attrIter.next().getName());
            curIndex++;
        }

        outPort.deliver(outSet);
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_INTERVALS_COUNT, "Chain length", 2, 1000));
        return types;
    }
}
