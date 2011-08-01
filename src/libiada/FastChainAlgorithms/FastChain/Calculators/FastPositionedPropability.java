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
 * Date: 30.07.11
 * Time: 17:20
 */
public class FastPositionedPropability implements IFastCalculator {
    private String event = "";
    private HashSet<Integer> poses = null;
    private int period = 0;

    public FastPositionedPropability(String event, HashSet<Integer> poses, int period) {
        this.event = event;
        this.poses = poses;
        this.period = period;
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double result = 0;
        FastPeriodicChainConverter converter = new FastPeriodicChainConverter();
        FastUniformChain uchain = converter.toPeriodicChain(chain, poses, period).getFastUniformChain(event);
        return FastCalculatorFactory.getPropability().getValue(uchain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "Shepherd";
    }
}
