package libiada.Statistics;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.ValueInt;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 25.01.2011
 * Time: 20:25:02
 * To change this template use File | Settings | File Templates.
 */
public class DictionaryEntryBase implements IBaseObject {
    private IBaseObject key;
    private IBaseObject value;

    public DictionaryEntryBase(IBaseObject pkey, IBaseObject pvalue) {
        key = pkey;
        value = pvalue;
    }

    public IBaseObject getKey()
    {
        return key;
    }

    public IBaseObject getValue()
    {
        return value;
    }

    public void setKey(IBaseObject key)
    {
        this.key = key;
    }

    public void setValue(IBaseObject value)
    {
        this.value = value;
    }

    public IBaseObject Clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean Equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IBin GetBin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
