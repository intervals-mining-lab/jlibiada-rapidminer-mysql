package libiada.Statistics;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.ValueInt;
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
    private ArrayList<Long> pFrequency = new ArrayList<Long>();

    @Override
    public IBaseObject get(int index)
    {
        return (IBaseObject) new DictionaryEntryBase(((IBaseObject) vault.get(index)).Clone(), new ValueInt(pFrequency.get(index)));
    }

    public IBaseObject Clone() {
        FrequencyList FLNew = new FrequencyList();
        FLNew.vault = (ArrayList) vault.clone();
        FLNew.pFrequency = (ArrayList) pFrequency.clone();
        return FLNew;
    }

    public boolean Equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IBin GetBin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getCount() {
        int temp = 0;
        for (Long value : pFrequency)
        {
            temp += value;
        }
        return temp;
    }

    public int add(IBaseObject o) throws Exception {
        int result = indexOf(o);

        if (result == -1)
        {
            result = super.add(o);
            pFrequency.add(1l);
        }
        else pFrequency.set(result, pFrequency.get(result) + 1);
        return result;
    }

    public void sum(FrequencyList intervals) throws Exception {
        for (int i = 0; i < intervals.getPower(); i++)
        {
            IBaseObject value = ((DictionaryEntryBase)intervals.get(i)).getKey();
            long valueCount = ((ValueInt) ((DictionaryEntryBase)intervals.get(i)).getValue()).getValue();
            if (!isContains(value))
            {
                add(value);
                valueCount = valueCount - 1;
            }
            pFrequency.set(indexOf(value), pFrequency.get(indexOf(value)) + valueCount);
        }
    }
}
