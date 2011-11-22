package rapidminer.chain.Characteristics;

import com.rapidminer.example.*;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeCategory;
import com.rapidminer.parameter.UndefinedParameterError;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastShepherd;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastTramontanoMacchiato;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorBase;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.07.11
 * Time: 20:09
 */
public class FastBuildingCharacteristicRM extends Operator {
    protected OutputPort outValue = getOutputPorts().createPort("values");
    protected InputPort inChain = getInputPorts().createPort("chain", ExampleSet.class);

    protected static final String PARAMETER_LINK_UP = "Link up";
    protected static final String PARAMETER_CHARACTERISTIC = "Characteristic";

    protected String[] itemsLinkUp = new String[] {"Start", "End", "Both", "Circle", "Free"};
    private String[] itemsCharacteristic = new String[] {"p", "g", "H", "deltaG", "r", "Length", "Shepherd", "Tramontano", "Ro", "Mu"};

    public FastBuildingCharacteristicRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet chains = inChain.getData();
        Attributes attributes = chains.getAttributes();

        String[][] values = new String[chains.size()][attributes.size() + 1];
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

    protected String getCharacteristicName() throws Exception {
        return getCharacteristic().getName();
    }

    protected Double getValue(FastChain chain) throws Exception {
        return getCharacteristic().getValue(chain, getLinkUp());
    }

    protected FastCalculatorBase getCharacteristic() throws Exception {
        String item = getParameterAsString(PARAMETER_CHARACTERISTIC);

        if (item.contentEquals("g")) {
            return FastCalculatorFactory.getAverageRemoteness();
        } else if (item.contentEquals("p")) {
            return FastCalculatorFactory.getPropability();
        } else if (item.contentEquals("H")) {
            return FastCalculatorFactory.getHentropy();
        } else if (item.contentEquals("G")) {
            return FastCalculatorFactory.getGamaut();
        } else if (item.contentEquals("r")) {
            return FastCalculatorFactory.getRegularity();
        } else if (item.contentEquals("Length")) {
            return FastCalculatorFactory.getLength();
        } else if (item.contentEquals("Shepherd")) {
            return new FastShepherd();
        } else if (item.contentEquals("Tramontano")) {
            return new FastTramontanoMacchiato();
        } else if (item.contentEquals("Ro")) {
            return FastCalculatorFactory.getRo();
        } else if (item.contentEquals("Mu")) {
            return FastCalculatorFactory.getMu();
        }
        else {
            throw new Exception("Not identified building characteristic");
        }
    }

    protected LinkUp getLinkUp() throws UndefinedParameterError {
        String item = getParameterAsString(PARAMETER_LINK_UP);

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

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeCategory(PARAMETER_LINK_UP, "Link up", itemsLinkUp, 1));
        types.add(new ParameterTypeCategory(PARAMETER_CHARACTERISTIC, "Characteristic", itemsCharacteristic, 1));
        return types;
    }
}
