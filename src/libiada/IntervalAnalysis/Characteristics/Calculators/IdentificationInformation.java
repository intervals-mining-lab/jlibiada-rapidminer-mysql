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
 * Time: 21:51
 */
public class IdentificationInformation implements ICharacteristicCalculator {
    @Override
    public double calculate(UniformChain pChain, LinkUp Link) throws Exception {
        return Math.log(pChain.getCharacteristic(Link, CharacteristicsFactory.getDeltaA())) / Math.log(2);
    }

    @Override
    public double calculate(Chain pChain, LinkUp Link) throws Exception {
        IChainDataForCalculator data = pChain;
        double result = 0;
        for (int i = 0; i < pChain.getAlpahbet().getPower(); i++) {
            double p = data.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getP());
            double da = data.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getDeltaA());
            result += p * Math.log(da) / Math.log(2);
        }
        return result;
    }

    @Override
    public String getName() {
        return "Entropy (H)";
    }
}
