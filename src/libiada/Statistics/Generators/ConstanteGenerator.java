package libiada.Statistics.Generators;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 10.10.11
 * Time: 3:19
 */
public class ConstanteGenerator implements IGenerator {
    private int value = 0;

    public ConstanteGenerator(int value) {
        this.value = value;
    }

    @Override
    public int getNextInt() {
        return value;
    }
}
