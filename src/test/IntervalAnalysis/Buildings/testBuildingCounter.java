package test.IntervalAnalysis.Buildings;

import junit.framework.TestCase;
import libiada.IntervalAnalysis.Buildings.BuildingCounter;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 20.03.11
 * Time: 22:30
 */
public class testBuildingCounter extends TestCase {
    @Test
    public void testCounterWithL1M1() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(1, 1);
        assertEquals(count, 1);
    }

    @Test
    public void testCounterWithL2M2() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(2, 2);
        assertEquals(count, 2);
    }

    @Test
    public void testCounterWithL3M3() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(3, 3);
        assertEquals(count, 5);
    }

    @Test
    public void testCounterWithL4M4() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(4, 4);
        assertEquals(count, 15);
    }

    @Test
    public void testCounterWithL5M5() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(5, 5);
        assertEquals(count, 52);
    }

    @Test
    public void testCounterWithL4M2() throws Exception {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.calculate(4, 2);
        assertEquals(count, 8);
    }

    @Test
    public void testCounterWithZeroLength() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.calculate(0, 1);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void testCounterWithZeroAlphabetPower() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.calculate(2, 0);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void testCounterWithAboveZeroLength() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.calculate(-3, 1);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    public void testCounterWithAboveZeroAlphabetPower() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.calculate(2, -51);
        }
        catch (Exception e) {
            return;
        }
        fail();
    }
}
