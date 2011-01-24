package libiada.IntervalAnalysis;

import libiada.EventTheory.Dimension;
import libiada.EventTheory.Space;
import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.SimpleTypes.ValueChar;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 11.12.2010
 * Time: 1:22:23
 * To change this template use File | Settings | File Templates.
 */
public class BaseChain extends Space implements IBaseObject {
    public BaseChain(int length) throws Exception {
        ClearAndSetNewLength(length);
    }

    public void add(IBaseObject baseObject, int index) throws Exception {
         addItem(baseObject, getPlacePattern().setValues(new long[] {index}));
    }

    public int getLength()
    {
        return getPlaceCount();
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

    public void ClearAndSetNewLength(int length) throws Exception
    {
        if (length <= 0)
        {
            throw new Exception("Длинна цепи <= 0");
        }
        deleteDimentions();
        addDimension(new Dimension(0, length - 1));
    }
}
