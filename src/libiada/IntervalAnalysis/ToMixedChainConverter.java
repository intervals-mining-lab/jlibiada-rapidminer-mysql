package libiada.IntervalAnalysis;

import libiada.EventTheory.PsevdoValue;
import libiada.Root.IBaseObject;
import libiada.Root.SimpleTypes.ValueChar;
import libiada.Root.SimpleTypes.ValueInt;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 19:40
 */
public class ToMixedChainConverter {
    public MixedChain convert(Chain chain) throws Exception {
        MixedChain mixedChain = new MixedChain(chain.getLength());
        for (int uChainIndex = 0; uChainIndex < chain.getAlpahbet().getPower(); uChainIndex++) {
            UniformChain uChain = chain.getIUniformChain(uChainIndex);
            int currentEventNum = 0;
            for (int currentUPos = 0; currentUPos < uChain.getLength(); currentUPos++) {
                if (uChain.get(currentUPos).getClass() != PsevdoValue.class) {
                    currentEventNum++;
                } else {
                    continue;
                }
                ValueChar message = new ValueChar((char) (currentEventNum + 'A' - 1));
                mixedChain.add(message, currentUPos);
            }
        }
        return mixedChain;
    }
}
