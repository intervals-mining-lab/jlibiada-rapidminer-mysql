package libiada.IntervalAnalysis.Buildings;

import libiada.IntervalAnalysis.Chain;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/23/11
 * Time: 3:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tree {
    Node root = null;

    public void rebuildTreeForBuildings(int len, int alphPower) {
        root = new Node(1, 1);
        root.addClildNodes(len, alphPower);
    }

    public ArrayList<Chain> getBuildingsAsChains() throws Exception {
        ArrayList<Chain> buildings = new ArrayList<Chain>();
        ArrayList<String> strBuildings = root.getBuildings();
        for (String building : strBuildings) {
            buildings.add(new Chain(building));
        }
        return buildings;
    }
}
