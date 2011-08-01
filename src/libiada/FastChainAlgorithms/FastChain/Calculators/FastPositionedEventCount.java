package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastPeriodicChainConverter;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.IFastCalculator;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 01.08.11
 * Time: 20:15
 */
public class FastPositionedEventCount implements IFastCalculator {
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
        return FastCalculatorFactory.getEventCount().getValue(uchain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "Event count";
    }
}
