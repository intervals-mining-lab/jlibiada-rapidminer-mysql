package Bio.Iterators;

import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.template.SequenceView;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.04.11
 * Time: 8:32
 */
public class TrippleIterator {
    protected int step = 0;
    protected int current = -1;
    protected String sequence = "";

    public TrippleIterator(DNASequence sequence, int startIndex, int step) {
        this.step = step;
        this.current = - step + startIndex - 1;
        this.sequence = sequence.getSequenceAsString();
    }

    public TrippleIterator() {}

    public boolean next() {
        current = current + step;
        return sequence.length() >= (current + 3);
    }

    public String getNext() {
        return sequence.substring(current, current + 3);
    }

    public int currentPos() {
        return current;
    }

    public boolean previous() {
        current = current - step;
        return 0 < (current - 3);
    }
}
