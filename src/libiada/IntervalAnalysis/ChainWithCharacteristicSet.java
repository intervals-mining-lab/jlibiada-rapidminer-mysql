package libiada.IntervalAnalysis;

import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.Statistics.Picks.Picks;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 24.05.11
 * Time: 19:50
 */
public class ChainWithCharacteristicSet extends ChainSet {
    public Picks getCharacteristics(LinkUp link, ICharacteristicCalculator calculator) throws Exception {
        Picks result = new Picks();
        for (ChainWithCharacteristic chain : chains) {
            result.add(chain.getCharacteristic(link, calculator));
        }
        return result;
    }

    public ChainWithCharacteristic get(int i) {
        return chains.get(i);
    }

}
