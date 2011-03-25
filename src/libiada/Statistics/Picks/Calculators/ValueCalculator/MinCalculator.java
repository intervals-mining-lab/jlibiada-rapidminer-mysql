package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.03.11
 * Time: 4:11
 */
public class MinCalculator implements IValueCalculator{
    @Override
    public double calculate(Picks picks) {
        double minValue = Double.MAX_VALUE;
        picks.resetIterator();
        while (picks.hasNext()) {
            double value = picks.next();
            if (minValue > value) {
                minValue = value;
            }
        }
        return minValue;
    }
}
