package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.IFastCalculator;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 22:21
 */
public class FastLength implements IFastCalculator {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        return chain.length();
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return chain.length();
    }

    @Override
    public String getName() {
        return "m";
    }
}
