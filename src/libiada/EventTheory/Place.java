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
 * @author Alex
 */
public class Place implements IBaseObject
{
    private ArrayList<IBaseObject> pDimensions = new ArrayList<IBaseObject>();
    private ArrayList<Long> pValues = new ArrayList<Long>();

    public Place(ArrayList<Dimension> dimensions) throws Exception
    {
        if (dimensions == null) throw new Exception("dimensions is null");
            if (dimensions.size() <= 0)
            {
                throw new Exception("Dimention is wrong <0");
            }
            else
            {
                for (int i = 0; i < dimensions.size(); i++)
                {
                    if (pDimensions.contains(dimensions.get(i)))
                    {
                        throw  new Exception("?????? ??????. ????? ????????????");
                    }
                    pValues.add(dimensions.get(i).getMin());
                    pDimensions.add(dimensions.get(i).Clone());
                }
            }
    }

    public ArrayList<IBaseObject> getDimension() {
        return pDimensions;
    }

    public IBaseObject getDimension(int index) {
        return  pDimensions.get(index);
    }

    public long[] getValues() {
        return new long[1];
    }

    public void setValue(long value, int index) throws Exception
    {
        if (value < (((Dimension)pDimensions.get(index)).getMin()) || value > (((Dimension)pDimensions.get(index)).getMax()))
        {
            throw new Exception("Trying to set the value outside the domain of");
        }
        pValues.set(index, value);
    }

    public Place setValues(long[] value) throws Exception
    {
        if (Array.getLength(value) != pDimensions.size())
        {
            throw new Exception("Dimensoin sizes is not equals");
        }

        for (int i = 0; i < pDimensions.size(); i++)
        {
            setValue(value[i], i);
        }

        return this;
    }

    public IBaseObject Clone() {
        return null;  //TODO: "????????? ?????"
    }

    public boolean Equals(Object obj) {
        return false;  //TODO: "????????? ?????"
    }

    public IBin GetBin() {
        return null;  //TODO: "????????? ?????"
    }

    public boolean compatibleTo(Place pattern) throws Exception {
        if (pattern == null)
        {
            throw new Exception("Place is null");
        }
        if (this == pattern)
        {
            return true;
        }

        if (pattern.getCount() != getCount())
        {
            return false;
        }

        for (int i = 0; i < pattern.getCount(); i++)
        {
            if (!((Dimension)pattern.getDimension(i)).equalsAsDimension((Dimension)pDimensions.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public int getCount() throws Exception
    {
        if (pDimensions.size() != pValues.size())
        {
            throw new Exception("Error of data integrity");
        }
        return pDimensions.size();
    }

    public long getValue(int index) throws Exception
    {
        if (index >= pDimensions.size() || index < 0)
        {
            throw new Exception("Trying to get the value is not an existing demension");
        }
        return pValues.get(index);
    }
}
