package libiada.IntervalAnalysis;

import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IChainDataForCalculator;
import libiada.Root.IBaseObject;
import libiada.Root.SimpleTypes.ValueChar;
import libiada.Statistics.FrequencyList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 11.12.2010
 * Time: 1:18:31
 * To change this template use File | Settings | File Templates.
 */
public class Chain extends ChainWithCharacteristic implements IChainDataForCalculator, IBaseObject {
    public Chain(long i) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public UniformChain IUniformChain(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FrequencyList getCommonIntervals() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FrequencyList getStartInterval() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FrequencyList getEndInterval() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ChainWithCharacteristic getUniformChain(IBaseObject baseObject) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
