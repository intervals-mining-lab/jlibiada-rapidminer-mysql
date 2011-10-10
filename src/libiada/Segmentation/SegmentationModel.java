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

    public SegmentationModel() {
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

    public void concat(SegmentationModel model) {
        if (segmentationModel.size() !=0 && segmentationModel.get(length() - 1))
            model.inverse();
        for (int i = 0; i < model.length(); i++) {
            segmentationModel.add(model.get(i));
        }
    }

    public void inverse() {
        ArrayList<Boolean> newModel = new ArrayList<Boolean>();
        for (int i = 0; i < segmentationModel.size(); i++) {
            newModel.add(!segmentationModel.get(i));
        }
        segmentationModel = newModel;
    }

    public boolean get(int index) {
        return segmentationModel.get(index);
    }

    public int length() {
        return segmentationModel.size();
    }
}
