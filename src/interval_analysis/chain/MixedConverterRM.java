package interval_analysis.chain;

import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.UserError;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import libiada.IntervalAnalysis.MixedChain;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 22:27
 */
public class MixedConverterRM extends Operator {
    public InputPort inChains = getInputPorts().createPort("chain", RMChainSet.class);
    public OutputPort outChain = getOutputPorts().createPort("mixed");

    public MixedConverterRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws UserError {
        RMChainSet inputChainsSet = inChains.getData(RMChainSet.class);
        RMChainSet outChainsSet = new RMChainSet();

        for (int i = 0; i < inputChainsSet.getCount(); i++) {
            try {
                MixedChain chain = new MixedChain(inputChainsSet.get(i).toString());
                outChainsSet.add(chain);
            } catch (Exception e) {
                System.err.print("Error of creating mixed chain");
            }
        }
        outChain.deliver(outChainsSet);
    }
}
