package libiada.FastChainAlgorithms.FastChain.Calculators;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 0:46
 */
public class FastCalculatorFactory {
    public static FastCalculatorBase getVolume() {
        return new FastVolume();
    }

    public static FastCalculatorBase getHentropy() {
        return new FastHentropy();
    }

    public static FastCalculatorBase getPropability() {
        return new FastPropability();
    }

    public static FastCalculatorBase getEventCount() {
        return new FastIntervalsCount();
    }

    public static FastCalculatorBase getGamaut() {
        return new FastGamaut();
    }

    public static FastCalculatorBase getAverageRemoteness() {
        return new FastAverageRemoteness();
    }

    public static FastCalculatorBase getRegularity() {
        return new FastRegularity();
    }

    public static FastCalculatorBase getLength() {
        return new FastLength();
    }

    public static FastCalculatorBase getPositionedPropability(String event, HashSet<Integer> poses, int period) {
        return new FastPositionedPropability(event, poses, period);
    }

    public static FastCalculatorBase getFastShepherd() {
        return new FastShepherd();
    }

    public static FastCalculatorBase getPositionedEventCount(String event, HashSet<Integer> poses, int period) {
        return new FastPositionedEventCount(event, poses, period);
    }

    public static FastCalculatorBase getFastTramontanoMacchiato() {
        return new FastTramontanoMacchiato();
    }

    public static FastCalculatorBase getGeometryInterval() {
        return new FastAverageGeometryInterval();
    }

    public static FastCalculatorBase getBinaryAverageGeomertyInterval(String sym1, String sym2) {
        return new BinaryFastAverageGeometryInterval(sym1, sym2);
    }

    public static FastCalculatorBase getBinaryAverageRemoteness(String sym1, String sym2) {
        return new BinaryFastAverageRemoteness(sym1, sym2);
    }

    public static FastCalculatorBase getPositionedAverageRemoteness(String event, HashSet<Integer> poses, int period) {
        return new FastPositionedAverageRemoteness(event, poses, period);
    }

    public static FastCalculatorBase getAverageWordLength() {
        return new FastAverageWordLength();
    }

    public static FastCalculatorBase getOrlovPartlyCriteria() {
        return new FastOrlovPartlyCriteria();
    }

    public static FastCalculatorBase getRo() {
        return new RoCalculator();
    }

    public static FastCalculatorBase getMu() {
        return new MuCalculator();
    }
}
