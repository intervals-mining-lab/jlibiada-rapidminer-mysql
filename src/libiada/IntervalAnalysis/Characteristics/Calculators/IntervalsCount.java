package libiada.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.ChainWithCharacteristic;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.01.2011
 * Time: 23:00:49
 */
public class IntervalsCount implements ICharacteristicCalculator {
    public double calculate(UniformChain pChain, LinkUp link) throws Exception {
        return commonCalculate(pChain, link);
    }

    public double calculate(Chain pChain, LinkUp link) throws Exception {
        return commonCalculate(pChain, link);
    }

    @Override
    public String getName() {
        return "Intervals count";
    }

    private double commonCalculate(ChainWithCharacteristic pChain, LinkUp link) throws Exception {
        switch (link)
        {
            case Start:
                return pChain.getCommonIntervals().getCount() + pChain.getStartInterval().getCount();
            case End:
                return pChain.getCommonIntervals().getCount() + pChain.getEndInterval().getCount();
            case Both:
                return pChain.getCommonIntervals().getCount() + pChain.getStartInterval().getCount() + pChain.getEndInterval().getCount();
            case Circle:
                return pChain.getCommonIntervals().getCount() + pChain.getStartInterval().getCount();
            default:
                throw new Exception("Very strange error :)");
        }
    }
}
