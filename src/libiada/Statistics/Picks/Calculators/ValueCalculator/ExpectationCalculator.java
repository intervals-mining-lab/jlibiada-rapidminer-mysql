package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.08.11
 * Time: 0:58
 */
public class ExpectationCalculator implements IValueCalculator {
    @Override
    public double calculate(Picks picks) {
        double mValue = 0;
        picks.resetIterator();
        while (picks.hasNext()) {
            mValue += picks.next();
        }
        mValue /= picks.size();
        return mValue;
    }
}
