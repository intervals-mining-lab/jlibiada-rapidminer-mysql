package rapidminer.chain.Generators;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeInt;
import libiada.IntervalAnalysis.Buildings.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/23/11
 * Time: 5:07 AM
 */
public class BuildingsGeneratorRM extends AbstractReader<ExampleSet> {
    public static final String PARAMETER_CHAIN_LENGTH = "chain_length";
    public static final String PARAMETER_ALPHABET_POWER = "chain_alphabet_power";

    public BuildingsGeneratorRM(OperatorDescription od) {
        super(od, ExampleSet.class);
    }

    @Override
    public ExampleSet read() throws OperatorException {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(getParameterAsInt(PARAMETER_CHAIN_LENGTH), getParameterAsInt(PARAMETER_ALPHABET_POWER));
        ArrayList<String> chains = null;
        try {
            chains = tree.getBuildingsAsStrings();
        } catch (Exception e) {
            Logger.getLogger(BuildingsGeneratorRM.class.getName()).log(Level.SEVERE, "It is not impossible to generate buildings", e);
        }
        String data[][] = new String[0][];
        if (chains != null) {
            data = new String[chains.size()][1];
        }
        for (int i = 0; i < chains.size(); i++) {
            data[i][0] = chains.get(i);
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("Chain");
        return outSet;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_LENGTH, "Chain length", 1, 100));
        types.add(new ParameterTypeInt(PARAMETER_ALPHABET_POWER, "Alphabet power", 1, 100));
        return types;
    }
}
