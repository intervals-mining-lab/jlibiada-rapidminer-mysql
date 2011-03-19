package libiada.Root.SimpleTypes;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.11.2010
 * Time: 10:11:33
 */
public class ValueInt implements IBaseObject {
    private long value;

    public ValueInt(long i) {
        value = i;
    }

    public IBaseObject Clone() {
        return new ValueInt(value);
    }

    public boolean Equals(Object obj) {
        return value == ((ValueInt)obj).getValue();
    }

    public IBin GetBin() {
        return null;  //TODO: "????????? ?????"
    }

    public long getValue() {
        return value;
    }
}
