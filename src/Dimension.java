/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Алексей
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

    Dimension(DimensionBin bin) {
        pmin = bin.min();
        pmax = bin.max();
    }

    boolean EqualsAsDimension(Dimension obj) {
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

    long getMax()
    {
        return 0;
    }

    long getMin()
    {
        return 0;
    }

    long getLength()
    {
        return 0;
    }

    Dimension Clone() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
