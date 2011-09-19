package libiada.IntervalAnalysis.Buildings;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.operator.learner.tree.GreaterSplitCondition;
import com.rapidminer.operator.learner.tree.Tree;
import com.rapidminer.tools.Ontology;
import libiada.IntervalAnalysis.Buildings.Counter.BuildingsCount;
import libiada.IntervalAnalysis.Chain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/23/11
 * Time: 3:47 AM
 */
public class BuildingsTree extends Tree {
    Node root = null;
    private int value  = 0;
    private int max  = 0;
    public BuildingsTree() {
        super(null);
    }

    public BuildingsTree(ExampleSet trainingSet) {
        super(trainingSet);
    }

    public BuildingsTree(ExampleSet exampleSet, int value, int max) {
        super(exampleSet);
        this.value = value;
        this.max = max;
    }

    public void rebuildTreeForBuildings(int len, int alphPower) {
        this.setLeaf(Integer.toString(this.value));
        if (len == 1) {
            return;
        }
        BuildingsCount counter = new BuildingsCount();
        for(int i=1; i<=Math.min(this.max+1, alphPower); i++) {
            BuildingsTree child = new BuildingsTree(this.getTrainingSet(), i, Math.max(this.max, i));
            this.addChild(child, new GreaterSplitCondition(AttributeFactory.createAttribute("att_"+len, Ontology.INTEGER), counter.getProbability(len-1,alphPower, Math.max(this.max, i), this.value)));
            child.rebuildTreeForBuildings(len-1, alphPower);
        }
    }

    public void rebuildTreeForBuildings(Contents contents) throws Exception {
        root = new Node(this.getTrainingSet());
        root.initialize(1, 1);
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

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
