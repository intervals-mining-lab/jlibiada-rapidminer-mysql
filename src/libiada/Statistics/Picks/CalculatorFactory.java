package libiada.Statistics.Picks;

import libiada.Statistics.Picks.Calculators.PatternRecognition.BetweenPatternDistance;
import libiada.Statistics.Picks.Calculators.PatternRecognition.ImageCompactDistance;
import libiada.Statistics.Picks.Calculators.PatternRecognition.InterPatternDistance;
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

    public static IValueCalculator getCorelation(Picks picks1, Picks picks2) throws Exception {
        return new Corelation(picks1, picks2);
    }

    public static IValueCalculator getCovariation(Picks picks1, Picks picks2) throws Exception {
        return new Covariation(picks1, picks2);
    }

    public static IValueCalculator getImageCompactDistance(Picks picks1, Picks picks2) {
        return new ImageCompactDistance(picks1, picks2);
    }

    public static IValueCalculator getInterPatternDistance() {
        return new InterPatternDistance();
    }

    public static IValueCalculator getBetweenPatternsDistance(Picks picks1, Picks picks2) {
        return new BetweenPatternDistance(picks1, picks2);
    }
}
