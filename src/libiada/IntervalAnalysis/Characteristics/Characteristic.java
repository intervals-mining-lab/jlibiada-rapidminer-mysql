package libiada.IntervalAnalysis.Characteristics;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.ChainWithCharacteristic;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.LinkUp;
import libiada.IntervalAnalysis.UniformChain;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 21.01.2011
 * Time: 21:11:51
 */
public class Characteristic {
    private boolean Calculated = false;
    private ChainWithCharacteristic pChain;
    private double pStartValue;
    private double pEndValue;
    private double pBothValue;
    private ICharacteristicCalculator Calculator;

    public Characteristic(ICharacteristicCalculator calculator) {
        Calculator = calculator;
    }

    public double value(UniformChain Chain, LinkUp link) throws Exception {
        if (!Calculated || !Chain.Equals(pChain))
        {
            pChain = Chain;
            pStartValue = Calculator.calculate(Chain, LinkUp.Start);
            pEndValue = Calculator.calculate(Chain, LinkUp.End);
            pBothValue = Calculator.calculate(Chain, LinkUp.Both);
            Calculated = true;
        }
        return getCurrentValue(link);
    }

    public double value(Chain Chain, LinkUp link) throws Exception {
        if (!Calculated || !Chain.Equals(pChain))
        {
            pChain = Chain;
            pStartValue = Calculator.calculate(Chain, LinkUp.Start);
            pEndValue = Calculator.calculate(Chain, LinkUp.End);
            pBothValue = Calculator.calculate(Chain, LinkUp.Both);
            Calculated = true;
        }
        return getCurrentValue(link);
    }

    private double getCurrentValue(LinkUp link) throws Exception {
        switch (link) {
            case Start:
                return pStartValue;
            case End:
                return pEndValue;
            case Both:
                return pBothValue;
            default:
                throw new Exception("��� ���� �� ������");
        }
    }
}
