package libiada.FastChainAlgorithms.FastChain;

import libiada.FastChainAlgorithms.FastChain.UtilClasses.FastIntervalsChain;
import libiada.FastChainAlgorithms.FastChain.UtilClasses.IntervalsAlgebra;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 20:01
 */
public class FastChain extends FastIntervalsChain {
    private HashMap<Integer, FastUniformChain> uChains = new HashMap<Integer, FastUniformChain>();

    private HashMap<Integer,Integer> pStartIntervals = new HashMap<Integer, Integer>();
    private HashMap<Integer,Integer> pEndIntervals = new HashMap<Integer, Integer>();
    private HashMap<Integer,Integer> pCommonIntervals = new HashMap<Integer, Integer>();
    private HashMap<Integer,Integer> pCircleIntervals = new HashMap<Integer, Integer>();

    public FastChain(int length) throws Exception {
        alphabet.add("-");
        for (int i = 0; i < length; i++) {
            events.add(0);
        }
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
    public HashMap<Integer, Integer> getCommonIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pCommonIntervals;
    }

    @Override
    public HashMap<Integer, Integer> getStartIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pStartIntervals;
    }

    @Override
    public HashMap<Integer, Integer> getEndIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pEndIntervals;
    }

    @Override
    public HashMap<Integer, Integer> getCircleIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pCircleIntervals;
    }

    private void buildIntervals() throws Exception {
        IntervalsAlgebra iaS = new IntervalsAlgebra();
        IntervalsAlgebra iaE = new IntervalsAlgebra();
        IntervalsAlgebra iaCom = new IntervalsAlgebra();
        IntervalsAlgebra iaCir = new IntervalsAlgebra();
        for (int i = 0; i < alphabet.size(); i++) {
            pStartIntervals = iaS.add(pStartIntervals, getFastUniformChain(i).getStartIntervals());
            pEndIntervals = iaE.add(pEndIntervals, getFastUniformChain(i).getEndIntervals());
            pCommonIntervals = iaCom.add(pCommonIntervals, getFastUniformChain(i).getCommonIntervals());
            pCircleIntervals = iaCir.add(pCircleIntervals, getFastUniformChain(i).getCircleIntervals());
        }
    }

    @Override
    public int alphabetPower() {
        return alphabet.size();
    }

    public String get(int i) {
        return alphabet.get(events.get(i));
    }

    public FastUniformChain getFastUniformChain(String s) throws Exception {
        return getFastUniformChain(alphabet.indexOf(s));
    }
}
