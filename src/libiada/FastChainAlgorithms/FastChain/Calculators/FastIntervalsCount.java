package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.IFastCalculator;
import libiada.FastChainAlgorithms.FastChain.UtilClasses.FastIntervalsChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 19:06
 */
public class FastIntervalsCount implements IFastCalculator {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        return getCommonValue(chain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return getCommonValue(chain, linkUp);
    }

    @Override
    public String getName() {
        return "n";
    }

    private double getCommonValue(FastIntervalsChain chain, LinkUp linkUp) throws Exception {
        int n = chain.getEventCount();
        if (linkUp == LinkUp.Both) {
            n++;
        }
        return n;
    }
}
