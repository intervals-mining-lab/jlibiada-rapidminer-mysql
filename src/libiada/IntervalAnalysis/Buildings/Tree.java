package libiada.IntervalAnalysis.Buildings;

import libiada.IntervalAnalysis.Chain;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/23/11
 * Time: 3:47 AM
 */
public class Tree {
    Node root = null;

    public void rebuildTreeForBuildings(int len, int alphPower) {
        root = new Node(1, 1);
        root.addClildNodes(len, alphPower);
    }

    public void rebuildTreeForBuildings(Contents contents) throws Exception {
        root = new Node(1, 1);
        contents.subElementCount(1);
        if (0 == contents.getChainLength()) {
            return;
        }
        root.addClildNodes(contents);
    }

    public ArrayList<Chain> getBuildingsAsChains() throws Exception {
        ArrayList<Chain> buildings = new ArrayList<Chain>();
        ArrayList<String> strBuildings = root.getBuildings();
        for (String building : strBuildings) {
            buildings.add(new Chain(building));
        }
        return buildings;
    }

    public ArrayList<String> getBuildingsAsStrings() {
        return root.getBuildings();
    }
}
