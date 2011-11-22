package libiada.IntervalAnalysis.Buildings;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.operator.learner.tree.Edge;
import com.rapidminer.operator.learner.tree.GreaterSplitCondition;
import com.rapidminer.operator.learner.tree.Tree;
import com.rapidminer.tools.Ontology;
import libiada.IntervalAnalysis.Buildings.Counter.BuildingsCount;
import libiada.IntervalAnalysis.Chain;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/23/11
 * Time: 3:47 AM
 */
public class BuildingsTree extends Tree {
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
        if (this.value != 0)
            this.setLeaf(Integer.toString(this.value));
        else
            this.setLeaf("");
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
        //root = new Node(this.getTrainingSet());
        //root.initialize(1, 1);
        //contents.subElementCount(1);
        //if (0 == contents.getChainLength()) {
        //    return;
        //}
        //root.addClildNodes(contents);
    }

    public ArrayList<Chain> getBuildingsAsChains() throws Exception {
        //ArrayList<Chain> buildings = new ArrayList<Chain>();
        //ArrayList<String> strBuildings = root.getBuildings();
        //for (String building : strBuildings) {
        //    buildings.add(new Chain(building));
        //}
        return null;
    }

    public ArrayList<String> getBuildingsAsStrings() {
        return this.getBuildings(this);
    }

    public ArrayList<String> getBuildings(Tree buildingsTree) {
        ArrayList<String> result = new ArrayList<String>();
        if (buildingsTree.isLeaf())
            result.add(getLabel());
        else {
            Iterator<Edge> iterator = buildingsTree.childIterator();
            while (iterator.hasNext()) {
                Tree child = iterator.next().getChild();
                ArrayList<String> childBuildings = ((BuildingsTree)child).getBuildings(child);
                for (int i = 0; i < childBuildings.size(); i++) {
                    result.add(getLabel() + childBuildings.get(i));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
