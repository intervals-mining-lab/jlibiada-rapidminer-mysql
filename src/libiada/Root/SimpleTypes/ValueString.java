package libiada.Root.SimpleTypes;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.05.11
 * Time: 19:00
 */
public class ValueString implements IBaseObject {
    private String value = "";

    public ValueString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public IBaseObject Clone() {
        return new ValueString(value);
    }

    @Override
    public boolean Equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        return value.equalsIgnoreCase(obj.toString());
    }

    @Override
    public IBin GetBin() {
        return null;  //TODO: "????????? ?????"
    }
}
