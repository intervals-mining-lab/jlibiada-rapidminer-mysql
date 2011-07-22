package libiada.FastChainAlgorithms.FastChain;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 21:30
 */
public class FastUniformChain extends FastChainBase {
    private boolean intervalsChanged = true;
    private HashMap<Integer, Integer> pIntervals = null;
    private HashMap<Integer, Integer> startIntervals = null;
    private HashMap<Integer, Integer> endIntervals = null;

    public FastUniformChain() throws Exception {
        alphabet.add("-");
        pIntervals = new HashMap<Integer, Integer>();
        startIntervals = new HashMap<Integer, Integer>();
        endIntervals = new HashMap<Integer, Integer>();
    }

    public HashMap<Integer, Integer> getCommonIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return pIntervals;
    }

    public HashMap<Integer, Integer> getStartIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return startIntervals;
    }

    public HashMap<Integer, Integer> getEndIntervals() throws Exception {
        if (intervalsChanged) {
            buildIntervals();
            intervalsChanged = false;
        }
        return endIntervals;
    }

    protected void buildIntervals() throws Exception {
        pIntervals.clear();
        startIntervals.clear();
        endIntervals.clear();
        int next = -1;
        HashMap<Integer, Integer> intervalList;
        do
        {
            int current = next;
            next = getRight(current);
            if (next == length())
            {
                intervalList = getFrequancyIntervalList(length());
            }
            else
            {
                intervalList = getFrequancyIntervalList(current);
            }

            addInterval(intervalList, next - current);
        }
        while (next != length());
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
            return startIntervals;
        }
        if (number == length())
        {
            return endIntervals;
        }
        return pIntervals;
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
}
