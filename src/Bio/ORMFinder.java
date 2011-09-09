package Bio;

import org.biojava3.core.sequence.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 27.04.11
 * Time: 2:24
 */

public class ORMFinder {
    private ArrayList<String> orfs = new ArrayList<String>();

    public ArrayList<String> find(DNASequence sequence, int minLength, ArrayList<DNASequence> startTrip, ArrayList<DNASequence> stopTrip) {
        SimpleORFFinder finder = new SimpleORFFinder();
        orfs.addAll(finder.find(sequence, minLength, startTrip, stopTrip, 1, false));
        orfs.addAll(finder.find(sequence, minLength, startTrip, stopTrip, 2, false));
        orfs.addAll(finder.find(sequence, minLength, startTrip, stopTrip, 3, false));
        orfs.addAll(finder.find(new DNASequence(sequence.getComplement().getSequenceAsString()), minLength, startTrip, stopTrip, 1, true));
        orfs.addAll(finder.find(new DNASequence(sequence.getComplement().getSequenceAsString()), minLength, startTrip, stopTrip, 2, true));
        orfs.addAll(finder.find(new DNASequence(sequence.getComplement().getSequenceAsString()), minLength, startTrip, stopTrip, 3, true));
        return orfs;
    }

    public ArrayList<String> getStarts() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < orfs.size(); i++) {
            String orf = orfs.get(i);
            int startPos = orf.indexOf("(")+1;
            int endPos = orf.indexOf(".");
            result.add(orf.substring(startPos, endPos));
        }
        return result;
    }

    public ArrayList<String> getStops() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < orfs.size(); i++) {
            String orf = orfs.get(i);
            int startPos = orf.indexOf("...")+3;
            int endPos = orf.indexOf(")");
            result.add(orf.substring(startPos, endPos));
        }
        return result;
    }
}
