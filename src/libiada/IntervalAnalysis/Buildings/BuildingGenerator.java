package libiada.IntervalAnalysis.Buildings;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 25.03.11
 * Time: 2:41
 */
public class BuildingGenerator {
    public ArrayList<String> getBuildings(int chainLength, int alphabetPower) {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(chainLength, alphabetPower);
        return tree.getBuildingsAsStrings();
    }

    public ArrayList<String> getBuildings(Contents contents) throws Exception {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(contents);
        return tree.getBuildingsAsStrings();
    }
}
