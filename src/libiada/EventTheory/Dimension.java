package libiada.EventTheory;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 17.11.2010
 * Time: 23:43:24
 * To change this template use File | Settings | File Templates.
 */

public class Dimension {
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
//                Debug.WriteLine ("-------------------------------------------------------------------------");
//                Debug.WriteLine("Warning: " + (GetType()) + " creating min > max");
//                Debug.WriteLine("Предупреждение: " + (GetType()) + " при создании min > max");
//                Debug.WriteLine("-------------------------------------------------------------------------");
                pmin = max;
                pmax = min;
            }
    }

    public Dimension(DimensionBin bin) {
        pmin = bin.min();
        pmax = bin.max();
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
        return 0;
    }

    public long getMin()
    {
        return 0;
    }

    public long getLength()
    {
        return 0;
    }

    public Dimension Clone() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
