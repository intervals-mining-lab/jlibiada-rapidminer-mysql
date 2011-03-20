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
    public void testCounterWithL1M1() {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.caclculate(1, 1);
        assertEquals(count, 1);
    }

    @Test
    public void testCounterWithL2M2() {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.caclculate(2, 2);
        assertEquals(count, 2);
    }

    @Test
    public void testCounterWithL3M3() {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.caclculate(3, 3);
        assertEquals(count, 5);
    }

    @Test
    public void testCounterWithL4M4() {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.caclculate(4, 4);
        assertEquals(count, 15);
    }

    @Test
    public void testCounterWithL5M5() {
        BuildingCounter counter = new BuildingCounter();
        int count = counter.caclculate(5, 5);
        assertEquals(count, 52);
    }

    @Test
    public void testCounterWithZeroLength() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.caclculate(0, 1);
        }
        catch (Exception e) {

        }
        fail();
    }

    @Test
    public void testCounterWithZeroAlphabetPower() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.caclculate(2, 0);
        }
        catch (Exception e) {

        }
        fail();
    }

    @Test
    public void testCounterWithAboveZeroLength() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.caclculate(-3, 1);
        }
        catch (Exception e) {

        }
        fail();
    }

    @Test
    public void testCounterWithAboveZeroAlphabetPower() {
        try {
            BuildingCounter counter = new BuildingCounter();
            int count = counter.caclculate(2, -51);
        }
        catch (Exception e) {

        }
        fail();
    }
}
