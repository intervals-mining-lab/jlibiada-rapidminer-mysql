package rapidminer.chain.Characteristics;

import com.rapidminer.example.*;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.*;
import com.rapidminer.parameter.*;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorBase;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.08.11
 * Time: 13:29
 */
public class FastBinaryBuildingCharacteristicRM extends Operator {
    protected OutputPort outValue = getOutputPorts().createPort("values");
    protected InputPort inChain = getInputPorts().createPort("chain", ExampleSet.class);

    private static final String FIRST_UCHAIN_SYM = "First chain";
    private static final String SECOND_UCHAIN_SYM = "Second chain";
    private static final String PARAMETER_CHARACT = "Characteristic";
    private static final String PARAMETER_LINKUP = "Link up";

    private String[] itemsCharact = new String[] {"delta", "g"};
    private String[] itemsLinkUP = new String[] {"Start", "End", "Both", "Circle", "Free"};

    public FastBinaryBuildingCharacteristicRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet chains = inChain.getData();
        Attributes attributes = chains.getAttributes();

        String[][] values = new String[chains.size()][chains.getAttributes().size() + 1];
        for (int i = 0; i < chains.size(); i++) {
            Example example = chains.getExample(i);
            Iterator<Attribute> attrIter = attributes.allAttributes();
            int j = 0;
            while (attrIter.hasNext()) {
                values[i][j] = example.getValueAsString(attrIter.next());
                j++;
            }
            String characteristicValue = "0";
            FastChain chain = null;
            try {
                String valueAsString = example.getValueAsString(attributes.get("Chain"));
                chain = new FastChain();
                for (int k = 0; k < valueAsString.length(); k++) {
                    chain.add(Character.toString(valueAsString.charAt(k)));
                }
            } catch (Exception e) {
                System.err.print("Chain creating error");
            }
            try {
                Double v = getValue(chain);
                characteristicValue = v.toString();//v;
            } catch (Exception e) {
                System.err.print("Characteristic calculating error");
            }
            values[i][j] = characteristicValue;
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);

        Iterator<Attribute> attrIter = attributes.allAttributes();
        int j = 1;
        while (attrIter.hasNext()) {
            outSet.getAttributes().get("att" + j).setName(attrIter.next().getName());
            j++;
        }
        try {
            outSet.getAttributes().get("att" + j).setName(getCharacteristicName());
        } catch (Exception e) {
            System.err.print("");
        }

        outValue.deliver(outSet);
    }

    protected FastCalculatorBase getCharacteristic() throws Exception {
        String item = getParameterAsString(PARAMETER_CHARACT);

        if (item.contentEquals("delta")) {
            return FastCalculatorFactory.getBinaryAverageGeomertyInterval(getParameter(FIRST_UCHAIN_SYM), getParameter(SECOND_UCHAIN_SYM));
        } else if (item.contentEquals("g")) {
            return FastCalculatorFactory.getBinaryAverageRemoteness(getParameter(FIRST_UCHAIN_SYM), getParameter(SECOND_UCHAIN_SYM));
        } else {
            throw new Exception("Not identified building characteristic");
        }
    }

    protected String getCharacteristicName() throws Exception {
        return getCharacteristic().getName();
    }

    protected LinkUp getLinkUp() throws UndefinedParameterError {
        String item = getParameterAsString(PARAMETER_LINKUP);

        if (item.contentEquals("Start")) {
            return LinkUp.Start;
        } else if (item.contentEquals("End")) {
            return LinkUp.End;
        } else if (item.contentEquals("Both")) {
            return LinkUp.Both;
        } else if (item.contentEquals("Circle")) {
            return LinkUp.Circle;
        } else if (item.contentEquals("Free")) {
            return LinkUp.Free;
        } else {
            return LinkUp.Start;
        }
    }

    protected Double getValue(FastChain chain) throws Exception {
        return getCharacteristic().getValue(chain, getLinkUp());
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeString(FIRST_UCHAIN_SYM, "First chain: "));
        types.add(new ParameterTypeString(SECOND_UCHAIN_SYM, "Second chain: "));
        types.add(new ParameterTypeCategory(PARAMETER_LINKUP, "Link up", itemsLinkUP, 0));
        types.add(new ParameterTypeCategory(PARAMETER_CHARACT, "Characteristic", itemsCharact, 0));
        return types;
    }
}
