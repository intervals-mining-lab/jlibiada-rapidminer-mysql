package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.03.11
 * Time: 3:59
 */
public class MaxCalculator implements IValueCalculator {
    @Override
    public double calculate(Picks picks) {
        double maxValue = Double.MIN_VALUE;
        picks.resetIterator();
        while (picks.hasNext()) {
            double value = picks.next();
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}
