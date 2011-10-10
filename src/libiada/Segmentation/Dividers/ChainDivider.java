package libiada.Segmentation.Dividers;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.Segmentation.Cutters.ChainCutter;
import libiada.Statistics.Generators.IGenerator;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 16:19
 */
public class ChainDivider {
    private int maxWordLength;
    private IGenerator generator;

    public ChainDivider(int maxWordLength, IGenerator generator) {
        this.maxWordLength = maxWordLength;
        this.generator = generator;
    }

    public ArrayList<FastChain> divide(FastChain chain) throws Exception {
        ArrayList<FastChain> chains = new ArrayList<FastChain>();
        chains.add(chain);
        return divideFromArray(chains);
    }

    private ArrayList<FastChain> divideFromArray(ArrayList<FastChain> chains) throws Exception {
        ArrayList<FastChain> result = new ArrayList<FastChain>();
        ChainCutter cutter = new ChainCutter();
        for (int i = 0; i < chains.size(); i++) {
            FastChain current = chains.get(i);
            if (current.length() > maxWordLength) {
                int cutPosition = generator.getNextInt() % current.length();
                if (cutPosition == 0)
                    cutPosition = 1;
                ArrayList<FastChain> coughtChains = cutter.cut(current, cutPosition);
                coughtChains = divideFromArray(coughtChains);
                result.addAll(coughtChains);
            }
            else {
                result.add(current);
            }
        }
        return result;
    }
}
