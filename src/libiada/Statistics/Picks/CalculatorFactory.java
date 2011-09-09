package libiada.Statistics.Picks;

import libiada.Statistics.Picks.Calculators.PicksCalculator.IPicksCalculator;
import libiada.Statistics.Picks.Calculators.PicksCalculator.SamplingCalculator;
import libiada.Statistics.Picks.Calculators.ValueCalculator.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.03.11
 * Time: 3:32
 */
public class CalculatorFactory {
    public static IPicksCalculator getSampling(int param) {
        return new SamplingCalculator(param);
    }

    public static IValueCalculator getMax() {
        return new MaxCalculator();
    }

    public static IValueCalculator getMin() {
        return new MinCalculator();
    }

    public static IValueCalculator getExpectation() {
        return new ExpectationCalculator();
    }

    public static IValueCalculator getVariance() {
        return new VarianceCalculator();
    }

    public static IValueCalculator getStandardDeviation() {
        return new StandardDeviation();
    }
}
