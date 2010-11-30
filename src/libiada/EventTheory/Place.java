package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алексе
 */
public class Place implements IBaseObject
{
    private ArrayList<Dimension> pDimensions;

    public Place(ArrayList<Dimension> dimensions) {
    }

    public ArrayList<Dimension> getDimension() {
        return pDimensions;
    }

    public long[] getValues() {
        return new long[1];
    }

    public void setValue(long value, int index) {
    }

    public Place setValues(long[] value) throws Exception {
        if (Array.getLength(value) != pDimensions.size())
            {
                throw new Exception("Размерности не совпадают");
            }

            for (int i = 0; i < pDimensions.size(); i++)
            {
                setValue(value[i], i);
            }

            return this;
    }

    public IBaseObject Clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean Equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IBin GetBin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
