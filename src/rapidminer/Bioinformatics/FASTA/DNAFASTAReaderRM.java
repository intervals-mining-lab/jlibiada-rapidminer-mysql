package rapidminer.Bioinformatics.FASTA;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeFile;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.ProteinSequence;
import org.biojava3.core.sequence.compound.AminoAcidCompound;
import org.biojava3.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava3.core.sequence.compound.DNACompoundSet;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.io.DNASequenceCreator;
import org.biojava3.core.sequence.io.FastaReader;
import org.biojava3.core.sequence.io.GenericFastaHeaderParser;
import org.biojava3.core.sequence.io.ProteinSequenceCreator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 22.07.11
 * Time: 22:45
 */
public class DNAFASTAReaderRM extends AbstractReader<ExampleSet> {
    public static final String PARAMETER_FASTA_FILE_NAME = "file_name";

    public DNAFASTAReaderRM(OperatorDescription od) {
        super(od, ExampleSet.class);
    }

    @Override
    public ExampleSet read() throws OperatorException {
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(getParameterAsFile(PARAMETER_FASTA_FILE_NAME));
        } catch (FileNotFoundException e) {
            //TODO: "Fill"
        }
        FastaReader<DNASequence,NucleotideCompound> fastaReader =
			new FastaReader<DNASequence,NucleotideCompound>(
					inStream,
					new GenericFastaHeaderParser<DNASequence, NucleotideCompound>(),
					new DNASequenceCreator(DNACompoundSet.getDNACompoundSet()));
        LinkedHashMap<String, DNASequence> b = null;
        try {
            b = fastaReader.process();
        } catch (Exception e) {
            //TODO: "Fill"
        }
        String data[][] = new String[0][];
        if (b != null) {
            data = new String[b.size()][2];
            int i = 0;
            for ( Map.Entry<String, DNASequence> entry : b.entrySet()) {
                data[i][0] = entry.getValue().getOriginalHeader();
                data[i][1] = entry.getValue().getSequenceAsString();
                i++;
            }
        }

        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("DNA name");
        outSet.getAttributes().get("att2").setName("Chain");
        return outSet;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeFile(PARAMETER_FASTA_FILE_NAME, "File name", "faa", false));
        return types;
    }
}
