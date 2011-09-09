package rapidminer.Bioinformatics;

import com.rapidminer.example.*;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeCategory;
import com.rapidminer.parameter.ParameterTypeInt;
import com.rapidminer.parameter.UndefinedParameterError;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 25.07.11
 * Time: 22:40
 */
public class ExoneIntroneMarkerRM extends Operator {
    public InputPort inORFs = getInputPorts().createPort("ORFs", ExampleSet.class);
    public InputPort inGenes = getInputPorts().createPort("Genes", ExampleSet.class);
    public OutputPort outORFs = getOutputPorts().createPort("ORFs");

    private static final String PARAMETER_TYPE_DELTA = "Start / stop point deviation";
    private static final String PARAMETER_MARK_TYPE = "Start / stop position compare type";

    private String[] markType = new String[] {"Equal", "Near"};

    public ExoneIntroneMarkerRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet inORFset = inORFs.getData();
        ExampleSet inGenesSet = inGenes.getData();

        Attributes attributesORF = inORFset.getAttributes();
        Attributes attributesGenes = inGenesSet.getAttributes();
        
        HashSet<LinkedHashMap<String, Integer>> genes = new HashSet<LinkedHashMap<String, Integer>>();
        for (Example example : inGenesSet) {
            String start = example.getValueAsString(attributesGenes.get("Start position"));
            String stop = example.getValueAsString(attributesGenes.get("Stop position"));
            if (-1 != start.indexOf('.'))
                start =  start.substring(0, start.indexOf('.'));
            if (-1 != stop.indexOf('.'))
                stop = stop.substring(0, stop.indexOf('.'));
            LinkedHashMap<String, Integer> startstoppos = new LinkedHashMap<String, Integer>();
            startstoppos.put("Start", Integer.parseInt(start));
            startstoppos.put("Stop", Integer.parseInt(stop));
            genes.add(startstoppos);
        }

        

        Iterator<Attribute> attrIter = attributesORF.allAttributes();
        String outValues[][] = new String[inORFset.size()][attributesORF.size()+1];
        int attrNum = 0;
        while (attrIter.hasNext()) {
            Attribute attribute = attrIter.next();
            int i = 0;
            for (Example example : inORFset) {
                outValues[i][attrNum] = example.getValueAsString(attribute);
                i++;
            }

            attrNum++;
        }

        int i = 0;
        for (Example example : inORFset) {
            String start = example.getValueAsString(attributesORF.get("Start position"));
            if (-1 != start.indexOf('.'))
                start =  start.substring(0, start.indexOf('.'));
            String stop = example.getValueAsString(attributesORF.get("Stop position"));
            if (-1 != stop.indexOf('.'))
                stop = stop.substring(0, stop.indexOf('.'));
            if (isInArray(genes, Integer.parseInt(start), Integer.parseInt(stop)))
                outValues[i][attributesORF.size()] = "1";
            else
                outValues[i][attributesORF.size()] = "0";
            i++;
        }

        ExampleSet outSet = ExampleSetFactory.createExampleSet(outValues);
        attrIter = attributesORF.allAttributes();
        int curIndex = 1;
        while (attrIter.hasNext()){
            outSet.getAttributes().get("att" + curIndex).setName(attrIter.next().getName());
            curIndex++;
        }
        outSet.getAttributes().get("att" + curIndex).setName("Is Gene");

        outORFs.deliver(outSet);
    }

    private boolean isInArray(HashSet<LinkedHashMap<String, Integer>> genes, Integer start, Integer stop) throws UndefinedParameterError {
        boolean isEqualType = getParameterAsString(PARAMETER_MARK_TYPE).equalsIgnoreCase("Equal");
        for (LinkedHashMap<String, Integer> s : genes) {
            Integer curStart = s.get("Start");
            Integer curStop = s.get("Stop");
            boolean secondExpression = (curStart.equals(start)) && (curStop.equals(stop));
            boolean firstExpression = (curStart - getParameterAsInt(PARAMETER_TYPE_DELTA) <= (start)) && (curStop.equals(stop));
            if (!isEqualType && firstExpression || (isEqualType && secondExpression))
                return true;
        }
        return false;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_TYPE_DELTA, "Start / stop point deviation", 0, 1000));
        types.add(new ParameterTypeCategory(PARAMETER_MARK_TYPE, "Start / stop position compare type", markType, 1));
        return types;
    }
}
