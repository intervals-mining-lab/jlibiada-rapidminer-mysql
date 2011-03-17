package libiada.Statistics;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;
import libiada.Root.SimpleTypes.ValueInt;
import libiada.TheoryOfSet.Alphabet;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 11.12.2010
 * Time: 1:47:53
 */
public class FrequencyList extends Alphabet implements IBaseObject {
    private ArrayList<Long> pFrequency = new ArrayList<Long>();

    @Override
    public IBaseObject get(int index)
    {
        return new DictionaryEntryBase(((IBaseObject) vault.get(index)).Clone(), new ValueInt(pFrequency.get(index)));
    }

    @Override
    public IBaseObject Clone() {
        FrequencyList frecList = new FrequencyList();
        frecList.pFrequency = (ArrayList<Long>) this.pFrequency.clone();
        frecList.vault = (ArrayList) this.vault.clone();
        return frecList;  //TODO: "Заполнить метод"
    }

    public boolean Equals(Object obj) {
        return false;  //TODO: "Заполнить метод"
    }

    public IBin GetBin() {
        return null;  //TODO: "Заполнить метод"
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
