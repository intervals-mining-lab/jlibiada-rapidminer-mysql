package test.IntervalAnalysis.Characteristics.Calculators;

import junit.framework.TestCase;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11.10.11
 * Time: 2:26
 */
public class testOrlovPartlyCreteria extends TestCase {
    @Test
    public void testValue() throws Exception {
        FastChain chain = new FastChain("122223445");
        double value = FastCalculatorFactory.getOrlovPartlyCriteria().getValue(chain, LinkUp.Start);
        assertEquals(value, -0.3933, 0.01);
    }
}
