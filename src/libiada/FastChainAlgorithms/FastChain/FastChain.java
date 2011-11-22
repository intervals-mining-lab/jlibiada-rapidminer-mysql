package libiada.FastChainAlgorithms.FastChain;

import libiada.FastChainAlgorithms.FastChain.UtilClasses.IntervalsAlgebra;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 20:01
 */
public class FastChain extends FastIntervalsChain {
    private HashMap<Integer, FastUniformChain> uChains = new HashMap<Integer, FastUniformChain>();

    private HashMap<String,HashMap<Integer, Integer>> pChainToChainIntervals = new HashMap<String, HashMap<Integer, Integer>>();

    public FastChain(int length) throws Exception {
        super(length);
    }

    public FastChain() {
        super();
    }

    public FastChain(String chainAsString) throws Exception {
        super(chainAsString);
    }

    public FastUniformChain getFastUniformChain(int i) throws Exception {
        int count = 0;
        if (uChains.containsKey(i))
            return uChains.get(i);
        FastUniformChain uChain = new FastUniformChain();
        for (Integer event : events) {
            if (event == i) {
                uChain.add(alphabet.get(i));
                count++;
            } else {
                uChain.add("-");
            }
        }
        uChain.setCount(count);
        uChains.put(i, uChain);
        return uChain;
    }

    @Override
    public int getEventCount() {
        return events.size();
    }

    @Override
    protected void buildIntervals() throws Exception {
        IntervalsAlgebra iaS = new IntervalsAlgebra();
        IntervalsAlgebra iaE = new IntervalsAlgebra();
        IntervalsAlgebra iaCom = new IntervalsAlgebra();
        IntervalsAlgebra iaCir = new IntervalsAlgebra();

        IntervalsAlgebra iaComPoses = new IntervalsAlgebra();
        IntervalsAlgebra iaSPoses = new IntervalsAlgebra();
        IntervalsAlgebra iaEPoses = new IntervalsAlgebra();
        IntervalsAlgebra iaCirPoses = new IntervalsAlgebra();

        for (int i = 0; i < alphabet.size(); i++) {
            pStartIntervals = iaS.add(pStartIntervals, getFastUniformChain(i).getStartIntervals());
            pEndIntervals = iaE.add(pEndIntervals, getFastUniformChain(i).getEndIntervals());
            pCommonIntervals = iaCom.add(pCommonIntervals, getFastUniformChain(i).getCommonIntervals());
            pCircleIntervals = iaCir.add(pCircleIntervals, getFastUniformChain(i).getCircleIntervals());

            pCommonIntervalsPosed = iaComPoses.add(pCommonIntervalsPosed, getFastUniformChain(i).getIntervalPosed(LinkUp.Free));
            pStartIntervalsPosed = iaSPoses.add(pStartIntervalsPosed, getFastUniformChain(i).getIntervalPosed(LinkUp.Start));
            pEndIntervalsPosed = iaEPoses.add(pEndIntervalsPosed, getFastUniformChain(i).getIntervalPosed(LinkUp.End));
            pCircleIntervalsPosed = iaCirPoses.add(pCircleIntervalsPosed, getFastUniformChain(i).getIntervalPosed(LinkUp.Circle));
        }
    }

    @Override
    public int alphabetPower() {
        return alphabet.size();
    }

    public FastUniformChain getFastUniformChain(String s) throws Exception {
        return getFastUniformChain(alphabet.indexOf(s));
    }

}
