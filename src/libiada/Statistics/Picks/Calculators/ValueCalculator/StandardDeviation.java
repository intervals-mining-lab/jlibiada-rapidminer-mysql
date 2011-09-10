package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.08.11
 * Time: 1:15
 */
public class StandardDeviation implements IValueCalculator {
    @Override
    public double calculate(Picks picks) throws Exception {
        return Math.sqrt(CalculatorFactory.getVariance().calculate(picks));
    }
}
