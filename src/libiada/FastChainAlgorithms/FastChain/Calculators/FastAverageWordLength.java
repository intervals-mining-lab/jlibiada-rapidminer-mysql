package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 10.10.11
 * Time: 2:03
 */
public class FastAverageWordLength extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double result = 0;
        for (int i = 0; i < chain.length(); i++) {
            result += chain.get(i).length();
        }
        return result / chain.length();
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        double result = 0;
        for (int i = 0; i < chain.length(); i++) {
            if (chain.get(i) == "-")
                continue;
            result += chain.get(i).length();
        }
        return result / chain.length();
    }

    @Override
    public String getName() {
        return "L_avegare" + super.getName();
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Common";
    }
}
