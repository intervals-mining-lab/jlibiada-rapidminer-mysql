package interval_analysis.chain;

import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
public class ChainReaderRM extends AbstractReader<RMChain> {
    public static final String PARAMETER_MODEL_FILE = "chain_file";

    public ChainReaderRM(OperatorDescription od) {
        super(od, RMChain.class);
    }

    @Override
    public RMChain read() throws OperatorException {
        Reader reader = null;
        try {
            reader = new FileReader(getParameterAsString(PARAMETER_MODEL_FILE));
            reader.read();
            return new RMChain("123412521251234");
        } catch (Exception ex) {
            throw new OperatorException("Chain loading error.", ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ChainReaderRM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeFile(PARAMETER_MODEL_FILE, "Filename containing the chain to load.", "mod", false));
        return types;
    }
}
