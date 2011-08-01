package libiada.FastChainAlgorithms.FastChain;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.07.11
 * Time: 16:04
 */
public class FastPeriodicChainConverter {
    public FastChain toPeriodicChain(FastChain chain, HashSet<Integer> poses, int lGrameLength) throws Exception {
        FastChain result = new FastChain(chain.length());
        for (Integer pos : poses) {
            for (int i = 0; i < chain.length(); i++) {
                if (i % lGrameLength == pos)
                    result.add(chain.get(i), i);
            }
        }
        return result;
    }
}
