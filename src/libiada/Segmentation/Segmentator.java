package libiada.Segmentation;

import libiada.FastChainAlgorithms.FastChain.FastChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 05.10.11
 * Time: 0:37
 */
public class Segmentator {
    private SegmentationModel model = null;

    public Segmentator(SegmentationModel model) {
        this.model = model;
    }

    public Segmentator() {
    }

    public FastChain segment(FastChain chain) throws Exception {
        FastChain result = new FastChain();
        boolean currentSym = true;
        String sym = "";
        int index = 0;
        for (Boolean cur : model) {
            if (cur == currentSym) {
                sym += chain.get(index);
            }

            if (cur != currentSym) {
                result.add(sym);
                currentSym = !currentSym;
                sym = "";
                sym += chain.get(index);
            }
            index++;
        }
        result.add(sym);
        return result;
    }

    public FastChain segment(FastChain chain, SegmentationModel model) throws Exception {
        this.model = model;
        return segment(chain);
    }
}
