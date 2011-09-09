package rapidminer.Bioinformatics;

import Bio.ORMFinder;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeFile;
import com.rapidminer.parameter.ParameterTypeInt;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.compound.DNACompoundSet;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.io.DNASequenceCreator;
import org.biojava3.core.sequence.io.FastaReader;
import org.biojava3.core.sequence.io.GenericFastaHeaderParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 27.04.11
 * Time: 2:23
 */
public class ORMFinderRM extends AbstractReader<ExampleSet> {
    private static final String PARAMETER_FASTA_NAME = "File_name: ";
    private static final String PARAMETER_ORF_MIN_LENGTH = "ORF min length: ";

    public ORMFinderRM(OperatorDescription od) {
        super(od, ExampleSet.class);
    }

    @Override
    public ExampleSet read() throws OperatorException {
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(getParameterAsFile(PARAMETER_FASTA_NAME));
        } catch (FileNotFoundException e) {
            //TODO: "Fill"
        }
        FastaReader<DNASequence, NucleotideCompound> fastaReader =
			new FastaReader<DNASequence, NucleotideCompound>(
					inStream,
					new GenericFastaHeaderParser<DNASequence,NucleotideCompound>(),
					new DNASequenceCreator(DNACompoundSet.getDNACompoundSet()));
        LinkedHashMap<String, DNASequence> genome = null;
        try {
            genome = fastaReader.process();
        } catch (Exception e) {
            //TODO: "Fill"
        }

        ORMFinder finder = new ORMFinder();
        ArrayList<DNASequence> startTrip = new ArrayList<DNASequence>();
        startTrip.add(new DNASequence("ATG"));    //+
        startTrip.add(new DNASequence("TTG"));    //+
        startTrip.add(new DNASequence("CTG"));    //+
        startTrip.add(new DNASequence("ATT"));    //+
        startTrip.add(new DNASequence("ATC"));    //--
        startTrip.add(new DNASequence("ATA"));    //--
        startTrip.add(new DNASequence("GTG"));    //+
        ArrayList<DNASequence> stopTrip = new ArrayList<DNASequence>();
        stopTrip.add(new DNASequence("TAA"));
        stopTrip.add(new DNASequence("TAG"));
        stopTrip.add(new DNASequence("TGA"));
        ArrayList<String> orfs = null;
        ArrayList<String> starts = null;
        ArrayList<String> stops = null;
        for ( Map.Entry<String, DNASequence> entry : genome.entrySet()) {
            orfs = finder.find(entry.getValue(), getParameterAsInt(PARAMETER_ORF_MIN_LENGTH), startTrip, stopTrip);
            starts = finder.getStarts();
            stops = finder.getStops();
        }


        String data[][] = null;
        if (orfs != null) {
            data = new String[orfs.size()][4];
        }
        for (int i = 0; (i < orfs.size()) && (i < starts.size()) && (i < stops.size()); i++) {
            int annotationEnd = orfs.get(i).indexOf(")");
            String currentAnotation = orfs.get(i).substring(0, annotationEnd+1);
            String currentOrf = orfs.get(i).substring(annotationEnd+2, orfs.get(i).length()-1);
            data[i][0] = currentAnotation;
            data[i][1] = currentOrf;
            data[i][2] = starts.get(i).toString();
            data[i][3] = stops.get(i).toString();
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("Annotation");
        outSet.getAttributes().get("att2").setName("Chain");
        outSet.getAttributes().get("att3").setName("Start position");
        outSet.getAttributes().get("att4").setName("Stop position");
        return outSet;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeFile(PARAMETER_FASTA_NAME, "File name", "faa | fna", false));
        types.add(new ParameterTypeInt(PARAMETER_ORF_MIN_LENGTH, "ORF min length", 0, 1000000));
        return types;
    }
}
