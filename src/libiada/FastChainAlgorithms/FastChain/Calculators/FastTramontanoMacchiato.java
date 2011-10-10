package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastChainBase;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.UtilClasses.ChainToTripleConverter;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 05.08.11
 * Time: 0:39
 */
public class FastTramontanoMacchiato extends FastCalculatorBase {
    private final double s1 = 0.15;
    private final double s2 = 0.25;
    private final double m1 = 2.25;
    private final double m2 = 2.5;

    private HashMap<String, Double> fOfTriple;

    public FastTramontanoMacchiato() {
        fOfTriple = new HashMap<String, Double>();
        fOfTriple.put("AAA", 2.9);
        fOfTriple.put("AAC", 3.4);
        fOfTriple.put("AAG", 2.3);
        fOfTriple.put("AAT", 2.8);

        fOfTriple.put("ACA", 1.3);
        fOfTriple.put("ACC", 1.2);
        fOfTriple.put("ACG", 1.3);
        fOfTriple.put("ACT", 1.2);

        fOfTriple.put("AGA", 1.5);
        fOfTriple.put("AGC", 1.0);
        fOfTriple.put("AGG", 1.7);
        fOfTriple.put("AGT", 1.1);

        fOfTriple.put("ATA", 3.9);
        fOfTriple.put("ATC", 4.3);
        fOfTriple.put("ATG", 3.0);
        fOfTriple.put("ATT", 4.4);

        ////////////////////////////

        fOfTriple.put("CAA", 3.5);
        fOfTriple.put("CAC", 3.4);
        fOfTriple.put("CAG", 2.0);
        fOfTriple.put("CAT", 3.0);

        fOfTriple.put("CCA", 4.2);
        fOfTriple.put("CCC", 3.5);
        fOfTriple.put("CCG", 4.2);
        fOfTriple.put("CCT", 3.5);

        fOfTriple.put("CGA", 1.4);
        fOfTriple.put("CGC", 1.6);
        fOfTriple.put("CGG", 1.6);
        fOfTriple.put("CGT", 1.6);

        fOfTriple.put("CTA", 1.9);
        fOfTriple.put("CTC", 1.5);
        fOfTriple.put("CTG", 2.2);
        fOfTriple.put("CTT", 1.5);

        ////////////////////////////

        fOfTriple.put("GAA", 1.4);
        fOfTriple.put("GAC", 1.2);
        fOfTriple.put("GAG", 1.4);
        fOfTriple.put("GAT", 1.2);

        fOfTriple.put("GCA", 1.0);
        fOfTriple.put("GCC", 1.0);
        fOfTriple.put("GCG", 1.0);
        fOfTriple.put("GCT", 1.0);

        fOfTriple.put("GGA", 1.4);
        fOfTriple.put("GGC", 0.8);
        fOfTriple.put("GGG", 1.5);
        fOfTriple.put("GGT", 0.8);

        fOfTriple.put("GTA", 2.4);
        fOfTriple.put("GTC", 2.4);
        fOfTriple.put("GTG", 1.8);
        fOfTriple.put("GTT", 2.4);

        ///////////////////////////

        fOfTriple.put("TAA", 0.0);
        fOfTriple.put("TAC", 4.1);
        fOfTriple.put("TAG", 0.0);
        fOfTriple.put("TAT", 4.1);

        fOfTriple.put("TCA", 3.0);
        fOfTriple.put("TCC", 3.2);
        fOfTriple.put("TCG", 4.1);
        fOfTriple.put("TCT", 3.2);

        fOfTriple.put("TGA", 0.0);
        fOfTriple.put("TGC", 4.1);
        fOfTriple.put("TGG", 5.6);
        fOfTriple.put("TGT", 3.5);

        fOfTriple.put("TTA", 2.9);
        fOfTriple.put("TTC", 2.5);
        fOfTriple.put("TTG", 1.7);
        fOfTriple.put("TTT", 2.5);
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double f = getF(chain);
        return ((1/s1)*Math.exp(Math.pow(f-m1, 2)/Math.pow(s1, 2))) /
                ((1/s1)*Math.exp(Math.pow(f-m1, 2)/Math.pow(s1, 2)) +
                (1/s2)*Math.exp(Math.pow(f-m2, 2)/Math.pow(s2, 2)));
    }

    private double getF(FastChain chain) throws Exception {
        ChainToTripleConverter converter = new ChainToTripleConverter();
        FastChainBase tripleChain = converter.convert(chain);
        double value = 0;
        for (int i = 0; i < tripleChain.length(); i++) {
            String triple = tripleChain.get(i);
            if (3 == triple.length())
                value += fOfTriple.get(triple);
        }
        return value / tripleChain.length();
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "Tramontano-Macchiato";
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Bioinformatics";
    }
}
