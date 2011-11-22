package libiada.DiffBuildingAnalysis;

import libiada.DiffBuildingAnalysis.Iterators.CutRuleIterator;
import libiada.DiffBuildingAnalysis.Rules.CutRule;
import libiada.IntervalAnalysis.Chain;

import javax.mail.search.StringTerm;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir
 * Date: 18.10.11
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class DifCutter {
    public ArrayList<String> cut(String chain, CutRule rule) {
        ArrayList<String> result =  new ArrayList<String>();

        rule.getIterator();
        CutRuleIterator iterator = rule.getIterator();

        while(iterator.next())
        {
           String s = chain.substring(iterator.getStartPos()-1, iterator.getStopPos());
           result.add(s);
        }
        return result;
    }
}
