package test.EventTheory;

import junit.framework.TestCase;
import libiada.EventTheory.Dimension;
import libiada.EventTheory.Event;
import libiada.EventTheory.Place;
import libiada.EventTheory.ReadRule;
import libiada.Root.SimpleTypes.ValueInt;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.11.2010
 * Time: 9:07:22
 */
public class testEventTest extends TestCase {
    private Event baseEvent = null;
    private Place key = null;
    private Place value = null;


    @Before
    public void setUp() throws Exception {
        baseEvent = new Event();
        baseEvent.addDimension(new Dimension(0, 10));
        baseEvent.addDimension(new Dimension(0, 10));
        key = baseEvent.getPlacePattern();
        value = baseEvent.getPlacePattern();
    }

    @Test
    public void testAddToRuleSelf()
    {
        try
        {
            key.setValues(new long[] {1, 1});
            value.setValues(new long[] {1, 1});
            baseEvent.addToReadRule(key, value);
        }
        catch (Exception e)
        {
            return;
        }
        fail();
    }

    @Test
    public void testAddToRuleInWorkMode() throws Exception {
        key.setValues(new long[] {1, 1});
        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        assertEquals(baseEvent.getFromReadRule(key).getValue(0), value);
    }

    @Test
    public void testAddToRuleNullValue() throws Exception {
        try {
            key.setValues(new long[] {1, 1});
            baseEvent.addToReadRule(key, (Place) null);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void TestAddToRuleNullKey() throws Exception {
        try {
            value.setValues(new long[] {1, 1});
            baseEvent.addToReadRule(null, value);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void testAddToRuleSame() throws Exception {
        key.setValues(new long[] {1, 1});
        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);
        baseEvent.addToReadRule(key, value);

        assertEquals(1, Array.getLength(baseEvent.getFromReadRule(key)));
        assertEquals(baseEvent.getFromReadRule(key).getValue(0), value);
    }

    @Test
    public void testRemoveFromNull()
    {
        try {
            baseEvent.removeFromReadRule(null);
        }
        catch (Exception e) {
                return;
        }
        fail();
    }

    @Test
    public void testRemovFromInWorkMode() throws Exception {
        key.setValues(new long[] {1, 1});
        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);
        baseEvent.removeFromReadRuleAt(0);

        assertNull(baseEvent.getFromReadRule(key));
    }

    @Test
    public void testRemoveFromWrongPlace() throws Exception {
        try {
            baseEvent.addDimension(new Dimension(0, 10));
            baseEvent.addDimension(new Dimension(-10, 10));
            Place placeOtherSpace = baseEvent.getPlacePattern();
            baseEvent.addDimension(new Dimension(10, 100));
            Place placeSpace = baseEvent.getPlacePattern();
            baseEvent.addItem(new ValueInt(1), placeSpace.setValues(new long[] {0, 0, 0}));
            baseEvent.removeFromReadRule(placeOtherSpace.setValues(new long[] {0, 0}));
        }
        catch (Exception e) {
                return;
        }
        fail();
    }


    @Test
    public void testGetFromRuleInWorkMode() throws Exception {
        ReadRule gFor = new ReadRule(baseEvent.getPlacePattern());
        Place pl = baseEvent.getPlacePattern().setValues(new long[] {0, 1});
        baseEvent.addToReadRule(pl, gFor);

        ReadRule gAfter = baseEvent.getFromReadRule(pl);
        assertEquals(gFor, gAfter);
    }

    @Test
    public void TestGetFromRuleNUll() throws Exception {
        try
        {
            baseEvent.getFromReadRule(null);
        }
        catch (NullPointerException e)
        {
           return;
        }
        fail();
    }

    @Test
    public void testAddRuleInWorkMode() throws Exception {
        ReadRule rr = new ReadRule(baseEvent.getPlacePattern());
        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        rr.addPlace(value);

        value.setValues(new long[] {1, 0});
        rr.addPlace(value);

        baseEvent.addToReadRule(key, rr);
        assertEquals(baseEvent.getFromReadRule(key), rr);
    }

    @Test
    public void testAddRuleNullValue()
    {
        try
        {
            key.setValues(new long[] {1, 1});
            baseEvent.addToReadRule(key, (ReadRule) null);
        }
        catch (Exception e)
        {
            return;
        }
        fail();
    }

    @Test
    public void TestAddRuleNullKey()
    {
        try {
            ReadRule rr = new ReadRule(baseEvent.getPlacePattern());
            value.setValues(new long[] {0, 1});
            rr.addPlace(value);

            value.setValues(new long[] {1, 0});
            rr.addPlace(value);
            baseEvent.addToReadRule(null, rr);
        }
        catch (Exception e) {
                return;
        }
        fail();
    }

    @Test
    public void testAddRuleTwice() throws Exception {
        ReadRule rr = new ReadRule(baseEvent.getPlacePattern());
        ReadRule rr2 = new ReadRule(baseEvent.getPlacePattern());

        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        rr.addPlace(value);

        assertEquals(1, rr.getCount());
        assertEquals(value, rr.getValue(0));

        value.setValues(new long[] {1, 0});
        rr2.addPlace(value);

        assertEquals(1, rr2.getCount());
        assertEquals(value, rr.getValue(0));

        assertFalse(rr.equals(rr2));

        baseEvent.addToReadRule(key, rr);
        assertFalse(rr.equals(baseEvent.getFromReadRule(key)));

        baseEvent.addToReadRule(key, rr2);
        assertFalse(rr2.equals(baseEvent.getFromReadRule(key)));

        assertFalse(rr.equals(baseEvent.getFromReadRule(key)));

        assertEquals(rr2.addRule(rr), baseEvent.getFromReadRule(key));
        assertEquals(value.setValues(new long[] {0, 1}), baseEvent.getFromReadRule(key).getValue(0));
        assertEquals(value.setValues(new long[] {1, 0}), baseEvent.getFromReadRule(key).getValue(1));
        assertNotNull(baseEvent.getFromReadRule(key).getValue(1));
    }

    @Test
    public void testReadRuleCount() throws Exception {
        key.setValues(new long[] {1, 1});
        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        key.setValues(new long[] {4, 6});
        value.setValues(new long[] {5, 6});
        baseEvent.addToReadRule((Place) key.Clone(), value);

        assertEquals(2, baseEvent.getReadRuleCount());
    }

    @Test
    public void testEquals() throws Exception {
        Event Event2 = new Event();
        Event2.addDimension(new Dimension(0, 10));
        Event2.addDimension(new Dimension(0, 10));

        Place Key2 = Event2.getPlacePattern();
        Place Value2 = Event2.getPlacePattern();

        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        value.setValues(new long[] {1, 0});
        baseEvent.addToReadRule(key, value);


        key.setValues(new long[] {4, 3});

        value.setValues(new long[] {4, 2});
        baseEvent.addToReadRule(key, value);

        Key2.setValues(new long[] {1, 1});

        Value2.setValues(new long[] {0, 1});
        Event2.addToReadRule(Key2, Value2);

        Value2.setValues(new long[] {1, 0});
        Event2.addToReadRule(Key2, Value2);


        Key2.setValues(new long[] {4, 3});

        Value2.setValues(new long[] {4, 2});
        Event2.addToReadRule(Key2, Value2);


        assertTrue(baseEvent.Equals(Event2));
    }

    @Test
    public void testEqualsFalse() throws Exception {
        Event Event2 = new Event();
        Event2.addDimension(new Dimension(0, 10));
        Event2.addDimension(new Dimension(0, 10));

        Place Key2 = Event2.getPlacePattern();
        Place Value2 = Event2.getPlacePattern();

        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        value.setValues(new long[] {1, 0});
        baseEvent.addToReadRule(key, value);


        key.setValues(new long[] {4, 3});

        value.setValues(new long[] {4, 2});
        baseEvent.addToReadRule(key, value);

        Key2.setValues(new long[] {1, 1});

        Value2.setValues(new long[] {0, 1});
        Event2.addToReadRule(Key2, Value2);

        Value2.setValues(new long[] {1, 0});
        Event2.addToReadRule(Key2, Value2);

        assertFalse(baseEvent.Equals(Event2));
    }


    @Test
    public void testEqualsNull() throws Exception {
        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        value.setValues(new long[] {1, 0});
        baseEvent.addToReadRule(key, value);

        key.setValues(new long[] {4, 3});

        value.setValues(new long[] {4, 2});
        baseEvent.addToReadRule(key, value);

        assertFalse(baseEvent.Equals(null));
    }

    @Test
    public void testClone() throws Exception {
        key.setValues(new long[] {1, 1});

        value.setValues(new long[] {0, 1});
        baseEvent.addToReadRule(key, value);

        value.setValues(new long[] {1, 0});
        baseEvent.addToReadRule(key, value);


        key.setValues(new long[] {4, 3});

        value.setValues(new long[] {4, 2});
        baseEvent.addToReadRule(key, value);

        Event ItsClone = (Event) baseEvent.Clone();
        assertEquals(baseEvent, ItsClone);
    }
}