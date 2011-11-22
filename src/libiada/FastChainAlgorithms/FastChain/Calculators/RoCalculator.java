package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 28.10.11
 * Time: 1:01
 */
public class RoCalculator extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        return 1;
    }

    public double getValue(FastUniformChain chain, LinkUp linkUp) {
        ArrayList<Integer> poses = chain.getPositions();
        double result = 0;
        for (int i = 0; i < poses.size(); i++) {
            result += poses.get(i);
        }
        result /= getProgressionSum(chain.length());
        return result;
    }

    private double getProgressionSum(int length) {
        return (Math.pow(length, 2) + length) / 2;
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
        return "Ro";
    }
}
