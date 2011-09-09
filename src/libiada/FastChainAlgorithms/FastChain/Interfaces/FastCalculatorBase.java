package libiada.FastChainAlgorithms.FastChain.Interfaces;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 0:46
 */
public abstract class FastCalculatorBase {
    protected String event = null;

    public abstract double getValue(FastChain chain, LinkUp linkUp) throws Exception;

    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        event = chain.getEvent();
        return 0;
    }

    public String getName() {
        if (event != null)
            return "(" + event + ")";
        else
            return "";
    }

    public abstract String getType();

    public abstract String getGroup();
}
