package libiada.IntervalAnalysis.Characteristics;

import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.Calculators.*;
import sun.management.counter.Counter;

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

    public static ICharacteristicCalculator getP() {
        return new Probability();
    }

    public static ICharacteristicCalculator getN() {
        return new Count();
    }

    public static ICharacteristicCalculator getLength() {
        return new Length();
    }

    public static ICharacteristicCalculator getDeltaA() {
        return new ArithmeticMean();
    }

    public static ICharacteristicCalculator getH() {
        return new IdentificationInformation();
    }
}
