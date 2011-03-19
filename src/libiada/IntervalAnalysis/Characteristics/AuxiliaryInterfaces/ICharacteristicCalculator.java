package libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.01.2011
 * Time: 21:14:18
 */
public interface ICharacteristicCalculator {
        double calculate(UniformChain pChain, LinkUp Link) throws Exception;

        double calculate(Chain pChain, LinkUp Link) throws Exception;
}
