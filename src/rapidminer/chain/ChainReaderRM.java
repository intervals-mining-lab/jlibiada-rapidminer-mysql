package rapidminer.chain;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeFile;
import com.rapidminer.parameter.UndefinedParameterError;

import java.io.*;
import java.nio.CharBuffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/12/11
 * Time: 3:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChainReaderRM extends AbstractReader<ExampleSet> {
    public static final String PARAMETER_MODEL_FILE = "chain_file";

    public ChainReaderRM(OperatorDescription od) {
        super(od, ExampleSet.class);
    }

    @Override
    public ExampleSet read() throws UndefinedParameterError {
        String data[][] = new String[1][1];
        try {
            data[0][0] = readFile();
        } catch (IOException e) {
            System.err.print("File reading error");
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(data);
        outSet.getAttributes().get("att1").setName("Chain");
        return outSet;
    }

    private String readFile() throws UndefinedParameterError, IOException {
        BufferedReader in = new BufferedReader(new FileReader(getParameterAsString(PARAMETER_MODEL_FILE)));
        String s = "";
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeFile(PARAMETER_MODEL_FILE, "Filename containing the chain to load.", "mod", false));
        return types;
    }
}
