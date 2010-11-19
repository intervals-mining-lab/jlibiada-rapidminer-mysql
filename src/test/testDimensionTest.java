package test;

import junit.framework.TestCase;
import libiada.EventTheory.Dimension;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Алексе
 * Date: 17.11.2010
 * Time: 23:43:24
 * To change this template use File | Settings | File Templates.
 */
public class testDimensionTest extends TestCase {
    //Тест проверяющий корректность отношения эквивалентности
    @Test
    public void testEquals()
    {
        Dimension d1 = new Dimension(0, 10);
        Dimension d2 = new Dimension(0, 10);
        Dimension d3 = new Dimension(-10, 0);
        assertTrue(d1.EqualsAsDimension(d2));
        assertFalse(d1.EqualsAsDimension(d3));
        assertTrue(d1.EqualsAsDimension(d1));
    }

    //Тест проверяющий корректность конструктора
    @Test
    public void testConstructor()
    {
        Dimension d1 = new Dimension(-120, 50);
        Dimension d2 = new Dimension(null);
        Dimension d3 = new Dimension(0, 10);
        Dimension d4 = new Dimension(10, 0);
        assertTrue(d3.EqualsAsDimension(d4));
        assertFalse(d1.EqualsAsDimension(d2));
    }

    //Тест проверяющий корректность отношения порядка
    @Test
    public void testCom()
    {
        Dimension d = new Dimension(0, 12);
        assertFalse(d.EqualsAsDimension(new Dimension(-15, -3)));
        assertTrue(d.EqualsAsDimension(new Dimension(0, 12)));
        assertFalse(d.EqualsAsDimension(new Dimension(-10, -3)));
    }

    //Тест проверяющий корректность получения максимальной и минимальной границы области опрделения
    @Test
    public void testMaxMin()
    {
        Dimension d = new Dimension(-155, 15);
        assertEquals(d.getMax(), 15);
        assertEquals(d.getMin(), -155);
    }

    //Тест проверяющий корректность длинны области определения
    @Test
    public void testLength()
    {
        Dimension d = new Dimension(-155, 15);
        assertEquals(d.getLength(), (d.getMax() - d.getMin() + 1));
    }

    // Тестирует Клонирование
    @Test
    public void testClone()
    {
        Dimension d = new Dimension(0, 10);
        Dimension d_clone = d.Clone();
        assertNotSame(d_clone, d);
        assertTrue(d.EqualsAsDimension(d_clone));
    }
}
