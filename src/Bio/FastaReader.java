package Bio;

import org.biojava3.core.sequence.DNASequence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.07.11
 * Time: 19:42
 */
public class FastaReader {
    public static LinkedHashMap<String, DNARegion> readFastaDNASequence(File file) throws IOException {
        LinkedHashMap<String, DNARegion> result = new LinkedHashMap<String, DNARegion>();
        FileInputStream fin = new FileInputStream(file);
        String dnaText = "";
        String dna = "";
        String anotation = "";
        int i = 0;
        String ncbiIndex = "";
        do {
            i = fin.read();
            if (i != -1) {
                dnaText += Character.toString((char)i);
            }
            String startStr = "";
            if ((dnaText.length() >= 5)) {
                startStr = dnaText.substring(dnaText.length() - 5);
            }

            if (startStr.equalsIgnoreCase(">ref|")) {
                dna = dnaText.substring(0, dnaText.length()-5);
                dnaText = "";
                if (dna.length() > 5) {
                    dna = dna.substring(1, dna.length()-1);
                    int startPos = getStartPos(anotation);
                    int endPos = getEndPos(anotation);
                    boolean isComplement = isComplement(anotation);
                    result.put(ncbiIndex + " " + anotation, new DNARegion(dna, anotation, startPos, endPos, isComplement));
                    anotation = "";
                    dna = "";
                }
            }
            if ((dnaText.length() > 2) && (':' == dnaText.charAt(dnaText.length()-1)) && ('|' == dnaText.charAt(dnaText.length()-2))) {
                ncbiIndex = dnaText.substring(0, dnaText.length()-2);
                dnaText = "";
            }
            if (']' == (char)i) {
                anotation += dnaText.substring(0, dnaText.length());
                dnaText = "";
            }
        } while (i != -1);
        return result;
    }

    private static boolean isComplement(String anotation) {
        return 'c' == anotation.charAt(0);
    }

    private static int getEndPos(String anotation) {
        return Integer.parseInt(anotation.substring(anotation.indexOf("-")+1, anotation.indexOf(' ')));
    }

    private static int getStartPos(String anotation) {
        if (isComplement(anotation))
            return Integer.parseInt(anotation.substring(1, anotation.indexOf("-")));
        else
            return Integer.parseInt(anotation.substring(0, anotation.indexOf("-")));
    }
}
