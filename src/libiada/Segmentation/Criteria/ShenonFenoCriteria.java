package libiada.Segmentation.Criteria;

import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 10.10.11
 * Time: 1:55
 */
public class ShenonFenoCriteria extends Criteria {
    private LinkUp linkUp;
    private double minValue = Double.MAX_VALUE;
    private double epsilon = 0;

    public ShenonFenoCriteria(LinkUp linkUp, double epsilon) {
        this.linkUp = linkUp;
        this.epsilon = epsilon;
    }

    @Override
    protected void clear() {
        minValue = Double.MAX_VALUE;
    }

    @Override
    protected boolean isBetter(FastChain segmentedChain, FastChain rootChain) throws Exception {
        double g_M = FastCalculatorFactory.getAverageRemoteness().getValue(segmentedChain, linkUp);
        double g_m = FastCalculatorFactory.getAverageRemoteness().getValue(rootChain, linkUp);
        double L_av = FastCalculatorFactory.getAverageWordLength().getValue(segmentedChain, linkUp);
        double value = Math.abs((segmentedChain.length() * g_M) / (rootChain.length() * g_m) - L_av) - epsilon;
        if (value < minValue) {
            minValue = value;
            return true;
        }
        return false;
    }
}
