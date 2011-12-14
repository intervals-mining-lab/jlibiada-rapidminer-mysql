package rapidminer.Bioinformatics;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 01.08.11
 * Time: 21:51
 */
public class ExoneIntroneCutterRM extends Operator {
    public InputPort inORFs = getInputPorts().createPort("ORFs", ExampleSet.class);
    public InputPort inGenome = getInputPorts().createPort("Genes", ExampleSet.class);
    public OutputPort outORFs = getOutputPorts().createPort("ORFs");


    private static final String START_POS_ATT_NAME = "Start position attribute name";
    private static final String STOP_POS_ATT_NAME = "Stop position attribute name";

    public ExoneIntroneCutterRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet inORFset = inORFs.getData();
        ExampleSet inGenomeData = inGenome.getData();

        Attribute startPosAtt = inORFset.getAttributes().get(getParameterAsString(START_POS_ATT_NAME));
        Attribute endPosAtt = inORFset.getAttributes().get(getParameterAsString(STOP_POS_ATT_NAME));

        HashMap<String, String> starts = new HashMap<String, String>();
        HashMap<String, String> ends = new HashMap<String, String>();
        HashMap<String, String> annotation = new HashMap<String, String>();
        Integer i = 0;
        for (Example example : inORFset) {
            starts.put(i.toString(), example.getValueAsString(startPosAtt));
            ends.put(i.toString(), example.getValueAsString(endPosAtt));
            annotation.put(i.toString(), example.getValueAsString(inORFset.getAttributes().get("Annotation")));
            i++;
        }
        String values[][] = null;
        for (Example example : inGenomeData) {
            String genomeAsStr = example.getValueAsString(example.getAttributes().get("Chain"));
            values = getData(genomeAsStr, starts, ends, annotation);
        }

        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);
        outSet.getAttributes().get("att1").setName("Chain");
        outSet.getAttributes().get("att2").setName("Annotation");
        outSet.getAttributes().get("att3").setName("IsGene");
        outSet.getAttributes().get("att4").setName("Start");
        outSet.getAttributes().get("att5").setName("End");
        outSet.getAttributes().get("att6").setName("Chain type");
        outORFs.deliver(outSet);
    }

    private String[][] getData(String genomeAsStr, HashMap<String, String> starts, HashMap<String, String> ends, HashMap<String, String> annotation) {
        String priveousStop = "1";
        String priveousStart = "0";
        ArrayList<String> chains = new ArrayList<String>();
        ArrayList<String> isGene = new ArrayList<String>();
        ArrayList<String> anno = new ArrayList<String>();
        ArrayList<String> startposes = new ArrayList<String>();
        ArrayList<String> endposes = new ArrayList<String>();
        for (int i = 0; i < starts.size(); i++) {
            int start = (int)Double.parseDouble(starts.get(Integer.toString(i)));
            int stop = (int)Double.parseDouble(ends.get(Integer.toString(i)));
            int priveousStopValue = (int)Double.parseDouble(priveousStop);
            int priveousStartValue = (int)Double.parseDouble(priveousStart);
            if ((priveousStartValue < priveousStopValue) && (start-priveousStopValue) > 10) {
                chains.add(genomeAsStr.substring(priveousStopValue, start));
                anno.add("Introne");
                isGene.add("0");
                startposes.add(priveousStop);
                endposes.add(Integer.toString(start));
            } else if ((priveousStartValue > priveousStopValue) && (start-priveousStartValue) > 10) {
                chains.add(genomeAsStr.substring(priveousStartValue, start));
                anno.add("Introne");
                isGene.add("0");
                startposes.add(priveousStop);
                endposes.add(Integer.toString(start));
            }
            if (start < stop) {
                chains.add(genomeAsStr.substring(start-1, stop-1));
                anno.add(annotation.get(Integer.toString(i)));
                isGene.add("1");
                startposes.add(Integer.toString(start));
                endposes.add(Integer.toString(stop));
            } else if (start > stop) {
                chains.add(genomeAsStr.substring(stop-1, start-1));
                anno.add(annotation.get(Integer.toString(i)));
                isGene.add("1c");
                startposes.add(Integer.toString(start));
                endposes.add(Integer.toString(stop));
            }
            priveousStop = Integer.toString(stop);
            priveousStart = Integer.toString(start);
        }
        String[][] result = new String[chains.size()][6];
        for (int i = 0; i < chains.size() && i < isGene.size() /*&& i < anno.size() && i < startposes.size() && i < endposes.size()*/; i++) {
            result[i][0] = chains.get(i);
            result[i][1] = anno.get(i);
            result[i][2] = isGene.get(i);
            if (i < startposes.size())
                result[i][3] = startposes.get(i);
            if (i < endposes.size())
                result[i][4] = endposes.get(i);
            if (isGene.get(i).equals("0"))
                result[i][5] = "DNA_introne";
            else if (isGene.get(i).equals("1"))
                result[i][5] = "DNA_exone";
            else if (isGene.get(i).equals("1c"))
                result[i][5] = "DNA_complement_exopne";
        }
        return result;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeAttribute(START_POS_ATT_NAME, "Start position attribute name", inORFs));
        types.add(new ParameterTypeAttribute(STOP_POS_ATT_NAME, "Stop position attribute name", inORFs));
        return types;
    }
}
