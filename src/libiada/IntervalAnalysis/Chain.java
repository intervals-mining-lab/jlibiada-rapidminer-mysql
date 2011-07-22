package libiada.IntervalAnalysis;

import libiada.EventTheory.Place;
import libiada.EventTheory.PsevdoValue;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IChainDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.IDataForCalculator;
import libiada.IntervalAnalysis.Characteristics.Characteristic;
import libiada.Root.IBaseObject;
import libiada.Root.SimpleTypes.ValueChar;
import libiada.Statistics.FrequencyList;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11.12.2010
 * Time: 1:18:31
 */
public class Chain extends ChainWithCharacteristic implements IChainDataForCalculator, IBaseObject {
    protected ArrayList<UniformChain> pUniformChains = new ArrayList<UniformChain>();

    public Chain(int length) throws Exception {
        super(length);
        pAlphabet.remove(0);
    }

    public Chain(String s) throws Exception {
        ClearAndSetNewLength(s.length());
        //pAlphabet.remove(0);
        for (int i = 0; i < s.length(); i++)
        {
            add(new ValueChar(s.charAt(i)), i);
        }
    }

    public UniformChain getIUniformChain(int i) {
        return pUniformChains.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void ClearAndSetNewLength(int length) throws Exception {
        super.ClearAndSetNewLength(length);
        pUniformChains = new ArrayList();
    }

    public void addItem(IBaseObject what, Place where) throws Exception
    {
        super.addItem(what, where);
//        if (pUniformChains.size() != getAlpahbet().getPower())
        if (isNewMessage(what))
        {
            pUniformChains.add(new UniformChain(getLength(), what));
        }
        for (int i = 0; i < pUniformChains.size(); i++)
        {
            pUniformChains.get(i).addItem(what, where);
        }
    }

    private boolean isNewMessage(IBaseObject message) {
        for (UniformChain chain : pUniformChains) {
            if (chain.getMessage().Equals(message)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void buildIntervals() throws Exception {
        if (!IntervalsChanged) return;
        IntervalsChanged = false;

        for (int i = 0; i < pUniformChains.size(); i++)
        {
            IDataForCalculator Datainterface = pUniformChains.get(i);
            pIntervals.sum(Datainterface.getCommonIntervals());
            startinterval.sum(Datainterface.getStartInterval());
            endinterval.sum(Datainterface.getEndInterval());
        }
    }

    @Override
    public double injectIntoCharacteristic(Class<? extends ICharacteristicCalculator> calculatorClass, LinkUp link) throws Exception {
        return ((Characteristic) CharacteristicSnapshot.get(calculatorClass)).value(this, link);
    }

    public ChainWithCharacteristic getUniformChain(IBaseObject baseObject) {
        return null;  //TODO:"Fill method"
    }

    public ChainWithCharacteristicSet getDecomposition(int wordLength, int step) throws Exception {
        ChainWithCharacteristicSet chSet = new ChainWithCharacteristicSet();
        int currentPos = 0;
        int wordCount = Math.round((getLength() - wordLength) / step);
        /*if (step == 1) {
            wordCount -= (wordLength - 1);
        }*/
        for (int j = 0; j <= wordCount; j++) {
            Chain ch = new Chain(wordLength);
            int newWordPos = 0;
            for (int i = currentPos; i < currentPos + wordLength; i++) {
                ch.add(this.get(i), newWordPos);
                newWordPos++;
            }
            currentPos += step;
            chSet.add(ch);
        }
        return chSet;
    }
}
