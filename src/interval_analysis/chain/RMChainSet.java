package interval_analysis.chain;

import com.rapidminer.operator.Annotations;
import com.rapidminer.operator.IOObject;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.ProcessingStep;
import com.rapidminer.tools.LoggingHandler;
import libiada.IntervalAnalysis.Chain;
import libiada.IntervalAnalysis.ChainWithCharacteristic;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: $Alex Semenov
 * Date: 2/23/11
 * Time: 6:34 PM
 */
public class RMChainSet implements IOObject {
    private ArrayList<Chain> chains = new ArrayList<Chain>();

    @Override
    public void setSource(String s) {
        //TODO: "Заполнить метод"
    }

    @Override
    public String getSource() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public void appendOperatorToHistory(Operator operator, OutputPort outputPort) {
        //TODO: "Заполнить метод"
    }

    @Override
    public List<ProcessingStep> getProcessingHistory() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public IOObject copy() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        //TODO: "Заполнить метод"
    }

    @Override
    public LoggingHandler getLog() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public void setLoggingHandler(LoggingHandler loggingHandler) {
        //TODO: "Заполнить метод"
    }

    @Override
    public Annotations getAnnotations() {
        return null;  //TODO: "Заполнить метод"
    }

    public void addChains(ArrayList<Chain> chains) {
        this.chains.addAll(chains);
    }

    public int getCount() {
        return chains.size();
    }

    public ChainWithCharacteristic get(int i) {
        return chains.get(i);
    }
}
