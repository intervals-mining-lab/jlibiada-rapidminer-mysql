package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastPeriodicChainConverter;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.FastCalculatorBase;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.08.11
 * Time: 15:15
 */
public class FastPositionedAverageRemoteness extends FastCalculatorBase {
    private String event = "";
    private HashSet<Integer> poses = null;
    private int period = 0;

    public FastPositionedAverageRemoteness(String event, HashSet<Integer> poses, int period) {
        this.event = event;
        this.poses = poses;
        this.period = period;
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        FastPeriodicChainConverter converter = new FastPeriodicChainConverter();
        FastUniformChain uchain = converter.toPeriodicChain(chain, poses, period).getFastUniformChain(event);
        if (uchain.getAlphabet().size() <= 1)
            return Double.MAX_VALUE;
        return FastCalculatorFactory.getAverageRemoteness().getValue(uchain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "g(" + event + getPos(poses) + ")";
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Positioned building characteristic";
    }

    private String getPos(HashSet<Integer> poses) {
        String result = "";
        for (Integer pos : poses) {
            result += ", " + Integer.toString(pos + 1);
        }
        return result;
    }
}
