package libiada.Segmentation;

import libiada.FastChainAlgorithms.FastChain.FastChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 05.10.11
 * Time: 0:37
 */
public class SimpleSegmentator {
    public SimpleSegmentator() {
    }

    public FastChain segment(FastChain chain, SegmentationModel model) throws Exception {
        FastChain result = new FastChain();
        boolean currentSym = true;
        String sym = "";
        for (int index = 0; index < model.length() && index < chain.length(); index++) {
            Boolean cur = model.get(index);
            if (cur == currentSym) {
                sym += chain.get(index);
            }

            if (cur != currentSym) {
                result.add(sym);
                currentSym = !currentSym;
                sym = "";
                sym += chain.get(index);
            }
        }
        result.add(sym);
        return result;
    }
}
