package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.Root.IBin;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 17.11.2010
 * Time: 23:43:24
 * To change this template use File | Settings | File Templates.
 */

public class Dimension implements IBaseObject {
    private long pmin;
    private long pmax;
    
    public Dimension(long min,  long max) {
        if (min < max)
            {
                pmax = max;
                pmin = min;
            }
            else
            {
                System.out.print("-------------------------------------------------------------------------");
                System.out.print("Warning: " + this.getClass() + " creating min > max");
                System.out.print("Предупреждение: " + this.getClass() + " при создании min > max");
                System.out.print("-------------------------------------------------------------------------");
                pmin = max;
                pmax = min;
            }
    }

    public Dimension(DimensionBin bin) {
        pmin = bin.getMin();
        pmax = bin.getMax();
    }

    public boolean EqualsAsDimension(Dimension obj) {
        if (null == obj)
        {
            return false;
        }

        if (obj.pmax == pmax && obj.pmin == pmin)
        {
            return true;
        }
        return false;
    }

    public long getMax()
    {
        return pmax;
    }

    public long getMin()
    {
        return pmin;
    }

    public long getLength()
    {
        int dt = 0;
        if (pmin <= 0 && pmax >= 0)
        {
            dt = 1;
        }
        return (int) (pmax - pmin) + dt;
    }

    public IBaseObject Clone() {
        return new Dimension(pmin, pmax);
    }

    public boolean Equals(Object obj) {
            if (this == obj)
            {
                return true;
            }
            return false;
    }

    public IBin GetBin() {
            DimensionBin Temp = new DimensionBin();
            Temp.setMin(pmin);
            Temp.setMax(pmax);
            return Temp;
    }
}
