package libiada.IntervalAnalysis;

import libiada.EventTheory.Place;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.Characteristic;
import libiada.Root.IBaseObject;
import libiada.Statistics.FrequencyList;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: ������
 * Date: 11.12.2010
 * Time: 1:37:02
 */
public abstract class ChainWithCharacteristic extends BaseChain implements IDataForCalculator, IBaseObject {
    protected Hashtable CharacteristicSnapshot;
    protected boolean IntervalsChanged;
    protected FrequencyList pIntervals;
    protected FrequencyList startinterval;
    protected FrequencyList endinterval;

    public ChainWithCharacteristic(int length) throws Exception {
        super(length);
        pIntervals = new FrequencyList();
        startinterval = new FrequencyList();
        endinterval = new FrequencyList();
    }

    public ChainWithCharacteristic() throws Exception {
        super();
        pIntervals = new FrequencyList();
        startinterval = new FrequencyList();
        endinterval = new FrequencyList();
    }

    public FrequencyList getCommonIntervals() throws Exception {
        buildIntervals();
        return (FrequencyList) pIntervals.Clone();
    }

    public FrequencyList getStartInterval() throws Exception {
        buildIntervals();
        return (FrequencyList) startinterval.Clone();
    }

    public FrequencyList getEndInterval() throws Exception {
        buildIntervals();
        return (FrequencyList) endinterval.Clone();
    }

    protected abstract void buildIntervals() throws Exception;

    public double getCharacteristic(LinkUp Link, ICharacteristicCalculator CharacteristicType) throws Exception {
        if (!CharacteristicSnapshot.containsKey(CharacteristicType.getClass()))
        {
            Characteristic temp = new Characteristic(CharacteristicType);
            CharacteristicSnapshot.put(CharacteristicType.getClass(), temp);
        }
        return injectIntoCharacteristic(CharacteristicType.getClass(), Link);
    }

    public abstract double injectIntoCharacteristic(Class<? extends ICharacteristicCalculator> calculatorClass, LinkUp link) throws Exception;

    public void addItem(IBaseObject what, Place where) throws Exception
    {
        super.addItem(what, where);
        markChanged();
    }

    public void removeAt(Place place) throws Exception
    {
        super.removeAt(place);
        markChanged();
    }

    private void markChanged() {
        IntervalsChanged = true;
        pIntervals = new FrequencyList();
        startinterval = new FrequencyList();
        endinterval = new FrequencyList();
        CharacteristicSnapshot = new Hashtable();
    }

    public void ClearAndSetNewLength(int length) throws Exception {
        super.ClearAndSetNewLength(length);
        CharacteristicSnapshot = new Hashtable();
        endinterval = new FrequencyList();
        IntervalsChanged = true;
        pIntervals = new FrequencyList();
        startinterval = new FrequencyList();
    }
}
