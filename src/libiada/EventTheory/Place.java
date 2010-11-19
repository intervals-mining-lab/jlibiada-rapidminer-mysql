package libiada.EventTheory;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алексе
 */
public class Place {
    private ArrayList<Dimension> pDimensions;

    public Place(ArrayList<Dimension> dimensions) {
    }

    public ArrayList<Dimension> getDimension() {
        return pDimensions;
    }

    public long[] getValues() {
        return new long[1];
    }

    public void setValue(int value, int index) {
    }
}
