package libiada.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.ChainWithCharacteristic;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IDataForCalculator;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 21.01.2011
 * Time: 23:00:49
 * To change this template use File | Settings | File Templates.
 */
public class IntervalsCount implements ICharacteristicCalculator {
    public double calculate(UniformChain pChain, LinkUp link) throws Exception {
        return commonCalculate(pChain, link);
    }

    public double calculate(Chain pChain, LinkUp link) throws Exception {
        return commonCalculate(pChain, link);
    }

    private double commonCalculate(ChainWithCharacteristic pChain, LinkUp link) throws Exception {
        IDataForCalculator data = pChain;
        switch (link)
        {
            case Start:
                return data.getCommonIntervals().getCount() + data.getStartInterval().getCount();
            case End:
                return data.getCommonIntervals().getCount() + data.getEndInterval().getCount();
            case Both:
                return data.getCommonIntervals().getCount() + data.getStartInterval().getCount() + data.getEndInterval().getCount();
            default:
                throw new Exception("Супер странная ошибка :)");
        }
    }
}
