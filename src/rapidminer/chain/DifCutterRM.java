package rapidminer.chain;

import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.InputPorts;
import com.rapidminer.operator.ports.OutputPort;
import libiada.DiffBuildingAnalysis.DifCutter;
import libiada.DiffBuildingAnalysis.Iterators.CutRuleIterator;
import libiada.DiffBuildingAnalysis.Rules.CutRule;
import libiada.DiffBuildingAnalysis.Rules.FromFixStartCutRule;

import javax.management.OperationsException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir
 * Date: 16.11.11
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class DifCutterRM extends Operator {
     protected OutputPort outChains = getOutputPorts().createPort("Chains");
     protected InputPort inChain = getInputPorts().createPort("Chain");

     public DifCutterRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() throws OperatorException {
        ExampleSet examples = inChain.getData();  //со входного порта считали информацицию, получили таблицу
        Example example = examples.getExample(0); //получили строчку таблицы данных
        String s = example.getValueAsString(examples.getAttributes().get("Chain"));
        DifCutter dif = new DifCutter();   //рубит строчку
        FromFixStartCutRule rule = new FromFixStartCutRule(s.length(), 100);  //првило разбиения
        ArrayList<String> cuts =  dif.cut(s, rule);  //метод разрубающий строчку

        String[][] values = new String[cuts.size()][1];

        for (int i = 0; i<cuts.size(); i++){
          values[i][0] = cuts.get(i);
        }
        ExampleSet outSet = ExampleSetFactory.createExampleSet(values);
        outChains.deliver(outSet);
    }
}
