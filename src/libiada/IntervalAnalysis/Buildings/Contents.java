package libiada.IntervalAnalysis.Buildings;

import libiada.Root.IBaseObject;
import libiada.TheoryOfSet.Alphabet;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 25.03.11
 * Time: 2:50
 */
public class Contents {
    public Alphabet alphabet = new Alphabet();
    public ArrayList<Integer> counts = new ArrayList<Integer>();

    public void add(IBaseObject obj, int count) throws Exception {
        if (!alphabet.isContains(obj)) {
            alphabet.add(obj);
            counts.add(count);
        }
        else {
            int index = alphabet.indexOf(obj);
            counts.set(index, counts.get(index) + count);
        }
    }

    public void subElementCount(int index) throws Exception {
        if (counts.size() < index) {
            throw new Exception("Element does not find in contents");
        }
        counts.set(index - 1, counts.get(index - 1) - 1);
    }

    public int getElementCount(int index) throws Exception {
        if (counts.size() < index) {
            throw new Exception("Element does not find in contents");
        }
        return counts.get(index - 1);
    }

    public int getChainLength() {
        int len = 0;
        for (Integer count : counts) {
            len += count;
        }
        return len;
    }

    @Override
    public Contents clone() {
        Contents contents = new Contents();
        for (int index = 0; index < counts.size(); index++) {
            try {
                contents.add(alphabet.get(index), counts.get(index));
            } catch (Exception e) {
                System.err.print("Contents clone error");
            }
        }
        return contents;
    }

    public int getPower() {
        return alphabet.getPower();
    }
}
