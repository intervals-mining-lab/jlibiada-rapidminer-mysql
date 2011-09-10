package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.FastCalculatorBase;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 19:01
 */
public class FastAverageGeometryInterval extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double V = FastCalculatorFactory.getVolume().getValue(chain, linkUp);
        double n = FastCalculatorFactory.getEventCount().getValue(chain, linkUp);
        return Math.pow(V, 1 / n);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        super.getValue(chain, linkUp);
        double V = FastCalculatorFactory.getVolume().getValue(chain, linkUp);
        double n = FastCalculatorFactory.getEventCount().getValue(chain, linkUp);
        return Math.pow(V, 1 / n);
    }

    @Override
    public String getName() {
        return "gelta_g" + super.getName();
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Building characteristic";
    }
}
