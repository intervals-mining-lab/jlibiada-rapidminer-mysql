package libiada.IntervalAnalysis.Characteristics.AuxiliaryInterfaces;

import libiada.Statistics.FrequencyList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 11.12.2010
 * Time: 1:43:35
 * To change this template use File | Settings | File Templates.
 */
public interface IDataForCalculator {
    FrequencyList getCommonIntervals() throws Exception;
    FrequencyList getStartInterval() throws Exception;
    FrequencyList getEndInterval() throws Exception;
}
