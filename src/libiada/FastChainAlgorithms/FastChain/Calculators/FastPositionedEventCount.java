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
 * Date: 01.08.11
 * Time: 20:15
 */
public class FastPositionedEventCount extends FastCalculatorBase {
    private String event = "";
    private HashSet<Integer> poses = null;
    private int period = 3;

    public FastPositionedEventCount(String event, HashSet<Integer> poses, int period) {
        this.event = event;
        this.poses = poses;
        this.period = period;
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        FastPeriodicChainConverter converter = new FastPeriodicChainConverter();
        FastUniformChain uchain = converter.toPeriodicChain(chain, poses, period).getFastUniformChain(event);
        if (uchain.getAlphabet().size() <= 1)
            return 0;
        return FastCalculatorFactory.getEventCount().getValue(uchain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "n(" + event + getPos(poses) + ")";
    }

    @Override
    public String getType() {
        return "int";
    }

    @Override
    public String getGroup() {
        return "Positioned common";
    }

    private String getPos(HashSet<Integer> poses) {
        String result = "";
        for (Integer pos : poses) {
            result += ", " + Integer.toString(pos + 1);
        }
        return result;
    }
}
