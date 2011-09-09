package Bio;

import Bio.Iterators.InverseTrippleIterator;
import Bio.Iterators.TrippleIterator;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.template.SequenceView;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.05.11
 * Time: 3:12
 */
public class SimpleORFFinder {
    public ArrayList<String> find(DNASequence sequence, int minLength, ArrayList<DNASequence> startTrip, ArrayList<DNASequence> stopTrip, int startIndex, boolean isComplement) {
        TrippleIterator iter = null;
        if (isComplement) {
            iter = new InverseTrippleIterator(sequence, startIndex, 3);
        } else {
            iter =  new TrippleIterator(sequence, startIndex, 3);
        }
        ArrayList<Integer> startTripIndex = new ArrayList<Integer>();
        ArrayList<String> orf = new ArrayList<String>();
        while (iter.next()) {
            String triple = iter.getNext();
            if (isContainInArray(triple, startTrip)) {
                startTripIndex.add(iter.currentPos());
            }
            else  if (isContainInArray(triple, stopTrip)) {
                if (startTripIndex.size() > 0) {
                    for (Integer index : startTripIndex) {
                        int len = Math.abs(iter.currentPos() + 3 - index);
                        if ((len > minLength) && (len % 3 == 0)) {
                            String substring = null;
                            if (isComplement) {
                                substring = "complement ORF: (" + ((Integer)(index+1)).toString() + "..." + ((Integer)(iter.currentPos()-1)).toString() + ") " + sequence.getSequenceAsString().substring(iter.currentPos()-3, index);
                            } else {
                                substring = "ORF: (" + ((Integer)(index+1)).toString() + "..." + ((Integer)(iter.currentPos()+3)).toString() + ") " + sequence.getSequenceAsString().substring(index, iter.currentPos()-3);
                            }
                            orf.add(substring);
                        }
                    }
                    startTripIndex.clear();
                }
            }
        }
        return orf;
    }

    private boolean isContainInArray(String sequenceAsString, ArrayList<DNASequence> array) {
        for (DNASequence trip : array) {
            if (trip.getSequenceAsString().equalsIgnoreCase(sequenceAsString))
                return true;
        }
        return false;
    }
}
