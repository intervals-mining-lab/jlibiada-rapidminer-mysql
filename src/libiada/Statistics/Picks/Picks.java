package libiada.Statistics.Picks;

import libiada.Statistics.Picks.Calculators.PicksCalculator.IPicksCalculator;
import libiada.Statistics.Picks.Calculators.ValueCalculator.IValueCalculator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.03.11
 * Time: 3:12
 */
public class Picks implements Iterator<Double> {
    private int currentIndex = 0;
    private ArrayList<Double> propabilities = new ArrayList<Double>();

    @Override
    public boolean hasNext() {
        return currentIndex < propabilities.size();
    }

    @Override
    public Double next() {
        Double value = propabilities.get(currentIndex);
        ++currentIndex;
        return value;
    }

    @Override
    public void remove() {
        propabilities.remove(currentIndex);
    }

    public void add(Double value) {
        propabilities.add(value);
    }

    public void resetIterator() {
        currentIndex = 0;
    }

    public void sort() {
        for (int i = 0; i < propabilities.size() - 1; i++) {
            if (propabilities.get(i) > propabilities.get(i + 1)) {
                Double tempValue = propabilities.get(i + 1);
                propabilities.set(i + 1, propabilities.get(i));
                propabilities.set(i, tempValue);
                i = 0;
            }
        }
    }


    public int getIndex() {
        return currentIndex;
    }

    /**
     * Вычисляет хврактеристику выборки в виде другой выборки (например распределение)
     * @param calculator Калькулятор
     * @return Возвращают параметр-выборку вычисляемой выборки
     */
    public Picks calculatePicks(IPicksCalculator calculator) {
        return calculator.calculate(this);
    }

    public double calculateCharacteristic(IValueCalculator calculator) {
        return calculator.calculate(this);
    }

    public int size() {
        return propabilities.size();
    }
}
