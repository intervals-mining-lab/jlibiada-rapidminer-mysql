package libiada.IntervalAnalysis.Buildings;

import libiada.IntervalAnalysis.Chain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 25.03.11
 * Time: 2:41
 */
public class BuildingsGenerator {
    public ArrayList<String> GetBuildings(int chainLength, int alphabetPower) {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(chainLength, alphabetPower);
        return tree.getBuildingsAsStrings();
    }

    public ArrayList<String> GetBuildings(Contents contents) throws Exception {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(contents);
        return tree.getBuildingsAsStrings();
    }
}
