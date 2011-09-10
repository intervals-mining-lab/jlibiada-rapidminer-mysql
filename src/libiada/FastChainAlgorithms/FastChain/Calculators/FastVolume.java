package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastIntervalsChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.FastCalculatorBase;
import libiada.IntervalAnalysis.LinkUp;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 0:49
 */
public class FastVolume extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        return getCommonValue(chain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        super.getValue(chain, linkUp);
        return getCommonValue(chain, linkUp);
    }

    @Override
    public String getName() {
        return "V" + super.getName();
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Building characteristic";
    }

    private double getCommonValue(FastIntervalsChain chain, LinkUp linkUp) throws Exception {
        double value = 1;
        for (Map.Entry<Integer, Integer> entry : chain.getCommonIntervals().entrySet()) {
            Integer interval = entry.getKey();
            Integer count = entry.getValue();
            value *= Math.pow(interval, count);
        }
        if ((linkUp == LinkUp.Start) || (linkUp == LinkUp.Both))
            for (Map.Entry<Integer, Integer> entry : chain.getStartIntervals().entrySet()) {
                value *= Math.pow(entry.getKey(), entry.getValue());
            }
        if ((linkUp == LinkUp.End) || (linkUp == LinkUp.Both))
            for (Map.Entry<Integer, Integer> entry : chain.getEndIntervals().entrySet()) {
                value *= Math.pow(entry.getKey(), entry.getValue());
            }
        if (linkUp == LinkUp.Circle)
            for (Map.Entry<Integer, Integer> entry : chain.getCircleIntervals().entrySet()) {
                value *= Math.pow(entry.getKey(), entry.getValue());
            }
        return value;
    }
}
