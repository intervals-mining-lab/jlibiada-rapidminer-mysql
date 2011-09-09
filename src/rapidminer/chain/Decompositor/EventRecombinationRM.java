package rapidminer.chain.Decompositor;

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
import com.rapidminer.parameter.ParameterTypeInt;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 18.05.11
 * Time: 0:04
 */
public class EventRecombinationRM extends Operator {
    private OutputPort outChain = getOutputPorts().createPort("chains");
    private InputPort inChain = getInputPorts().createPort("src_chains");
    private static final String PARAMETER_CHAIN_RECOMBINATION_COUNT = "Recombinated_chains_count: ";
    private static final String PARAMETER_CHAIN_RECOMBINATION_GAMAUT = "Recombinated_chains_gamaut: ";

    public EventRecombinationRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet chains = inChain.getData();
        Attributes attributes = chains.getAttributes();

        String[][] data = new String[chains.size() * (1 + getParameterAsInt(PARAMETER_CHAIN_RECOMBINATION_GAMAUT) * getParameterAsInt(PARAMETER_CHAIN_RECOMBINATION_COUNT))][1];
        int i = 0;
        for (Example example : chains) {
            String chain = example.getValueAsString(attributes.get("Chain"));
            data[i][0] = chain;
            i++;
            for (int j = 0; j < getParameterAsInt(PARAMETER_CHAIN_RECOMBINATION_COUNT); j++) {
                String s = chain;
                for (int k = 0; k < getParameterAsInt(PARAMETER_CHAIN_RECOMBINATION_GAMAUT); k++) {
                    s = recombinateNext(s);
                    data[i][0] = s;
                    i++;
                }
            }
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("Chain");
        outChain.deliver(outSet);
    }

    private String recombinateNext(String chain) {
        Random rnd = new Random();
        int pos1 =  1 + Math.abs(rnd.nextInt() % (chain.length()-2));
        int pos2 =  1 + Math.abs(rnd.nextInt() % (chain.length()-2));
        if (pos1 > pos2) {
            int p = pos2;
            pos2 = pos1;
            pos1 = p;
        } else if (pos1 == pos2) {
            pos2++;
        }

        char firstSym = chain.charAt(pos1);
        char secondSym = chain.charAt(pos2);
        String newChain;
        newChain = chain.substring(0, pos1);
        newChain += Character.toString(secondSym);
        newChain += chain.substring(pos1+1, pos2);
        newChain += Character.toString(firstSym);
        newChain += chain.substring(pos2+1, chain.length());

        return newChain;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_RECOMBINATION_COUNT, "Recombinated chains count: ", 1, 1000));
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_RECOMBINATION_GAMAUT, "Recombinated chains gamaut: ", 1, 1000));
        return types;
    }
}
