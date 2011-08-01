package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.Interfaces.IFastCalculator;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 0:46
 */
public class FastCalculatorFactory {
    public static IFastCalculator getVolume() {
        return new FastVolume();
    }

    public static IFastCalculator getHentropy() {
        return new FastHentropy();
    }

    public static IFastCalculator getPropability() {
        return new FastPropability();
    }

    public static IFastCalculator getEventCount() {
        return new FastIntervalsCount();
    }

    public static IFastCalculator getGamaut() {
        return new FastGamaut();
    }

    public static IFastCalculator getAverageRemoteness() {
        return new FastAverageRemoteness();
    }

    public static IFastCalculator getRegularity() {
        return new FastRegularity();
    }

    public static IFastCalculator getLength() {
        return new FastLength();
    }

    public static IFastCalculator getPositionedPropability(String event, HashSet<Integer> poses, int period) {
        return new FastPositionedPropability(event, poses, period);
    }

    public static IFastCalculator getFastShepherd() {
        return new FastShepherd();
    }

    public static IFastCalculator getPositionedEventCount(String event, HashSet<Integer> poses, int period) {
        return new FastPositionedEventCount(event, poses, period);
    }
}
