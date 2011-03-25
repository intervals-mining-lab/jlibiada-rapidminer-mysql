package interval_analysis.chain;

import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.UserError;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import libiada.IntervalAnalysis.Buildings.BuildingGenerator;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 3:51
 */
public class BuildingGeneratorFromContentsRM extends Operator {
    private OutputPort outBuildings = getOutputPorts().createPort("buildings");
    private InputPort inContents = getInputPorts().createPort("contents");

    public BuildingGeneratorFromContentsRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws UserError {
        RMContents contents = inContents.getData(RMContents.class);

        BuildingGenerator generator = new BuildingGenerator();
        ArrayList<String> chains = null;
        try {
            chains = generator.getBuildings(contents);
        } catch (Exception e) {
            Logger.getLogger(BuildingsGeneratorRM.class.getName()).log(Level.SEVERE, "It is not impossible to generate buildings", e);
        }

        RMChainSet chSet = new RMChainSet();
        try {
            chSet.addChainsFromStrings(chains);
        } catch (Exception e) {
            System.err.print("It is couldn't add chains from strins");
        }

        outBuildings.deliver(chSet);
    }
}
