package test.Segmentation;

import junit.framework.TestCase;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.IntervalAnalysis.LinkUp;
import libiada.Segmentation.Criteria.MaxG;
import libiada.Segmentation.Segmentator;
import libiada.Statistics.Generators.SimpleGenerator;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 17:03
 */
public class testSegmentator extends TestCase {
    @Test
    public void testSegmentate() throws Exception {
        FastChain chain = new FastChain("TCTGGTTGATTCTGCCAGTAGCATATGCTTGTCTCAAAGATTAAGCCATGCAAGTCTAAGTACACACGGCCGGTACAGTGAAACTGCGAATGGCTCATTA");
        Segmentator segmentator = new Segmentator(16, new MaxG(LinkUp.Start), new SimpleGenerator(1, chain.length()-1));
        FastChain result = segmentator.segmetate(chain);
        double g = FastCalculatorFactory.getAverageRemoteness().getValue(result, LinkUp.Start);
    }
}
