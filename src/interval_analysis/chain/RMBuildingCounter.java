package interval_analysis.chain;

import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeString;
import libiada.IntervalAnalysis.Buildings.BuildingCounter;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: InquisitioN
 * Date: 22.04.2011
 * Time: 19:28:54
 * To change this template use File | Settings | File Templates.
 */

public class RMBuildingCounter extends Operator {
    private OutputPort outValue = getOutputPorts().createPort("Items");
    private InputPort inChainData = getInputPorts().createPort("InputData");

    public static final String PARAMETER_CHAIN_LENGTH = "ChainLength attribute name";
    public static final String PARAMETER_ALPHABET_POWER = "AlphabetPower attribute name";

    public RMBuildingCounter(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet ChainData = inChainData.getData();
        Attributes attributes = ChainData.getAttributes();

        String data[][]=new String [ChainData.size()][3];
        int item = 0;
        for (Example example : ChainData) {
            Double L = Double.parseDouble(example.getValueAsString(attributes.get(getParameterAsString(PARAMETER_CHAIN_LENGTH))));
            Double P = Double.parseDouble(example.getValueAsString(attributes.get(getParameterAsString(PARAMETER_ALPHABET_POWER))));

            BuildingCounter counter = new BuildingCounter();
            Integer count = 0;
            try {
                count = counter.calculate((int) Math.round(L), (int) Math.round(P));
            } catch (Exception e) {
                System.err.print("Error of calculating");
            }
            
            data[item][0] = L.toString();
            data[item][1] = P.toString();
            data[item][3] = count.toString();
            item++;
        }
        ExampleSet outValueSet = ExampleSetFactory.createExampleSet(data);
        outValueSet.getAttributes().get("att1").setName("Chain length");
        outValueSet.getAttributes().get("att2").setName("Alphabet power");
        outValueSet.getAttributes().get("att3").setName("Count");
        outValue.deliver(outValueSet);
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeString(PARAMETER_CHAIN_LENGTH, "ChainLength attribute name"));
        types.add(new ParameterTypeString(PARAMETER_ALPHABET_POWER, "AlphabetPower attribute name"));
        return types;
        }
}
