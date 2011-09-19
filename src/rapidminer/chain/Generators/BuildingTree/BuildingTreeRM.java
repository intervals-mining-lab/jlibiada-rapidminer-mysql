package rapidminer.chain.Generators.BuildingTree;

import com.rapidminer.example.Attributes;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.learner.tree.TreeModel;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeInt;
import libiada.IntervalAnalysis.Buildings.BuildingsTree;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 19.09.11
 * Time: 21:07
 */
public class BuildingTreeRM extends Operator {
    protected OutputPort outValue = getOutputPorts().createPort("data");
    protected InputPort inData = getInputPorts().createPort("model");
    private static final String PARAMETER_CHAIN_LENGTH = "Chain_length";
    private static final String PARAMETER_ALPHABET_POWER = "Alphabet_power";

    public BuildingTreeRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet exampleSet = inData.getData();
        Attributes attributes = exampleSet.getAttributes();

        BuildingsTree root = new BuildingsTree(exampleSet, 1, 1);
        root.rebuildTreeForBuildings(getParameterAsInt(PARAMETER_CHAIN_LENGTH), getParameterAsInt(PARAMETER_ALPHABET_POWER));

        TreeModel model = new TreeModel(exampleSet, root);
        outValue.deliver(model);
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_LENGTH, "Chain length", 1, 100));
        types.add(new ParameterTypeInt(PARAMETER_ALPHABET_POWER, "Alphabet power", 1, 100));
        return types;
    }
}
