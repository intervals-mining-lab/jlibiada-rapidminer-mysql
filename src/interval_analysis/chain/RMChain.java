package interval_analysis.chain;

import com.rapidminer.operator.Annotations;
import com.rapidminer.operator.IOObject;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.ProcessingStep;
import com.rapidminer.tools.LoggingHandler;
import libiada.IntervalAnalysis.Chain;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/12/11
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMChain extends Chain implements IOObject {
    public RMChain(int length) throws Exception {
        super(length);
    }

    public RMChain(String s) throws Exception {
        super(s);
    }

    @Override
    public void setSource(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getSource() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void appendOperatorToHistory(Operator operator, OutputPort outputPort) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ProcessingStep> getProcessingHistory() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IOObject copy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LoggingHandler getLog() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLoggingHandler(LoggingHandler loggingHandler) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Annotations getAnnotations() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
