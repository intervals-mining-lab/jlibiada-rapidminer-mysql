package libiada.Segmentation.Criteria;

import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11.10.11
 * Time: 2:58
 */
public class OrlovPartlyCriteria extends Criteria {
    private LinkUp linkUp;
    private double minDelta = Double.MAX_VALUE;

    public OrlovPartlyCriteria(LinkUp linkUp) {
        this.linkUp = linkUp;
    }

    @Override
    protected void clear() {
        minDelta = Double.MAX_VALUE;
    }

    @Override
    protected boolean isBetter(FastChain segmentedChain, FastChain rootChain) throws Exception {
        double delta = Math.abs(FastCalculatorFactory.getOrlovPartlyCriteria().getValue(segmentedChain, linkUp));
        if (delta <= minDelta) {
            minDelta = delta;
            return true;
        }
        return false;
    }
}
