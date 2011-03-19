package libiada.IntervalAnalysis;

import libiada.EventTheory.Dimension;
import libiada.EventTheory.Space;
import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.SimpleTypes.ValueChar;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11.12.2010
 * Time: 1:22:23
 */
public class BaseChain extends Space implements IBaseObject {
    public BaseChain(int length) throws Exception {
        ClearAndSetNewLength(length);
    }

    public BaseChain() throws Exception {
        super();
    }

    @Override
    public String toString()
    {
        String s = "";
        for (long obj : vault)
        {
            s += pAlphabet.get((int) obj).toString();
        }
        return s;
    }

    public void add(IBaseObject baseObject, int index) throws Exception {
         addItem(baseObject, getPlacePattern().setValues(new long[] {index}));
    }

    public int getLength()
    {
        return getPlaceCount();
    }

    public IBaseObject Clone() {
        return null;  //TODO: "????????? ?????"
    }

    public boolean Equals(Object obj) {
        return false;  //TODO: "????????? ?????"
    }

    public IBin GetBin() {
        return null;  //TODO: "????????? ?????"
    }

    public void ClearAndSetNewLength(int length) throws Exception
    {
        if (length <= 0)
        {
            throw new Exception("Chain length <= 0");
        }
        deleteDimentions();
        addDimension(new Dimension(0, length - 1));
    }
}
