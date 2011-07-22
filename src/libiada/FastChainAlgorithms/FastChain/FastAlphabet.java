package libiada.FastChainAlgorithms.FastChain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 20:11
 */
public class FastAlphabet {
    private ArrayList<String> values = new ArrayList<String>();

    public void add(String s) throws Exception {
        if (null == s || s.equalsIgnoreCase(""))
            throw new Exception("Can not add null object or empty string to alphabet");
        if (-1 != values.indexOf(s))
            throw new Exception("Can not add event to alphabet twice");
        values.add(s);
    }

    public boolean isContain(String value) {
        for (String current : values) {
            if (current.equalsIgnoreCase(value))
                return true;
        }
        return false;
    }

    public int indexOf(String s) {
        return values.indexOf(s);
    }

    public int size() {
        return values.size();
    }

    public String get(int i) {
        return values.get(i);
    }
}
