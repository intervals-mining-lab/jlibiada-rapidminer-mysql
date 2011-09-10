package libiada.FastChainAlgorithms.FastChain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 21:31
 */
public abstract class FastChainBase {
    protected ArrayList<Integer> events = new ArrayList<Integer>();
    protected FastAlphabet alphabet = new FastAlphabet();
    protected boolean intervalsChanged = true;
    protected int count = 0;
    private boolean calculatedCount = false;

    public FastChainBase(String chainAsString) throws Exception {
        for (int i = 0; i < chainAsString.length(); i++) {
            add(Character.toString(chainAsString.charAt(i)));
        }
    }

    public FastChainBase() {
    }

    public FastChainBase(int length) throws Exception {
        alphabet.add("-");
        for (int i = 0; i < length; i++) {
            events.add(0);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Integer cur : events) {
            result += alphabet.get(cur);
        }
        return result;
    }

    public abstract int alphabetPower();

    public void add(String s) throws Exception {
        intervalsChanged = true;
        if (null == s || s.equalsIgnoreCase(""))
            throw new Exception("Can not add null object or empty string to chain");
        int index = alphabet.indexOf(s);
        if (-1 == index) {
            alphabet.add(s);
            events.add(alphabet.size() - 1);
        } else {
            events.add(index);
        }
    }

    public int length() {
        return events.size();
    }

    public int getEventCount() {
        if (!calculatedCount) {
            count = 0;
            for (Integer value : events) {
                if (value != 0)
                    count++;
            }
        }
        calculatedCount = true;
        return count;
    }

    public void add(String s, int i) throws Exception {
        int pos = alphabet.indexOf(s);
        if (-1 == pos) {
            alphabet.add(s);
            pos = alphabet.size()-1;
        }
        events.set(i, pos);
    }

    public String toDividedString(char sym) {
        String result = "";
        boolean isFirst = true;
        for (Integer cur : events) {
            if (!isFirst) {
                result += Character.toString(sym);
            }
            isFirst = false;
            result += alphabet.get(cur);
        }
        return result;
    }

    public String get(int i) {
        return alphabet.get(events.get(i));
    }

    public FastAlphabet getAlphabet() {
        return alphabet;
    }

    public String getBuilding() {
        String result = "";
        for (Integer value : events) {
            result += Integer.toString(value + 1);
        }
        return result;
    }
}
