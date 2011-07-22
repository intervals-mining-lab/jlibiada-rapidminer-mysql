package libiada.FastChainAlgorithms.FastChain;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 31.05.11
 * Time: 21:31
 */
public class FastChainBase {
    protected ArrayList<Integer> events = new ArrayList<Integer>();
    protected FastAlphabet alphabet = new FastAlphabet();

    public void add(String s) throws Exception {
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
}
