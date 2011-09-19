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
        BuildingsTree buildingsTree = new BuildingsTree();
        buildingsTree.rebuildTreeForBuildings(chainLength, alphabetPower);
        return buildingsTree.getBuildingsAsStrings();
    }

    public ArrayList<String> getBuildings(Contents contents) throws Exception {
        BuildingsTree buildingsTree = new BuildingsTree();
        buildingsTree.rebuildTreeForBuildings(contents);
        return buildingsTree.getBuildingsAsStrings();
    }
}
