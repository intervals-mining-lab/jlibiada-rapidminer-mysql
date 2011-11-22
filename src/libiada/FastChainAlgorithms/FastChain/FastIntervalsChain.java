package libiada.FastChainAlgorithms.FastChain;

import libiada.IntervalAnalysis.LinkUp;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 2:55
 */
public abstract class FastIntervalsChain extends FastChainBase {
    protected HashMap<Integer,Integer> pStartIntervals = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pEndIntervals = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pCommonIntervals = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pCircleIntervals = new HashMap<Integer, Integer>();

    protected HashMap<Integer,Integer> pCommonIntervalsPosed = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pStartIntervalsPosed = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pEndIntervalsPosed = new HashMap<Integer, Integer>();
    protected HashMap<Integer,Integer> pCircleIntervalsPosed = new HashMap<Integer, Integer>();

    public FastIntervalsChain(String chainAsString) throws Exception {
        super(chainAsString);
    }

    public FastIntervalsChain() {
        super();
    }

    public FastIntervalsChain(int length) throws Exception {
        super(length);
    }

    public abstract int alphabetPower();

    protected abstract void buildIntervals() throws Exception;

    public HashMap<Integer, Integer> getCommonIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pCommonIntervals;
    }

    public HashMap<Integer, Integer> getStartIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pStartIntervals;
    }

    public HashMap<Integer, Integer> getEndIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pEndIntervals;
    }

    public HashMap<Integer, Integer> getCircleIntervals() throws Exception {
        if (intervalsChanged)
            buildIntervals();
        intervalsChanged = false;
        return pCircleIntervals;
    }

    public HashMap<Integer,Integer> getIntervalPosed(LinkUp linkup) throws Exception {
        if (intervalsChanged)
            buildIntervals();
        if (linkup == LinkUp.Free)
            return pCommonIntervalsPosed;
        else if (linkup == LinkUp.Start)
            return pStartIntervalsPosed;
        else if (linkup == LinkUp.End)
            return pEndIntervalsPosed;
        else if (linkup == LinkUp.Circle)
            return pCircleIntervalsPosed;
        else return null;
    }
}
