package test.EventTheory;

import junit.framework.TestCase;
import libiada.EventTheory.Dimension;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 17.11.2010
 * Time: 23:43:24
 */
public class testDimensionTest extends TestCase {
    @Test
    public void testEquals()
    {
        Dimension d1 = new Dimension(0, 10);
        Dimension d2 = new Dimension(0, 10);
        Dimension d3 = new Dimension(-10, 0);
        assertTrue(d1.equalsAsDimension(d2));
        assertFalse(d1.equalsAsDimension(d3));
        assertTrue(d1.equalsAsDimension(d1));
    }

    @Test
    public void testConstructor()
    {
        Dimension d1 = new Dimension(-120, 50);
        Dimension d2 = null;
        Dimension d3 = new Dimension(0, 10);
        Dimension d4 = new Dimension(10, 0);
        assertTrue(d3.equalsAsDimension(d4));
        assertFalse(d1.equalsAsDimension(d2));
    }

    @Test
    public void testCom()
    {
        Dimension d = new Dimension(0, 12);
        assertFalse(d.equalsAsDimension(new Dimension(-15, -3)));
        assertTrue(d.equalsAsDimension(new Dimension(0, 12)));
        assertFalse(d.equalsAsDimension(new Dimension(-10, -3)));
    }

    @Test
    public void testMaxMin()
    {
        Dimension d = new Dimension(-155, 15);
        assertEquals(d.getMax(), 15);
        assertEquals(d.getMin(), -155);
    }

    @Test
    public void testLength()
    {
        Dimension d = new Dimension(-155, 15);
        assertEquals(d.getLength(), (d.getMax() - d.getMin() + 1));
    }

    @Test
    public void testClone()
    {
        Dimension d = new Dimension(0, 10);
        Dimension d_clone = (Dimension)d.Clone();
        assertNotSame(d_clone, d);
        assertTrue(d.equalsAsDimension(d_clone));
    }
}