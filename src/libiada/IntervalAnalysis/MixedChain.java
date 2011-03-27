package libiada.IntervalAnalysis;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 26.03.11
 * Time: 19:32
 */
public class MixedChain extends Chain {
    public MixedChain(int length) throws Exception {
        super(length);
    }

    public MixedChain(String s) throws Exception {
        super(s);
        convert();
    }

    private void convert() throws Exception {
        ToMixedChainConverter converter = new ToMixedChainConverter();
        MixedChain newChain = converter.convert(this);

        this.ClearAndSetNewLength(this.getLength());
        this.fillFromAnother(newChain);
    }
}
