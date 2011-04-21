package interval_analysis;

import com.rapidminer.example.*;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.*;
import com.rapidminer.parameter.*;
import com.sun.org.apache.xpath.internal.operations.*;
import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces.ICharacteristicCalculator;
import libiada.IntervalAnalysis.Characteristics.CharacteristicsFactory;
import libiada.IntervalAnalysis.LinkUp;

import java.lang.String;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/12/11
 * Time: 2:28 AM
 */
public class BuildingCharacteristicRM extends Operator {
    private OutputPort outValue = getOutputPorts().createPort("values");
    private InputPort inChain = getInputPorts().createPort("chain", ExampleSet.class);

    public static final String PARAMETER_LINK_UP = "Link up";
    public static final String PARAMETER_CHARACTERISTIC = "Characteristic";

    private String[] itemsLinkUp = new String[] {"Start", "End", "Both", "Circle"};
    private String[] itemsCharacteristic = new String[] {"g", "H", "deltaG"};

    public BuildingCharacteristicRM(OperatorDescription description) {
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
            Chain chain = null;
            try {
                chain = new Chain(example.getValueAsString(attributes.get("Chain")));
            } catch (Exception e) {
                System.err.print("Невозможно создать цепь");
            }
            try {
                Double v = new Double(chain.getCharacteristic(getLinkUp(), getCharacteristic()));
                characteristicValue = v.toString();//v;
            } catch (Exception e) {
                System.err.print("Ошибка вычисления характеристики");
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
            outSet.getAttributes().get("att" + j).setName(getCharacteristic().getName());
        } catch (Exception e) {
            System.err.print("");
        }

        outValue.deliver(outSet);
    }

    private ICharacteristicCalculator getCharacteristic() throws Exception {
        String item = getParameterAsString(PARAMETER_CHARACTERISTIC);

        if (item.contentEquals("g")) {
            return CharacteristicsFactory.getAverageRemoteness();
        } else if (item.contentEquals("H")) {
            return CharacteristicsFactory.getH();
        } else if (item.contentEquals("deltaG")) {
            return CharacteristicsFactory.getDeltaG();
        } else {
            throw new Exception("Не известная характеристика строя.");
        }
    }

    public LinkUp getLinkUp() throws UndefinedParameterError {
        String item = getParameterAsString(PARAMETER_LINK_UP);

        if (item.contentEquals("Start")) {
            return LinkUp.Start;
        } else if (item.contentEquals("End")) {
            return LinkUp.End;
        } else if (item.contentEquals("Both")) {
            return LinkUp.Both;
        } else if (item.contentEquals("Circle")) {
            return LinkUp.Circle;
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
