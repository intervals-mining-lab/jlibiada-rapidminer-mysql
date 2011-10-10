package libiada.Statistics.Generators;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 09.10.11
 * Time: 16:19
 */
public class SimpleGenerator implements IGenerator {
    private Random gen = null;
    private double left = 0;
    private double right = 0;

    public SimpleGenerator(double left, double right) {
        gen = new Random();
        this.left = left;
        this.right = right;
    }

    @Override
    public int getNextInt() {
        int nextInt = gen.nextInt((int) (right - left));
        return (int)left + nextInt;
    }
}
