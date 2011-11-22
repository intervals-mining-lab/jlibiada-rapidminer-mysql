package libiada.Statistics.Picks.Calculators.ValueCalculator;

import libiada.Statistics.Picks.CalculatorFactory;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.09.11
 * Time: 22:23
 */
public class Covariation implements IValueCalculator {
    private Picks picks1 = null;
    private Picks picks2 = null;

    public Covariation(Picks picks1, Picks picks2) throws Exception {
        if (picks1.size() != picks2.size())
            throw new Exception("Pics size error");
        this.picks1 = picks1;
        this.picks2 = picks2;
    }

    @Override
    public double calculate(Picks picks) throws Exception {
        double result = 0;
        double mx = CalculatorFactory.getExpectation().calculate(picks1);
        double my = CalculatorFactory.getExpectation().calculate(picks2);
        picks1.resetIterator();
        picks2.resetIterator();
        while (picks1.hasNext() && picks2.hasNext()) {
            double x = picks1.next();
            double y = picks2.next();
            result += (x - mx)*(y - my);
        }
        return result / picks1.size();
    }
}
