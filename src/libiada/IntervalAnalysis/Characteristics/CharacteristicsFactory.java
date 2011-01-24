package libiada.IntervalAnalysis.Characteristics;

import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.Calculators.GeometricMiddling;
import libiada.IntervalAnalysis.Characteristics.Calculators.IntervalsCount;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 21.01.2011
 * Time: 22:18:10
 * To change this template use File | Settings | File Templates.
 */
public class CharacteristicsFactory {
    public static ICharacteristicCalculator getDeltaG() {
        return new GeometricMiddling();
    }

    public static ICharacteristicCalculator getIntervalsCount() {
        return new IntervalsCount();
    }
}
