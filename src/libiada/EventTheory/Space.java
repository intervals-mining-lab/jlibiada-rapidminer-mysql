package libiada.EventTheory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 30.11.2010
 * Time: 9:13:07
 * To change this template use File | Settings | File Templates.
 */
public class Space {
    private ArrayList<Dimension> pDimentions;
    private boolean WasChange = false;
    private int ElementsCount = 0;
    private long[] vault;

    public Place getPlacePattern() {
        return new Place(pDimentions);
     }

    public void addDimension(Dimension dimension)
        {
            pDimentions.add(dimension);
            WasChange = true;
            CreateSpace();
            if (1 == pDimentions.size())
            {
                ElementsCount = (int) (((Dimension) pDimentions.get(0)).getMax() - ((Dimension) pDimentions.get(0)).getMin());
            }
            else
            {
                ElementsCount = ElementsCount*(int) (((Dimension) pDimentions.get(0)).getMax() - ((Dimension) pDimentions.get(0)).getMin());
            }
        }

    private void CreateSpace() {
            long[] temp = new long[0];
            temp = CreateTemp(temp);
            AllocSpace();
            ReFillSpace(temp);
    }

    private long[] CreateTemp(long[] temp) {
       if (vault != null)
       {
            temp = vault;
       }
       else
       {
            temp = null;
       }
        return temp;
    }

    private void ReFillSpace(long[] temp)
    {
//        if (temp != null)
//            {
//                Array.Copy(temp, vault, temp.GetLength(0));
//            }
    }

    private void AllocSpace()
    {
        long length = 1;
        foreach (Dimension dimension in pDimentions)
        {
            length *= (Dimension.max - Dimension.min) + 1;
        }
        vault = new long[length];
    }
}