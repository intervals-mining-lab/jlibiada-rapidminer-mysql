package libiada.IntervalAnalysis;

import libiada.EventTheory.Place;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.Characteristic;
import libiada.Root.IBaseObject;
import libiada.Root.ValueInt;
import libiada.Statistics.FrequencyList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 11.12.2010
 * Time: 1:39:23
 */
public class UniformChain extends ChainWithCharacteristic implements IBaseObject {
    public UniformChain(int length, IBaseObject message) throws Exception {
        super(length);
        pAlphabet.add(message);
    }

    public void addItem(IBaseObject what, Place where) throws Exception
    {
        if (getMessage().Equals(what))
        {
            super.addItem(what, where);            
        }
    }

    @Override
    protected void buildIntervals() throws Exception {
        if (!IntervalsChanged) return;

        IntervalsChanged = false;

        pIntervals = new FrequencyList();
        int next = -1;
        FrequencyList IntervalList;
        do
        {
            int current = next;
            next = getRight(current);
            if (next == getLength())
            {
                IntervalList = getFrequancyIntervalList(getLength());
            }
            else
            {
                IntervalList = getFrequancyIntervalList(current);
            }

            IntervalList.add(new ValueInt(next - current));
        }
        while (next != getLength());
    }

    private int getRight(int current) {
        for (int i = current + 1; i < getLength(); i++)
        {
            if (vault.get(i) == 1)
            {
                return i;
            }
        }
        return getLength();
    }

    private FrequencyList getFrequancyIntervalList(int number) {
        if (number == -1)
        {
            return startinterval;
        }
        if (number == getLength())
        {
            return endinterval;
        }
        return pIntervals;
    }

    @Override
    public double injectIntoCharacteristic(Class<? extends ICharacteristicCalculator> calculatorClass, LinkUp link) throws Exception {
        return ((Characteristic) CharacteristicSnapshot.get(calculatorClass)).value(this, link);
    }

    public IBaseObject getMessage() {
        return pAlphabet.get(1);
    }
}
