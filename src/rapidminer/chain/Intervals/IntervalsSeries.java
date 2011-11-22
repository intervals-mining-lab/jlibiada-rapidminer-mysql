package rapidminer.chain.Intervals;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import libiada.FastChainAlgorithms.FastChain.UtilClasses.IntervalsAlgebra;
import libiada.IntervalAnalysis.LinkUp;
import rapidminer.chain.Intervals.ChainModelRM;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 21.10.11
 * Time: 0:34
 */
public class IntervalsSeries extends Operator{
    private InputPort inChainModel = getInputPorts().createPort("model", ChainModelRM.class);
    private OutputPort outIntervalSeries = getOutputPorts().createPort("intervals");

    public IntervalsSeries(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ChainModelRM chain = inChainModel.getData();
        HashMap<Integer, Integer> intervals = null;
        try {
            IntervalsAlgebra algebra = new IntervalsAlgebra();
            intervals = algebra.add(chain.getStartIntervals(), chain.getCommonIntervals());
        } catch (Exception e) {
            e.printStackTrace();  //TODO: "Write method"
        }
        try {
            IntervalsAlgebra ia = new IntervalsAlgebra();
            intervals = ia.add(chain.getIntervalPosed(LinkUp.Free), chain.getIntervalPosed(LinkUp.Start));
        } catch (Exception e) {
            //TODO: ""
        }
        double intervalsValues[][] = new double[intervals.size()][1];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            intervalsValues[i][0] = entry.getValue();
            i++;
        }
        ExampleSet result = ExampleSetFactory.createExampleSet(intervalsValues);
        outIntervalSeries.deliver(result);
    }
}
