package test.DiffBuildingAnalysis.Rules;

import junit.framework.TestCase;
import libiada.DiffBuildingAnalysis.DifCutter;
import libiada.DiffBuildingAnalysis.Rules.FromFixStartCutRule;
import org.junit.Test;
import org.nfunk.jep.function.Str;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vladimir
 * Date: 16.11.11
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class testDifCutter extends TestCase{
    @Test
    public void testDifCutter() {
        String s = "reegwvwvw";
DifCutter dif = new DifCutter();   //рубит строчку
        FromFixStartCutRule rule = new FromFixStartCutRule(s.length(), 3);  //првило разбиения
        ArrayList<String> cuts =  dif.cut(s, rule);  //метод разрубающий строчку

        assertEquals(cuts.get(0), "ree");        //проверяем правильность результата
        assertEquals(cuts.get(1), "reegwv");
        assertEquals(cuts.get(2), "reegwvwvw");

    }
}
