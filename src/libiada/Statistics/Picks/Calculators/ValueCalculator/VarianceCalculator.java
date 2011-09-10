package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.08.11
 * Time: 1:02
 */
public class VarianceCalculator implements IValueCalculator {
    @Override
    public double calculate(Picks picks) throws Exception {
        double dValue = 0;
        double mValue = CalculatorFactory.getExpectation().calculate(picks);
        picks.resetIterator();
        while (picks.hasNext()) {
            dValue += Math.pow(picks.next() - mValue, 2);
        }
        return dValue / picks.size();
    }
}
