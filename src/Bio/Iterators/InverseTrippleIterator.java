package Bio.Iterators;

import org.biojava3.core.sequence.DNASequence;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 21.05.11
 * Time: 13:54
 */
public class InverseTrippleIterator extends TrippleIterator {
    public InverseTrippleIterator(DNASequence sequence, int startIndex, int step) {
        super();
        this.current = sequence.getLength() + step - 1;
        this.step = step;
        this.sequence = sequence.getSequenceAsString();
    }

    @Override
    public boolean next() {
        current = current - step;
        return current > 3;
    }

    public String getNext() {
        String result = "";
        result += Character.toString(sequence.charAt(current));
        result += Character.toString(sequence.charAt(current - 1));
        result += Character.toString(sequence.charAt(current - 2));
        return result;
    }

    public int currentPos() {
        return current;
    }

    public boolean previous() {
        current = current + step;
        return sequence.length() > (current + 3);
    }
}