package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastPeriodicChainConverter;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.07.11
 * Time: 17:20
 */
public class FastPositionedPropability extends FastCalculatorBase {
    private HashSet<Integer> poses = null;
    private int period = 0;

    public FastPositionedPropability(String event, HashSet<Integer> poses, int period) {
        this.event = event;
        this.poses = poses;
        this.period = period;
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        FastPeriodicChainConverter converter = new FastPeriodicChainConverter();
        FastUniformChain uchain = converter.toPeriodicChain(chain, poses, period).getFastUniformChain(event);
        return FastCalculatorFactory.getPropability().getValue(uchain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        super.getValue(chain, linkUp);
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "P(" + event + getPos(poses) + ")";
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Positioned propability";
    }

    private String getPos(HashSet<Integer> poses) {
        String result = "";
        for (Integer pos : poses) {
            result += ", " + Integer.toString(pos + 1);
        }
        return result;
    }
}
