package libiada.Statistics.Picks.Calculators.PicksCalculator;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.03.11
 * Time: 3:33
 */
public class SamplingCalculator implements IPicksCalculator {
    private int intervalsCount = 1;

    public SamplingCalculator(int intervalsCount) {
        this.intervalsCount = intervalsCount;
    }

    @Override
    public Picks calculate(Picks picks) throws Exception {
        Picks sampling = new Picks();
        picks.sort();
        double max = picks.calculateCharacteristic(CalculatorFactory.getMax());
        double min = picks.calculateCharacteristic(CalculatorFactory.getMin());
        double delta = (max - min) / intervalsCount;
        int currentValuesCount = 0;
        picks.resetIterator();
        for (int i = 1; i <= intervalsCount; i++) {
            double currentMax = min + i * delta;
            while (picks.hasNext()) {
                if (picks.next() <= currentMax) {
                    currentValuesCount++;
                } else {
                    sampling.add((double)currentValuesCount / picks.size());
                    currentValuesCount = 0;
                    break;
                }
            }
        }
        sampling.add((double)currentValuesCount / picks.size());
        return sampling;
    }
}
