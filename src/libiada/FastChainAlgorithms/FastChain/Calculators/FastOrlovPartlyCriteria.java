package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11.10.11
 * Time: 2:31
 */
public class FastOrlovPartlyCriteria extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double f1 = getF1(chain);
        if (f1 == 1)
            return Double.MAX_VALUE;
        double K = 1 / Math.log(f1);
        double B = K * f1 - 1;
        return K * chain.length() - B - chain.alphabetPower();
    }

    private double getF1(FastChain chain) throws Exception {
        double maxValue = 0;
        for (int i = 0; i < chain.alphabetPower(); i++) {
            FastUniformChain uniformChain = chain.getFastUniformChain(i);
            double value = FastCalculatorFactory.getEventCount().getValue(uniformChain, LinkUp.Start);
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    @Override
    public String getName() {
        return "OrlovPart" + super.getName();
    }

    @Override
    public String getType() {
        return "double";  //TODO: "Заполнить метод"
    }

    @Override
    public String getGroup() {
        return "Propability";
    }
}
