package libiada.Segmentation.Criteria;

import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 17:07
 */
public class MaxG extends Criteria {
    private LinkUp linkUp;
    private double maxG = 0;

    public MaxG(LinkUp linkUp) {
        this.linkUp = linkUp;
    }

    @Override
    protected void clear() {
        maxG = 0;
    }

    @Override
    protected boolean isBetter(FastChain segmentedChain, FastChain rootChain) throws Exception {
        double g = FastCalculatorFactory.getAverageRemoteness().getValue(segmentedChain, linkUp);
        if (g >= maxG) {
            maxG = g;
            return true;
        }
        return false;
    }
}
