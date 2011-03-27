package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.01.2011
 * Time: 17:13:20
 */
public class PsevdoValue implements IBaseObject {
    private static PsevdoValue singletone = new PsevdoValue();
    
    public static PsevdoValue Instance() {
        return singletone;
    }

    public IBaseObject Clone() {
        return Instance();
    }

    public boolean Equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    public IBin GetBin() {
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        s += '-';
        return s;
    }
}
