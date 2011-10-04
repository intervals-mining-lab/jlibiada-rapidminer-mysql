package libiada.Segmentation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 05.10.11
 * Time: 0:38
 */
public class SegmentationModel implements Iterable<Boolean>, Cloneable {
    private ArrayList<Boolean> segmentationModel = new ArrayList<Boolean>();

    public SegmentationModel(String s) {
        for (int i = 0; i < s.length(); i++) {
            segmentationModel.add(s.charAt(i) == '1');
        }
    }

    public SegmentationModel(ArrayList<Boolean> segmentationModel) {
        this.segmentationModel = segmentationModel;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return segmentationModel.iterator();
    }

    public void add(boolean s) {
        segmentationModel.add(s);
    }

    @Override
    public SegmentationModel clone() {
        ArrayList<Boolean> newModel = new ArrayList<Boolean>();
        for (Boolean cur : segmentationModel) {
            newModel.add(cur);
        }
        return new SegmentationModel(newModel);
    }

    @Override
    public String toString() {
        String result = "";
        for (Boolean cur : segmentationModel) {
            result += (cur) ? "1" : "0";
        }
        return result;
    }
}
