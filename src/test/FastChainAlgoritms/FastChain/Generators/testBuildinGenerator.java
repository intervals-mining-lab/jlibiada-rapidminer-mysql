package test.FastChainAlgoritms.FastChain.Generators;

import junit.framework.TestCase;
import libiada.IntervalAnalysis.Buildings.BuildingsTree;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 28.10.11
 * Time: 2:27
 */
public class testBuildinGenerator extends TestCase {
    @Test
    public void testGeneraion() {
        BuildingsTree buildingsTree = new BuildingsTree();
        buildingsTree.rebuildTreeForBuildings(4, 4);
        ArrayList<String> chains = null;
        chains = buildingsTree.getBuildingsAsStrings();

        assertEquals(chains.get(0), "111");
        assertEquals(chains.get(1), "112");
        assertEquals(chains.get(2), "121");
        assertEquals(chains.get(3), "122");
        assertEquals(chains.get(4), "123");
    }
}
