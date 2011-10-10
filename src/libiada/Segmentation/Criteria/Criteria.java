package libiada.Segmentation.Criteria;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.Segmentation.SegmentationModel;
import libiada.Segmentation.SimpleSegmentator;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 17:09
 */
public abstract class Criteria {
    private SegmentationModel bestModel = null;

    public SegmentationModel getBest(FastChain chain, ArrayList<SegmentationModel> models) throws Exception {
        for (int i = 0; i < models.size(); i++) {
            SimpleSegmentator segmentator = new SimpleSegmentator();
            FastChain current = segmentator.segment(chain, models.get(i));
            if (isBetter(current, chain))
                bestModel = models.get(i);
        }
        clear();
        return bestModel;
    }

    protected abstract void clear();

    protected abstract boolean isBetter(FastChain segmentedChain, FastChain rootChain) throws Exception;
}
