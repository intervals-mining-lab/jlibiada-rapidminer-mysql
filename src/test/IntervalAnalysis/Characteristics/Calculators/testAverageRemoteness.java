package test.IntervalAnalysis.Characteristics.Calculators;

import junit.framework.TestCase;
import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.Calculators.AverageRemoteness;
import libiada.IntervalAnalysis.Characteristics.Characteristic;
import libiada.IntervalAnalysis.LinkUp;
import org.junit.Before;
import org.junit.Test;
import test.IntervalAnalysis.Characteristics.Calculators.ObjectMother;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 21.01.2011
 * Time: 21:00:35
 * To change this template use File | Settings | File Templates.
 */
public class testAverageRemoteness extends TestCase {
    private Chain TestChain;

    @Before
    public void setUp() throws Exception {
        ObjectMother Mother =  new ObjectMother();
        TestChain = Mother.TestChain();
    }

    @Test
    public void testCalculation() throws Exception {
        Characteristic ARemoteness = new Characteristic(new AverageRemoteness());

        double Interval1 = 4;
        double Interval2 = 1;
        double Interval3 = 3;
        double Interval4 = 3;

        double pIntervalsCount = 3;

        double deltaG = Math.pow(Interval1*Interval2*Interval3, 1/pIntervalsCount);
        double pAverageRemoteness = Math.log10(deltaG) / Math.log10(2);
        assertEquals(pAverageRemoteness, ARemoteness.value(TestChain, LinkUp.Start));

        deltaG = Math.pow(Interval2*Interval3*Interval4, 1/pIntervalsCount);
        pAverageRemoteness = Math.log10(deltaG) / Math.log10(2);
        assertEquals(pAverageRemoteness, ARemoteness.value(TestChain, LinkUp.End));

        pIntervalsCount = 4;

        deltaG = Math.pow(Interval1*Interval2*Interval3*Interval4, 1/pIntervalsCount);
        pAverageRemoteness = Math.log10(deltaG) / Math.log10(2);
        assertEquals(pAverageRemoteness, ARemoteness.value(TestChain, LinkUp.Both));
    }
}
