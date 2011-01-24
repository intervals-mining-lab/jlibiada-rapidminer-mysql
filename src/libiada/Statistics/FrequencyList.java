package libiada.Statistics;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.TheoryOfSet.Alphabet;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 11.12.2010
 * Time: 1:47:53
 * To change this template use File | Settings | File Templates.
 */
public class FrequencyList extends Alphabet implements IBaseObject {
    private ArrayList pFrequency = new ArrayList();

    public IBaseObject Clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean Equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IBin GetBin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getCount() {
        return pFrequency.size();
    }

    public void sum(FrequencyList intervals) {
        //TODO: "Срочно"
    }
}
