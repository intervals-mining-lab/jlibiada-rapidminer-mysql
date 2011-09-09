package rapidminer.chain;

import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;

/**
 * Created by IntelliJ IDEA.
 * User: InquisitioN
 * Date: 26.03.2011
 * Time: 15:58:12
 * To change this template use File | Settings | File Templates.
 */
public class RMBuildingCounter extends Operator {
    private OutputPort outValue = getOutputPorts().createPort("Items");
    private InputPort inChainLeight = getInputPorts().createPort("Leight");
    private InputPort inChainPower = getInputPorts().createPort("Power");

    public RMBuildingCounter(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {}
}
