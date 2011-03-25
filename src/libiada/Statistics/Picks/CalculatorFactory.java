package libiada.Statistics.Picks;

import libiada.Statistics.Picks.Calculators.PicksCalculator.IPicksCalculator;
import libiada.Statistics.Picks.Calculators.PicksCalculator.SamplingCalculator;
import libiada.Statistics.Picks.Calculators.ValueCalculator.MaxCalculator;
import libiada.Statistics.Picks.Calculators.ValueCalculator.MinCalculator;

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

    public static MaxCalculator getMax() {
        return new MaxCalculator();
    }

    public static MinCalculator getMin() {
        return new MinCalculator();
    }
}
