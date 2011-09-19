package libiada.Statistics.Picks.Calculators.PatternRecognition;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Calculators.ValueCalculator.IValueCalculator;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.11
 * Time: 23:21
 */
public class ImageCompactDistance implements IValueCalculator {
    private Picks picks1 = null;
    private Picks picks2 = null;

    public ImageCompactDistance(Picks attribute1, Picks attribute2) {
        this.picks1 = attribute1;
        this.picks2 = attribute2;
    }

    @Override
    public double calculate(Picks picks) throws Exception {
        double Wij = CalculatorFactory.getBetweenPatternsDistance(picks1, picks2).calculate(null);
        double Wi = CalculatorFactory.getInterPatternDistance().calculate(picks1);
        double Wj = CalculatorFactory.getInterPatternDistance().calculate(picks2);
        return Wij / (Wi + Wj);
    }
}
