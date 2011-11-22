package libiada.DiffBuildingAnalysis.Iterators;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir
 * Date: 18.10.11
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class CutRuleIterator {
    private ArrayList<Integer> starts;
    private ArrayList<Integer> stops;
    private int i = -1;

    public CutRuleIterator(ArrayList<Integer> starts, ArrayList<Integer> stops) {
        this.starts = starts;   //храним начальные позиции
        this.stops = stops;     //храним конечные позиции

    }

    public boolean next() {
        i++;
        return (starts.size() > i) && (stops.size() > i);
    }

    public int getStartPos() {
        return starts.get(i);
    }

    public int getStopPos() {
        return stops.get(i);
    }
}
