package interval_analysis.chain;

import com.rapidminer.operator.*;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.*;
import libiada.IntervalAnalysis.Buildings.Tree;
import libiada.IntervalAnalysis.Chain;

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
public class BuildingsGeneratorRM extends AbstractReader<RMChainSet> {
    public static final String PARAMETER_CHAIN_LENGTH = "chain_length";
    public static final String PARAMETER_ALPHABET_POWER = "chain_alphabet_power";

    public BuildingsGeneratorRM(OperatorDescription od) {
        super(od, RMChainSet.class);
    }

    @Override
    public RMChainSet read() throws OperatorException {
        Tree tree = new Tree();
        tree.rebuildTreeForBuildings(getParameterAsInt(PARAMETER_CHAIN_LENGTH), getParameterAsInt(PARAMETER_ALPHABET_POWER));
        ArrayList<Chain> chains = null;
        try {
            chains = tree.getBuildingsAsChains();
        } catch (Exception e) {
            Logger.getLogger(BuildingsGeneratorRM.class.getName()).log(Level.SEVERE, "It is not impossible to generate buildings", e);
        }

        RMChainSet chSet = new RMChainSet();
        chSet.addChains(chains);
        return chSet;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_LENGTH, "Chain length", 1, 100));
        types.add(new ParameterTypeInt(PARAMETER_ALPHABET_POWER, "Alphabet power", 1, 100));
        return types;
    }
}
