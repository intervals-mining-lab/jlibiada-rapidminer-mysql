package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.IFastCalculator;
import libiada.IntervalAnalysis.LinkUp;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 19:51
 */
public class FastGamaut implements IFastCalculator {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double value = 0;
        for (int i = 0; i < chain.alphabetPower(); i++) {
            value += getValue(chain.getFastUniformChain(i), linkUp);
        }
        return value;
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        double result = 0;
        for (Map.Entry<Integer, Integer> entry : chain.getCommonIntervals().entrySet()) {
            result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
        }
        switch (linkUp) {
            case Start:
                for (Map.Entry<Integer, Integer> entry : chain.getStartIntervals().entrySet()) {
                    result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
                }
                break;
            case End:
                for (Map.Entry<Integer, Integer> entry : chain.getEndIntervals().entrySet()) {
                    result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
                }
                break;
            case Both:
                for (Map.Entry<Integer, Integer> entry : chain.getStartIntervals().entrySet()) {
                    result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
                }
                for (Map.Entry<Integer, Integer> entry : chain.getEndIntervals().entrySet()) {
                    result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
                }
                break;
            case Circle:
                for (Map.Entry<Integer, Integer> entry : chain.getCircleIntervals().entrySet()) {
                    result += entry.getValue() * Math.log10(entry.getKey()) / Math.log10(2);
                }
                break;
        }
        return result;
    }

    @Override
    public String getName() {
        return "G";
    }
}
