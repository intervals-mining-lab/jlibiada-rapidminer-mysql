package libiada.Statistics.Generators;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 16:54
 */
public class MockGenerator implements IGenerator {
    private int current = -1;
    private int[] values = {1, 4, 12, 5, 8, 9, 4, 15, 17, 1, 17, 4, 14, 16, 7, 9, 3, 3, 4, 5, 6, 7, 8, 14, 13, 4, 7, 6, 2};

    public MockGenerator(int left, int right) {
    }

    @Override
    public int getNextInt() {
        current++;
        return values[current];
    }
}
