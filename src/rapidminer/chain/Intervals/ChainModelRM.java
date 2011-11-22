package rapidminer.chain.Intervals;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.set.HeaderExampleSet;
import com.rapidminer.operator.*;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.ProcessingStep;
import com.rapidminer.tools.LoggingHandler;
import libiada.FastChainAlgorithms.FastChain.FastChain;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 21.10.11
 * Time: 0:29
 */
public class ChainModelRM extends FastChain implements Model {
    public ChainModelRM(String chain) throws Exception {
        super(chain);
    }

    @Override
    public HeaderExampleSet getTrainingHeader() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public ExampleSet apply(ExampleSet exampleSet) throws OperatorException {
        return null;  //TODO:"Write method!"
    }

    @Override
    public void setParameter(String s, Object o) throws OperatorException {
        //TODO:"Write method!"
    }

    @Override
    public boolean isUpdatable() {
        return false;  //TODO:"Write method!"
    }

    @Override
    public void updateModel(ExampleSet exampleSet) throws OperatorException {
        //TODO:"Write method!"
    }

    @Override
    public String getName() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public String toResultString() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public Icon getResultIcon() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public List getActions() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public void setSource(String s) {
        //TODO:"Write method!"
    }

    @Override
    public String getSource() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public void appendOperatorToHistory(Operator operator, OutputPort outputPort) {
        //TODO:"Write method!"
    }

    @Override
    public List<ProcessingStep> getProcessingHistory() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public IOObject copy() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        //TODO:"Write method!"
    }

    @Override
    public LoggingHandler getLog() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public void setLoggingHandler(LoggingHandler loggingHandler) {
        //TODO:"Write method!"
    }

    @Override
    public Annotations getAnnotations() {
        return null;  //TODO:"Write method!"
    }

    @Override
    public boolean isInTargetEncoding() {
        return false;  //TODO:"Write method!"
    }
}
