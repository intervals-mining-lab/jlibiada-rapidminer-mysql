package Bio;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.07.11
 * Time: 21:40
 */
public class DNARegion {
    private String dna = "";
    private String annotation = "";
    private int startPos = 0;
    private int endPos = 0;
    private boolean isComplement = false;

    public DNARegion(String dna, String anotation, int startPos, int endPos, boolean complement) {
        this.dna = dna;
        this.annotation = anotation;
        this.startPos = startPos;
        this.endPos = endPos;
        this.isComplement = complement;
    }

    public String getAnotation() {
        return annotation;
    }

    public String getSequence() {
        return dna;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public boolean getIsComplement() {
        return isComplement;
    }
}
