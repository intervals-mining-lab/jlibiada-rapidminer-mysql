package test.Segmentation;

import junit.framework.TestCase;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.Segmentation.Dividers.ChainDivider;
import libiada.Statistics.Generators.MockGenerator;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 16:15
 */
public class testChainDivider extends TestCase {
    @Test
    public void testDivideChain() throws Exception {
        FastChain chain = new FastChain("123456789012345678901234567890");
        ChainDivider divider = new ChainDivider(5, new MockGenerator(1, chain.length()-1));
        ArrayList<FastChain> result = divider.divide(chain);
        assertEquals(result.get(0).toString(), "1");
        assertEquals(result.get(1).toString(), "2345");
        assertEquals(result.get(2).toString(), "67890");
        assertEquals(result.get(3).toString(), "1");
        assertEquals(result.get(4).toString(), "234");
        assertEquals(result.get(5).toString(), "567");
        assertEquals(result.get(6).toString(), "8901");
        assertEquals(result.get(7).toString(), "23456");
        assertEquals(result.get(8).toString(), "7");
        assertEquals(result.get(9).toString(), "890");
    }
}
