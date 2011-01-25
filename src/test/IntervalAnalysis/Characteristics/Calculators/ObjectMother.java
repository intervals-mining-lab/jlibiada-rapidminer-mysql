package test.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.Root.SimpleTypes.ValueChar;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 21.01.2011
 * Time: 21:38:38
 * To change this template use File | Settings | File Templates.
 */
public class ObjectMother {
    private ValueChar MessageA = new ValueChar('a');
    private ValueChar MessageB = new ValueChar('b');
    private ValueChar MessageC = new ValueChar('c');

    public Chain TestChain() throws Exception {
        Chain TestChain = new Chain(10);

        TestChain.add(MessageB, 0);
        TestChain.add(MessageB, 1);
        TestChain.add(MessageA, 2);

        TestChain.add(MessageA, 3);
        TestChain.add(MessageC, 4);
        TestChain.add(MessageB, 5);

        TestChain.add(MessageA, 6);
        TestChain.add(MessageC, 7);
        TestChain.add(MessageC, 8);

        TestChain.add(MessageB, 9);

        return TestChain;
    }
}
