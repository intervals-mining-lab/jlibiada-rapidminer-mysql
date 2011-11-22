package libiada.FastChainAlgorithms.FastChain;

import libiada.IntervalAnalysis.LinkUp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 21:30
 */
public class FastUniformChain extends FastIntervalsChain {

    private ArrayList<Integer> poses = new ArrayList<Integer>();

    public FastUniformChain() throws Exception {
        super();
        alphabet.add("-");
        commonConstructor();
    }

    public FastUniformChain(int length) throws Exception {
        super(length);
        commonConstructor();
    }

    @Override
    public int getEventCount() {
        int count = 0;
        for (Integer event : events) {
            if (event == 1)
                count++;
        }
        return count;
    }

    private void commonConstructor() throws Exception {
        pCommonIntervals = new HashMap<Integer, Integer>();
        pStartIntervals = new HashMap<Integer, Integer>();
        pEndIntervals = new HashMap<Integer, Integer>();
        pCircleIntervals = new HashMap<Integer, Integer>();
    }

    public HashMap<Integer, Integer> getCommonIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return pCommonIntervals;
    }

    public HashMap<Integer, Integer> getStartIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return pStartIntervals;
    }

    public HashMap<Integer, Integer> getEndIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return pEndIntervals;
    }

    @Override
    public HashMap<Integer, Integer> getCircleIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return pCircleIntervals;
    }

    @Override
    protected void buildIntervals() throws Exception {
        pCommonIntervals.clear();
        pStartIntervals.clear();
        pEndIntervals.clear();
        int next = -1;
        HashMap<Integer, Integer> intervalList;
        HashMap<Integer, Integer> intervalPosedlList;
        do
        {
            int current = next;
            next = getRight(current);
            if (next == length())
            {
                intervalList = getFrequancyIntervalList(length());
                intervalPosedlList = getIntervalPosedList(length());
            }
            else
            {
                intervalList = getFrequancyIntervalList(current);
                intervalPosedlList = getIntervalPosedList(current);
            }

            intervalPosedlList.put(new Integer(next), new Integer(next-current));
            addInterval(intervalList, next - current);
        }
        while (next != length());
        pCircleIntervals.put(pStartIntervals.entrySet().iterator().next().getKey() +
                pEndIntervals.entrySet().iterator().next().getKey() - 1, 1);
    }

    private HashMap<Integer, Integer> getIntervalPosedList(int number) {
        if (number == -1)
        {
            return pStartIntervalsPosed;
        }
        if (number == length())
        {
            return pEndIntervalsPosed;
        }
        return pCommonIntervalsPosed;
    }

    private void addInterval(HashMap<Integer, Integer> intervalList, int interval) {
        if (intervalList.containsKey(interval)) {
            int count = intervalList.get(interval);
            intervalList.remove(interval);
            intervalList.put(interval, count+1);
        } else {
            intervalList.put(interval, 1);
        }
    }

    private HashMap<Integer, Integer> getFrequancyIntervalList(int number) {
        if (number == -1)
        {
            return pStartIntervals;
        }
        if (number == length())
        {
            return pEndIntervals;
        }
        return pCommonIntervals;
    }

    private int getRight(int current) {
        for (int i = current + 1; i < length(); i++)
        {
            if (events.get(i) == 1)
            {
                return i;
            }
        }
        return length();
    }

    @Override
    public int alphabetPower() {
        return 1;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEvent() {
        return alphabet.get(1);
    }

    public ArrayList<Integer> getPositions() {
        if (intervalsChanged)
            buildPoses();
        return poses;
    }

    private void buildPoses() {
        for (int i = 0; i < length(); i++) {
            if (events.get(i) != 0)
                poses.add(i+1);
        }
    }
}
