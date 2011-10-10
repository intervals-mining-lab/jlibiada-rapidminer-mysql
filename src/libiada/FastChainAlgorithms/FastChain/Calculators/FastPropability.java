package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 18:11
 */
public class FastPropability extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double result = 0;
        for (int i = 0; i < chain.alphabetPower(); i ++) {
            result += getValue(chain.getFastUniformChain(i), linkUp);
        }
        return result;
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        if (chain.getAlphabet().size() <= 1)
            return 0;
        super.getValue(chain, linkUp);
        double length = chain.length();
        double count = chain.getEventCount();
        return (double)((double)count / (double)length);
    }

    @Override
    public String getName() {
        return "p" + super.getName();
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Propability";
    }
}
