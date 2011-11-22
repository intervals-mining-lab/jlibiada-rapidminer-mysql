package libiada.Segmentation.Cutters;

import libiada.FastChainAlgorithms.FastChain.FastChain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 15:43
 */
public class ChainCutter {
    public ArrayList<FastChain> cut(FastChain chain, int leftChainLength) throws Exception {
        if (leftChainLength > chain.length())
            throw new IndexOutOfBoundsException("Cut place out of chain length");
        ArrayList<FastChain> result = new ArrayList<FastChain>();
        FastChain left = new FastChain();
        for (int i = 0; i < leftChainLength; i++) {
            left.add(chain.get(i));
        }
        FastChain right = new FastChain();
        for (int i = leftChainLength; i < chain.length(); i++) {
            right.add(chain.get(i));
        }
        result.add(left);
        result.add(right);
        return result;
    }
}
