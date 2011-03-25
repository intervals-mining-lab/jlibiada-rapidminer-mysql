package interval_analysis.chain;

import com.rapidminer.operator.IOObject;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.io.AbstractReader;
import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeInt;
import libiada.Root.SimpleTypes.ValueInt;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 4:26
 */
public class PeriodicChainContentsBuilderRM extends AbstractReader<RMContents> {
    public static final String PARAMETER_CHAIN_LENGTH = "chain_length";
    public static final String PARAMETER_ALPHABET_POWER = "alphabet_power";

    public PeriodicChainContentsBuilderRM(OperatorDescription od) {
        super(od, RMContents.class);
    }

    @Override
    public RMContents read() throws OperatorException {
        RMContents contents = new RMContents();
        int count = Math.round(getParameterAsInt(PARAMETER_CHAIN_LENGTH) / getParameterAsInt(PARAMETER_ALPHABET_POWER));
        for (int power = 1; power <= getParameterAsInt(PARAMETER_ALPHABET_POWER); power++) {
            try {
                contents.add(new ValueInt(power), count);
            } catch (Exception e) {
                System.err.print("Ошибка добавления компонента в состав");
            }
        }
        return contents;
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeInt(PARAMETER_CHAIN_LENGTH, "Chain length", 1, 100));
        types.add(new ParameterTypeInt(PARAMETER_ALPHABET_POWER, "Alphabet power", 1, 100));
        return types;
    }
}
