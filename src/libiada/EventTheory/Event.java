package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.11.2010
 * Time: 9:08:58
 */
public class Event extends Space implements IBaseObject {
    public Event() throws Exception {
        super();
    }
    public void addDimension(Dimension dimension)
    {
        super.addDimension(dimension);
    }

    public IBaseObject Clone() {
        return null;  //TODO: "Заполнить метод"
    }

    public boolean Equals(Object obj) {
        return false;  //TODO: "Заполнить метод"
    }

    public IBin GetBin() {
        return null;  //TODO: "Заполнить метод"
    }

    public void addToReadRule(Place key, Place value) {
    }

    public void removeFromReadRuleAt(int index) {
    }

    public void removeFromReadRule(Place key) {
    }

    public void addItem(IBaseObject value, Place key) {
    }

    public void addToReadRule(Place value, ReadRule rule) {
        //TODO: "To code method"
    }

    public ReadRule getFromReadRule(Place key) throws Exception {
        if (key == null)
        {
            throw new NullPointerException("Null place in getFromReadRule of Event");
        }
        return null;
    }

    public long getReadRuleCount() {
        return 0;
    }
}
