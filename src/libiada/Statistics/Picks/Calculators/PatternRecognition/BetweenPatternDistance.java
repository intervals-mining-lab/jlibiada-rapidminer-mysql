package libiada.Statistics.Picks.Calculators.PatternRecognition;

import libiada.Statistics.Picks.Calculators.ValueCalculator.IValueCalculator;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.11
 * Time: 23:40
 */
public class BetweenPatternDistance implements IValueCalculator {
    private Picks attribute1 = null;
    private Picks attribute2 = null;

    public BetweenPatternDistance(Picks attribute1, Picks attribute2) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    @Override
    public double calculate(Picks picks) throws Exception {
        double d = 0;
        for (int i = 0; i < attribute1.size(); i++)
            for (int j = 0; j < attribute2.size(); j++) {
                d += Math.abs(attribute1.get(i) - attribute2.get(j));
            }
        return d / (attribute1.size() * attribute2.size());
    }
}
