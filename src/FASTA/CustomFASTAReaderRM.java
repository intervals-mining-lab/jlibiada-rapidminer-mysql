package rapidminer.Bioinformatics.FASTA;

import Bio.DNARegion;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeFile;
import org.biojava3.core.sequence.DNASequence;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 24.07.11
 * Time: 0:25
 */
public class CustomFASTAReaderRM extends AbstractReader<ExampleSet> {
    public static final String PARAMETER_FASTA_FILE_NAME = "file_name";

    public CustomFASTAReaderRM(OperatorDescription od) {
        super(od, ExampleSet.class);
    }

    @Override
    public ExampleSet read() throws OperatorException {
        LinkedHashMap<String, DNARegion> sequens = null;
        try {
            sequens = Bio.FastaReader.readFastaDNASequence(getParameterAsFile(PARAMETER_FASTA_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String data[][] = new String[0][];
        if (sequens != null) {
            data = new String[sequens.size()][5];
            int i = 0;
            for (Map.Entry<String, DNARegion> entry : sequens.entrySet()) {
                data[i][0] = entry.getValue().getAnotation();
                data[i][1] = entry.getValue().getSequence();
                data[i][2] = Integer.toString(entry.getValue().getStartPos());
                data[i][3] = Integer.toString(entry.getValue().getEndPos());
                data[i][4] = Boolean.toString(entry.getValue().getIsComplement());
                i++;
            }
        }

        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("Annotation");
        outSet.getAttributes().get("att2").setName("Chain");
        outSet.getAttributes().get("att3").setName("Start position");
        outSet.getAttributes().get("att4").setName("Stop position");
        outSet.getAttributes().get("att5").setName("Is complement");
        return outSet;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeFile(PARAMETER_FASTA_FILE_NAME, "File name", "faa", false));
        return types;
    }
}
