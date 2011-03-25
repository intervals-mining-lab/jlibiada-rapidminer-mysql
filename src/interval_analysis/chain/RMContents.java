package interval_analysis.chain;

import com.rapidminer.operator.Annotations;
import com.rapidminer.operator.IOObject;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.ProcessingStep;
import com.rapidminer.tools.LoggingHandler;
import libiada.IntervalAnalysis.Buildings.Contents;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 3:50
 */
public class RMContents extends Contents implements IOObject {
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
}
