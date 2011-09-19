package libiada.IntervalAnalysis.Buildings;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.operator.learner.tree.GreaterSplitCondition;
import com.rapidminer.operator.learner.tree.Tree;
import com.rapidminer.tools.Ontology;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 2/23/11
 * Time: 3:48 AM
 */
public class Node extends Tree {
    private ArrayList<Node> childNodes = new ArrayList<Node>();
    private int value = 1;
    private int maxInPathFromRootToChild = 1;

    public Node() {
        super(null);
    }

    public Node(ExampleSet trainingSet) {
        super(trainingSet);
    }

    public void initialize(int value, int max) {
        this.value = value;
        this.maxInPathFromRootToChild = max;
    }

    public void addClildNodes(int len, int alphPower) {
        if (len <= 1) {
            this.setLeaf(Integer.toString(value));
            return;
        }
        for (int i = 1; i <= Math.min(maxInPathFromRootToChild + 1, alphPower); i++) {
            Node node = createNode(i);
            node.addClildNodes(len - 1, alphPower);
            //childNodes.add(node);
            this.addChild(node, new GreaterSplitCondition(AttributeFactory.createAttribute("att_1", Ontology.REAL), 0.5));
        }
    }

    public void addClildNodes(Contents contents) throws Exception {
        if (contents.getChainLength() < 1)
            return;
        for (int i = 1; i <= Math.min(maxInPathFromRootToChild + 1, contents.getPower()); i++) {
            if (contents.getElementCount(i) < 1)
                continue;
            Contents newNodeContents = contents.clone();
            newNodeContents.subElementCount(i);
            Node node = createNode(i);
            node.addClildNodes(newNodeContents);
            childNodes.add(node);
        }
    }

    private Node createNode(int i) {
        int max = Math.max(i, maxInPathFromRootToChild);
        Node node = new Node(this.getTrainingSet());
        node.initialize(i, max);
        return node;
    }

    public ArrayList<String> getBuildings() {
        ArrayList<String> buildings = new ArrayList<String>();
        if (0 == childNodes.size()) {
            buildings.add(String.valueOf(value));
            return buildings;
        }
        ArrayList<String> buildingsFromChilds = new ArrayList<String>();
        for (Node childNode : childNodes) {
            buildingsFromChilds.addAll(childNode.getBuildings());
        }
        for (String build : buildingsFromChilds) {
            buildings.add(String.valueOf(value) + build);
        }
        return buildings;
    }
}