package libiada.IntervalAnalysis.Characteristics;

import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.Calculators.AverageRemoteness;
import libiada.IntervalAnalysis.Characteristics.Calculators.Gamut;
import libiada.IntervalAnalysis.Characteristics.Calculators.GeometricMiddling;
import libiada.IntervalAnalysis.Characteristics.Calculators.IntervalsCount;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.01.2011
 * Time: 22:18:10
 */
public class CharacteristicsFactory {
    public static ICharacteristicCalculator getDeltaG() {
        return new GeometricMiddling();
    }

    public static ICharacteristicCalculator getIntervalsCount() {
        return new IntervalsCount();
    }

    public static ICharacteristicCalculator getG() {
        return new Gamut();
    }

    public static ICharacteristicCalculator getAverageRemoteness() {
        return new AverageRemoteness();
    }
}
