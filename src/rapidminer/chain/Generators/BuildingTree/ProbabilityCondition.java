package rapidminer.chain.Generators.BuildingTree;

import com.rapidminer.example.Example;
import com.rapidminer.operator.learner.tree.SplitCondition;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 19.09.11
 * Time: 21:41
 */
public class ProbabilityCondition implements SplitCondition {
    private double probability = 0;

    public ProbabilityCondition(double p) {
        this.probability = p;
    }

    @Override
    public String getAttributeName() {
        return null;
    }

    @Override
    public String getRelation() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public String getValueString() {
        return null;  //TODO: "Заполнить метод"
    }

    @Override
    public boolean test(Example example) {
        return false;  //TODO: "Заполнить метод"
    }
}
