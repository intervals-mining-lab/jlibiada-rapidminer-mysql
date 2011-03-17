package libiada.EventTheory;

import libiada.Root.IBaseObject;
import libiada.TheoryOfSet.Alphabet;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Алексей
 * Date: 30.11.2010
 * Time: 9:13:07
 */
public class Space {
    private ArrayList<Dimension> pDimensions = new ArrayList<Dimension>();
    private boolean WasChange = false;
    private int ElementsCount = 0;
    protected ArrayList<Long> vault = new ArrayList<Long>();

    public Space() throws Exception {
        pAlphabet.add(PsevdoValue.Instance());
    }

    public void addItem(IBaseObject value, Place place) throws Exception {
        if (value == null)
        {
            throw new Exception("Попытка добавить нулевой объект к пространству");
        }

        if (!place.compatibleTo(getPlacePattern()))
        {
            throw new Exception("Place does not belong to space");
        }

        int Index = pointToPosition(place);
        if ((Index < 0) || (Index >= Long.MAX_VALUE))
        {
            throw new Exception("Place does not belong to space");
        }

        removeAt(place);
        long pos = pAlphabet.indexOf(value);
        if (-1 == pos)
        {
            pAlphabet.add(value);
            pos = pAlphabet.getPower() - 1;
        }

        vault.set(pointToPosition(place), pos);
        WasChange = true;
    }

    public int getPlaceCount() {
        return vault.size();  //To change body of created methods use File | Settings | File Templates.
    }

    public void removeAt(Place place) throws Exception {
        long pos = pointToPosition(place);
        if ((pos < 0) || (pos >= Long.MAX_VALUE))
        {
            throw new Exception("Place does not belong to space");
        }
        long temp = vault.get(pointToPosition(place));        //TODO:
        vault.set(pointToPosition(place), (long)0);
        if (-1 == vault.indexOf(temp))
        {
            pAlphabet.remove((int) temp);
            for (int i = 0; i < vault.size(); i++)
            {
                if (vault.get(i) > temp)
                {
                    vault.add(i, vault.get(i)-1);   //vault[i]--
                }
            }
        }
        WasChange = true;
    }

    private int pointToPosition(Place place) throws Exception {
        int position = 0;
        for (int i = 0; i < pDimensions.size() - 1; i++)
        {
            int product = 1;
            for (int j = 0; j < i - 1; j++)
            {
                product *= pDimensions.get(j).getLength();
            }
            position += product*(place.getValue(i) - pDimensions.get(i).getMin());
        }
        position += place.getValue(place.getCount() - 1);
        return position;
    }

    protected Alphabet pAlphabet = new Alphabet();

    public Place getPlacePattern() throws Exception {
        return new Place(pDimensions);
     }

    public void addDimension(Dimension dimension)
    {
        pDimensions.add(dimension);
        WasChange = true;
        createSpace();
        if (1 == pDimensions.size())
        {
            ElementsCount = (int) (((Dimension) pDimensions.get(0)).getMax() - ((Dimension) pDimensions.get(0)).getMin());
        }
        else
        {
            ElementsCount = ElementsCount*(int) (((Dimension) pDimensions.get(0)).getMax() - ((Dimension) pDimensions.get(0)).getMin());
        }
    }

    protected void deleteDimentions() throws Exception {
        pDimensions.clear();
        pAlphabet = new Alphabet();
        pAlphabet.add(PsevdoValue.Instance());
        WasChange = true;
    }

    private void createSpace() {
        ArrayList<Long> temp = null;
        temp = createTemp(temp);
        allocSpace();
        refillSpace(temp);
    }

    private ArrayList<Long> createTemp(ArrayList<Long> temp)
    {
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

    private void refillSpace(ArrayList<Long> temp)
    {
        //TODO: "������� �������������� ������������"
//        if (temp != null)
//            {
//                Array.Copy(temp, vault, temp.GetLength(0));
//            }
    }

    private void allocSpace()
    {
        int length = 1;
        for (int i = 0; i < pDimensions.size(); i++)
        {
            length *= (pDimensions.get(i).getMax() - pDimensions.get(i).getMin()) + 1;
        }
        vault = new ArrayList<Long>(length);
        for (int i = 0; i < length; i++)
        {
            vault.add(i, (long)0);
        }
    }

    public Alphabet getAlpahbet() {
        return pAlphabet;
    }
}