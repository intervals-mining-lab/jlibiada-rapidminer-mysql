package libiada.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IChainDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;
import libiada.Root.SimpleTypes.ValueInt;
import libiada.Statistics.DictionaryEntryBase;
import libiada.Statistics.FrequencyList;

/**
 * Created by IntelliJ IDEA.
 * User: ������
 * Date: 25.01.2011
 * Time: 20:13:46
 * To change this template use File | Settings | File Templates.
 */
public class Gamut implements ICharacteristicCalculator {

    public double calculate(UniformChain pChain, LinkUp Link) throws Exception {
        IDataForCalculator Data = pChain;

        FrequencyList CommonIntervalList = Data.getCommonIntervals();
        FrequencyList StartInterval = Data.getStartInterval();
        FrequencyList EndInterval = Data.getEndInterval();

        double result = 0;
        for (int i = 0; i < CommonIntervalList.getPower(); i++)
        {
            double r1 = ((ValueInt)(((DictionaryEntryBase)CommonIntervalList.get(i)).getValue())).getValue();
            double r2 = ((ValueInt)(((DictionaryEntryBase)CommonIntervalList.get(i)).getKey())).getValue();
            result += r1 * (Math.log10(r2) / (Math.log10(2)));
        }

        switch (Link)
        {
            case Start:
                return result + Math.log(((ValueInt) ((DictionaryEntryBase)StartInterval.get(0)).getKey()).getValue())/Math.log(2);
            case End:
                return result + Math.log(((ValueInt) ((DictionaryEntryBase)EndInterval.get(0)).getKey()).getValue())/Math.log(2);
            case Both:
                return result + Math.log(((ValueInt) ((DictionaryEntryBase)StartInterval.get(0)).getKey()).getValue())/Math.log(2) +
                    Math.log(((ValueInt) ((DictionaryEntryBase)EndInterval.get(0)).getKey()).getValue())/Math.log(2);
            default:
                throw new Exception("����� �������� ������ :)");
        }
    }

    public double calculate(Chain pChain, LinkUp Link) throws Exception {
        IChainDataForCalculator Data = pChain;
        double temp = 0;
        for (int i = 0; i < pChain.getAlpahbet().getPower(); i++)
        {
            temp += Data.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getG());
        }
        return temp;
    }
}
