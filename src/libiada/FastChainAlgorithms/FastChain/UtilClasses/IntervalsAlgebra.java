package libiada.FastChainAlgorithms.FastChain.UtilClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.11
 * Time: 23:21
 */
public class IntervalsAlgebra {
    public HashMap<Integer, Integer> add(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer, Integer> result = first;
        for (Map.Entry<Integer, Integer> entry : second.entrySet()) {
            Integer value1 = entry.getValue();
            if (result.containsKey(entry.getKey()))
            {
                Integer value2 = result.get(entry.getKey());
                result.remove(entry.getKey());
                result.put(entry.getKey(), value1 + value2);
            } else {
                result.put(entry.getKey(), value1);
            }
        }
        return result;
    }

    public int getCount(HashMap<Integer, Integer> intervals) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            result += entry.getValue();
        }
        return result;
    }
}
