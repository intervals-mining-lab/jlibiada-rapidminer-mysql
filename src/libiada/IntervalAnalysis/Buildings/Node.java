package libiada.IntervalAnalysis.Buildings;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 2/23/11
 * Time: 3:48 AM
 */
public class Node {
    private ArrayList<Node> childNodes = new ArrayList<Node>();
    private int value = 1;
    private int maxInPathFromRootToChild = 1;

    public Node(int value, int max) {
        this.value = value;
        this.maxInPathFromRootToChild = max;
    }

    public void addClildNodes(int len, int alphPower) {
        if (len <= 1)
            return;
        for (int i = 1; i <= maxInPathFromRootToChild + 1; i++) {
            Node node = createNode(i);
            node.addClildNodes(len - 1, alphPower);
            childNodes.add(node);
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
        return new Node(i, max);
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