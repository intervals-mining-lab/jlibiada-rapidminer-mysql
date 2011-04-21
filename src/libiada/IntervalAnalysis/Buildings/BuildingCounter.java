package libiada.IntervalAnalysis.Buildings;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 20.03.11
 * Time: 22:33
 */
public class BuildingCounter
{
    public int calculate(int chainLength, int alphabetPower) throws Exception {
        if (chainLength <= 0)
            throw new Exception("Chain Length is below or equal to zero");
        if (alphabetPower <= 0)
            throw new Exception("Alphabet Power is below or equal to zero");
        ArrayList<Integer> a_prev = new ArrayList<Integer>();
        ArrayList<Integer> a_current = new ArrayList<Integer>();
        a_prev.add(1);
        int F = 1;
        int S = 2;
        while (S <= chainLength) {
            F = 0;
            a_current.clear();
            for (Integer item : a_prev) {
                for (int i = 0; i < item-1; i++) {
                    a_current.add(item);
                    F=F+item;
                }
                int lastItem = Math.min(item + 1, alphabetPower);
                a_current.add(lastItem);
                F = F + lastItem;
            }
            a_prev = (ArrayList<Integer>) a_current.clone();
            S++;
        }
        return F;
    }
}