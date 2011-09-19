package libiada.Statistics.Picks.Calculators.PatternRecognition;

import libiada.Statistics.Picks.Calculators.ValueCalculator.IValueCalculator;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.11
 * Time: 23:31
 */
public class InterPatternDistance implements IValueCalculator {
    @Override
    public double calculate(Picks picks) throws Exception {
        double d = 0;
        for (int i = 0; i < picks.size(); i++) {
            for (int j = 0; j < picks.size(); j++) {
                double d1 = picks.get(i);
                double d2 = picks.get(j);
                d += Math.abs(d1-d2);
            }
        }
        return d / Math.pow(picks.size(), 2);
    }
}
