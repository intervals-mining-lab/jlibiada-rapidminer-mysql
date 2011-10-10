package test.Segmentation;

import junit.framework.TestCase;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.Segmentation.Cutters.ChainCutter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 15:38
 */
public class testCutter extends TestCase {
    private FastChain chain;
    private ChainCutter cutter;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        chain = new FastChain("123456");
        cutter = new ChainCutter();
    }

    @Test
    public void testCut() throws Exception {
        ArrayList<FastChain> chains =  cutter.cut(chain, 3);
        assertTrue(chains.get(0).equals(new FastChain("123")));
        assertTrue(chains.get(1).equals(new FastChain("456")));
    }

    @Test
    public void testOutOfLengthException() throws Exception {
        try{
            ArrayList<FastChain> chains =  cutter.cut(chain, 7);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        fail();
    }
}
