package test.IntervalAnalysis.Characteristics.Calculators;

import junit.framework.TestCase;
import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.Calculators.AverageRemoteness;
import libiada.IntervalAnalysis.Characteristics.Characteristic;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.01.2011
 * Time: 21:00:35
 * To change this template use File | Settings | File Templates.
 */
public class testAverageRemoteness extends TestCase {
    private Chain TestChain;
    private UniformChain TestUChain;

    @Before
    public void setUp() throws Exception {
        ObjectMother Mother =  new ObjectMother();
        TestChain = Mother.getChain();
        TestUChain = Mother.getUniformChain();
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
        assertEquals(pAverageRemoteness, ARemoteness.value(TestUChain, LinkUp.Start));

        deltaG = Math.pow(Interval2*Interval3*Interval4, 1/pIntervalsCount);
        pAverageRemoteness = Math.log10(deltaG) / Math.log10(2);
        assertEquals(pAverageRemoteness, ARemoteness.value(TestUChain, LinkUp.End));

        pIntervalsCount = 4;

        deltaG = Math.pow(Interval1*Interval2*Interval3*Interval4, 1/pIntervalsCount);
        pAverageRemoteness = Math.log10(deltaG) / Math.log10(2);
        assertEquals(pAverageRemoteness, ARemoteness.value(TestUChain, LinkUp.Both));
    }

    @Test
    public void testCalculationForChain() throws Exception {
        Characteristic ARemoteness = new Characteristic(new AverageRemoteness());

        int Alphabet_power = 3;
        assertEquals(Alphabet_power, TestChain.getAlpahbet().getPower());


        double remoutness11 = Math.log(1) / Math.log(2);
        double remoutness12 = Math.log(1) / Math.log(2);
        double remoutness13 = Math.log(4) / Math.log(2);
        double remoutness14 = Math.log(4) / Math.log(2);
        double remoutness15 = Math.log(1) / Math.log(2);

        double SumAStart = remoutness11 + remoutness12 + remoutness13 + remoutness14;
        double SumAEnd = remoutness12 + remoutness13 + remoutness14 + remoutness15;
        double SumABoth = remoutness11 + remoutness12 + remoutness13 + remoutness14 + remoutness15;

        double remoutness21 = Math.log(3) / Math.log(2);
        double remoutness22 = Math.log(1) / Math.log(2);
        double remoutness23 = Math.log(3) / Math.log(2);
        double remoutness24 = Math.log(4) / Math.log(2);

        double SumBStart = remoutness21 + remoutness22 + remoutness23;
        double SumBEnd = remoutness22 + remoutness23 + remoutness24;
        double SumBBoth = remoutness21 + remoutness22 + remoutness23 + remoutness24;

        double remoutness31 = Math.log(5) / Math.log(2);
        double remoutness32 = Math.log(3) / Math.log(2);
        double remoutness33 = Math.log(1) / Math.log(2);
        double remoutness34 = Math.log(2) / Math.log(2);

        double SubCStart = remoutness31 + remoutness32 + remoutness33;
        double SubCEnd = remoutness32 + remoutness33 + remoutness34;
        double SubCBoth = remoutness31 + remoutness32 + remoutness33 + remoutness34;

        double SumStart = SumAStart + SumBStart + SubCStart;
        double n = 10;

        assertEquals(SumStart/n, ARemoteness.value(TestChain, LinkUp.Start));

        double SumEnd = SumAEnd + SumBEnd + SubCEnd;
        assertEquals(SumEnd/n, ARemoteness.value(TestChain, LinkUp.End));

        double nBoth = 13;
        double SumBoth = SumABoth + SumBBoth + SubCBoth;
        assertEquals((float)(SumBoth/nBoth), (float)ARemoteness.value(TestChain, LinkUp.Both));
    }
}
