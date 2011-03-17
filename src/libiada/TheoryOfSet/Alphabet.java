package libiada.TheoryOfSet;

import libiada.EventTheory.PsevdoValue;
import libiada.Root.IBaseObject;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 11.12.2010
 * Time: 1:49:09
 */
public class Alphabet {
    protected ArrayList vault = new ArrayList();

    public int getPower() {
        return vault.size();
    }

    public int add(IBaseObject value) throws Exception {
        if (vault.contains((value)))
        {
            throw new Exception("Повторное добавление объекта в алфавит");
        }
        if (value == null)
        {
            throw new Exception("Попытка добавить нулевой объект в алфавит");
        }
        IBaseObject obj = value.Clone();
        vault.add(obj);
        return vault.indexOf(obj);
    }

    public boolean isContains(IBaseObject obj)
    {
        return vault.contains(obj);
    }

    public int indexOf(IBaseObject value) {
        int index = -1;
        for (int i = 0; i < vault.size(); i++)
        {
            if (value.Equals(vault.get(i)))
                index = i;
        }
        return index;
    }

    public void remove(int index) {
        vault.remove(index);
    }

    public IBaseObject get(int index) {
        return (IBaseObject) vault.get(index);
    }
}
