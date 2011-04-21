package libiada.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IChainDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.03.11
 * Time: 21:59
 */
public class Probability implements ICharacteristicCalculator {
    @Override
    public double calculate(UniformChain pChain, LinkUp Link) throws Exception {
        return pChain.getCharacteristic(LinkUp.Both, CharacteristicsFactory.getN()) /
               pChain.getCharacteristic(LinkUp.Both, CharacteristicsFactory.getLength());
    }

    @Override
    public double calculate(Chain pChain, LinkUp Link) throws Exception {
        IChainDataForCalculator data = pChain;
        double result = 0;
        for (int i = 0; i < pChain.getAlpahbet().getPower(); i++) {
            result += data.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getP());
        }
        if (result > 1)
        {
            result = 1;
        }
        return result;
    }

    @Override
    public String getName() {
        return "Probability (P)";
    }
}
