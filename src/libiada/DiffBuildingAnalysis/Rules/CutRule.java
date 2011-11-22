package libiada.DiffBuildingAnalysis.Rules;

import libiada.DiffBuildingAnalysis.Iterators.CutRuleIterator;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir
 * Date: 18.10.11
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class CutRule {
    protected ArrayList<Integer> starts = new ArrayList<Integer>();
    protected ArrayList<Integer> stops = new ArrayList<Integer>();

    public abstract CutRuleIterator getIterator();
}
