package libiada.EventTheory;

import libiada.Root.IBaseObject;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 30.11.2010
 * Time: 10:20:58
 * To change this template use File | Settings | File Templates.
 */
public class ReadRule {
    protected ArrayList vault = new ArrayList();

    public ReadRule(Place place) {
    }

    public IBaseObject getValue(int i) {
        return null;
    }

    public void addPlace(Place value) {
    }

    public ReadRule addRule(ReadRule rule) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public long getCount() {
        return vault.size();
    }
}
