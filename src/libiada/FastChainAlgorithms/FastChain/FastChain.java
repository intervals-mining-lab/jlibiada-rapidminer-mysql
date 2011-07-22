package libiada.FastChainAlgorithms.FastChain;

import org.jfree.chart.plot.MeterPlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 20:01
 */
public class FastChain extends FastChainBase {
    public HashMap<Integer, FastUniformChain> uChains = new HashMap<Integer, FastUniformChain>();

    public FastUniformChain getFastUniformChain(int i) throws Exception {
        if (uChains.containsKey(i))
            return uChains.get(i);
        FastUniformChain uChain = new FastUniformChain();
        for (Integer event : events) {
            if (event == i) {
                uChain.add(alphabet.get(i));
            } else {
                uChain.add("-");
            }
        }
        uChains.put(i, uChain);
        return uChain;
    }
}
