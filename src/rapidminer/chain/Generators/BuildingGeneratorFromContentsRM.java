package rapidminer.chain.Generators;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.UserError;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import libiada.IntervalAnalysis.Buildings.BuildingGenerator;
import rapidminer.chain.RMContents;

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
        String data [][] = new String[chains.size()][1];
        for (int i = 0; i < chains.size(); i++) {
            data[i][0] = chains.get(i);
        }
        ExampleSet chSet = ExampleSetFactory.createExampleSet(data);
        chSet.getAttributes().get("att1").setName("Chain");

        outBuildings.deliver(chSet);
    }
}
