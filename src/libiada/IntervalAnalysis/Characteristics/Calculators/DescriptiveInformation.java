package libiada.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.04.11
 * Time: 15:51
 */
public class DescriptiveInformation implements ICharacteristicCalculator {
    @Override
    public double calculate(UniformChain pChain, LinkUp Link) throws Exception {
        double P = pChain.getCharacteristic(Link, CharacteristicsFactory.getP());
        double Result = Math.pow(pChain.getCharacteristic(Link, CharacteristicsFactory.getDeltaA()), P);
        //Если вся цепь заполнена одинаковыми сообщениями
        //то вероятность равна 1 и считать описательные информации не имеет смысла.
        if (1!=P)
        {
            double P_1 = 1 - P;
            double A_1 = 1 / P_1;
            Result *= Math.pow(A_1, P_1);
        }
        return Result;
    }

    @Override
    public double calculate(Chain pChain, LinkUp Link) throws Exception {
        double temp = 1;

        for (int i = 0; i < pChain.getAlpahbet().getPower(); i++)
        {
            double D = pChain.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getDeltaA());
            double P = pChain.getIUniformChain(i).getCharacteristic(Link, CharacteristicsFactory.getP());
            temp *= Math.pow(D,P) ;
        }
        double P_sum = pChain.getCharacteristic(Link, CharacteristicsFactory.getP());
        if (1!= P_sum)
        {
            temp *= Math.pow(1 / (1 - P_sum), 1 - P_sum);
        }
        return temp;
    }

    @Override
    public String getName() {
        return "DescriptiveInformation";
    }
}
