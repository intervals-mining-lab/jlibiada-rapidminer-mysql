package rapidminer.chain.Characteristics;

import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeString;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.Chain;
import libiada.Root.SimpleTypes.ValueString;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.05.11
 * Time: 18:50
 */
public class UBuildingCharacteristicRM extends FastBuildingCharacteristicRM {
    private static final String PARAMETER_CHAIN_EVENT = "Event_of_uniform_chain:";

    public UBuildingCharacteristicRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        super.doWork();
    }

    @Override
    protected Double getValue(FastChain chain) throws Exception {
        String event = getParameterAsString(PARAMETER_CHAIN_EVENT);
        return getCharacteristic().getValue(chain.getFastUniformChain(event), getLinkUp());
    }

    @Override
    protected String getCharacteristicName() throws Exception {
        return getCharacteristic().getName() + " (" + getParameterAsString(PARAMETER_CHAIN_EVENT) + ")";
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeString(PARAMETER_CHAIN_EVENT, "Event of uniform chain: "));
        return types;
    }
}
