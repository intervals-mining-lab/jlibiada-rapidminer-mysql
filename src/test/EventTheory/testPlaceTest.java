package test.EventTheory;

import junit.framework.TestCase;
import libiada.EventTheory.Dimension;
import libiada.EventTheory.Place;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: јлексе
 * Date: 18.11.2010
 * Time: 0:27:58
 * To change this template use File | Settings | File Templates.
 */
public class testPlaceTest extends TestCase {
    @Test
    //“естирует конструктор если передано null пространство
    public void testConstructorNull()
    {
        try
        {
            new Place(null);
            fail();
        }
        catch (Exception e)
        {
            return;
        }
    }

    @Test
    //“естирует конструктор если передано пустое пространство
    public void testConstructorFreeSpace()
    {
        try
        {
            new Place(new ArrayList<Dimension>());
            fail();
        }
        catch (Exception e)
        {
            return;
        }
    }

    @Test
    //“естирует конструктор если передаем протранство содержащее одно и тоже измерение > 1 раза
    public void testConstructorBrokenSpace()
    {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Dimension Ds = new Dimension(0, 10);
        Ar.add(new Dimension(-50, -48));
        Ar.add(Ds);
        Ar.add(Ds);
        Ar.add(new Dimension(-2, 10));
        try
        {
            new Place(Ar);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    //“естирует конструктор и передачу второго измерени€ > первого
    public void testConstructorFirstDimensionLess() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(-50, -48));
        Ar.add(new Dimension(-2, 10));
        Place pl = new Place(Ar);
        assertTrue((Ar.get(0)).equalsAsDimension((Dimension)pl.getDimension().get(0)));
        assertTrue((Ar.get(1)).equalsAsDimension((Dimension)pl.getDimension().get(1)));
    }

    @Test
    //“естирует конструктор и передачу второго измерени€ < первого
    public void testConstructorSecondDimensionLess() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(-50, 150));
        Ar.add(new Dimension(-2, 10));
        Place pl = new Place(Ar);
        assertTrue((Ar.get(0)).equalsAsDimension((Dimension)pl.getDimension().get(0)));
        assertTrue((Ar.get(1)).equalsAsDimension((Dimension)pl.getDimension().get(1)));
    }

    @Test
    //“естирует конструктор и передачу второго измерени€ равного первому
    public void testConstructorDimensionsEquals() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 150));
        Ar.add(new Dimension(0, 150));
        Place pl = new Place(Ar);
        assertTrue((Ar.get(0)).equalsAsDimension((Dimension)pl.getDimension().get(0)));
        assertTrue((Ar.get(1)).equalsAsDimension((Dimension)pl.getDimension().get(1)));
    }

    @Test
    //“естирует если передаем не пространство(один из элеметов не измерение)
    public void testConstructorNotAllAreDimension()
    {
        ArrayList Ar = new ArrayList();
        Ar.add(new Dimension(-50, 100));
        Ar.add(new Object());
        Ar.add(new Dimension(-2, 10));
        Place Pl = null;
        try
        {
           Pl = new Place(Ar);
           fail();
        }
        catch (Exception e)
        {
            if (Pl == null) {
                return;
            }
            assertEquals(Pl.getDimension().size(), Array.getLength(Pl.getValues()));
        }
    }

    @Test
    //“естирует ситуацию когда список заданый при создании Place изменитс€
    public void testtestChangeSpaceAfterCreateSpace() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 150));
        Place Pl = new Place(Ar);
        Ar.add(new Dimension(0, 150));
        Ar.add(new Dimension(0, 150));
        Ar.add(new Dimension(0, 150));
        assertEquals(1, Array.getLength(Pl.getDimension()));
        assertEquals(1, Array.getLength(Pl.getValues()));
    }

    @Test
    //“естирует ситуацию когда пытаемс€ установит знаение измерению имеющему индекс больше чем размерность
     public void testSetValueIndexGreateThanMax() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(10, 3);
            fail();
        }
        catch (Exception e)
        {
            return;
        }
     }

     @Test
    //“естирует ситуацию когда пытаемс€ установит знаение измерению имеющему индекс меньше 0
    public void testSetValueIndexLessThanZero() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(10, -3);
            fail();
        }
        catch (Exception e)
        {
            return;
        }
    }

    @Test
    //“естирует ситуацию когда пытаемс€ установит знаение меньше минимальной границы на которой определено измерение
    public void testSetValueLessThanMin() throws Exception {
        ArrayList Ar = new ArrayList();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
           Pl.setValue(-10, 0);
           fail();
        }
        catch (Exception e)
        {
           return;
        }
    }

    @Test
    // “естирует ситуацию когда пытаемс€ установит знаение больше максимальной границы на которой определено измерение
    public void testSetValueGreateThanMax() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(100, 0);
            fail();
        }
        catch (Exception e)
        {
            return;
           
        }
    }

    // “естирует ситуацию когда пытаемс€ установит знаение равное максимальной границе на которой определено измерение
    @Test
    public void testSetValueEqualsMax() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(10, 0);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    // “естирует ситуацию когда пытаемс€ установит знаение равное минимельной границе на которой определено измерение
    @Test
    public void testSetValueEqualsMin() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(0, 0);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    // “естирует установку в рабочем режиме
    @Test
    public void TestSetValueInWorkMode() throws Exception {
        ArrayList<Dimension> Ar = new ArrayList<Dimension>();
        Ar.add(new Dimension(0, 10));
        Place Pl = new Place(Ar);
        try
        {
            Pl.setValue(5, 0);
        }
        catch (Exception e)
        {
            fail();
        }
    }
}