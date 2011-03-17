package test.IntervalAnalysis.Characteristics.Calculators;

import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.UniformChain;
import libiada.Root.SimpleTypes.ValueChar;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 21.01.2011
 * Time: 21:38:38
 */
public class ObjectMother {
    private ValueChar MessageA = new ValueChar('a');
    private ValueChar MessageB = new ValueChar('b');
    private ValueChar MessageC = new ValueChar('c');

    public Chain getChain() throws Exception {
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

    public UniformChain getUniformChain() throws Exception {
            UniformChain TestUChain = new UniformChain(10, MessageA);
            TestUChain.add(MessageA, 3);
            TestUChain.add(MessageA, 7);
            TestUChain.add(MessageA, 4);
            return TestUChain;
        }
}
