package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.09.11
 * Time: 22:14
 */
public class Corelation implements IValueCalculator {
    private Picks picks1 = null;
    private Picks picks2 = null;

    public Corelation(Picks picks1, Picks picks2) throws Exception {
        if (picks1.size() != picks2.size())
            throw new Exception("Pics size error");
        this.picks1 = picks1;
        this.picks2 = picks2;
    }

    @Override
    public double calculate(Picks picks) throws Exception {
        double result = 0;
        double cov = CalculatorFactory.getCovariation(picks1, picks2).calculate(null);
        double sko1 = CalculatorFactory.getStandardDeviation().calculate(picks1);
        double sko2 = CalculatorFactory.getStandardDeviation().calculate(picks2);
        result = cov / (sko1 * sko2);
        return result;
    }
}
