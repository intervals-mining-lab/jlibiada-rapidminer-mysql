package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 28.10.11
 * Time: 3:11
 */
public class MuCalculator extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        return 1;
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) {
        ArrayList<Integer> poses = chain.getPositions();
        double result = 1;
        for (int i = 0; i < poses.size(); i++) {
            result *= poses.get(i);
        }
        result /= getFactorial(chain.length());
        return result;
    }

    private double getFactorial(int length) {
        double result = 1;
        for (int i = 1; i <= length; i++)
            result *= i;
        return result;
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Positioned";
    }

    @Override
    public String getName() {
        return "Mu";
    }
}
