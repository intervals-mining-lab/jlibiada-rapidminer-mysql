package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastAlphabet;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.UtilClasses.ChainAlgebra.FastChainAlgebra;
import libiada.IntervalAnalysis.LinkUp;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 17.08.11
 * Time: 22:41
 */
public class BinaryFastAverageRemoteness extends FastCalculatorBase {
    private String sym1 = "";
    private String sym2 = "";
    private String linkUp = "";

    public BinaryFastAverageRemoteness(String s1, String s2) {
        this.sym1 = s1;
        this.sym2 = s2;
    }

    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        this.linkUp = linkUp.toString();
        FastAlphabet alphabet = chain.getAlphabet();
        FastUniformChain fastUniformChain1 = chain.getFastUniformChain(alphabet.indexOf(sym1));
        FastUniformChain fastUniformChain2 = chain.getFastUniformChain(alphabet.indexOf(sym2));
        FastUniformChain binaryChain = FastChainAlgebra.composition(fastUniformChain1, fastUniformChain2);
        return FastCalculatorFactory.getAverageRemoteness().getValue(binaryChain, linkUp);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "g(" + sym1 + "," + sym2 + ")";
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Binary building characteristic";
    }
}
