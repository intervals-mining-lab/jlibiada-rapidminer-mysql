package libiada.IntervalAnalysis.Buildings.Counter;

/**
 * Created by IntelliJ IDEA.
 * User: goruha
 * Date: 9/19/11
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildingsCount {
    protected double f(int l, int m, int s) {
        if (l < 1 || m < s) {
            return 0;
        }

        if (l == 1) {
            return 1;
        }

        double temp = 0;
        for (int i = 1; i <= s + 1; i++) {
            temp += f(l - 1, m, Math.max(s, i));
        }
        return temp;
    }

    public double getBuildingsCount(int l, int m) {
        return this.f(l, m, 1);
    }

    public double getProbability(int l, int m, int s, int s_parent) {
        return f(l, m, s)/f(l+1, m, s_parent);
    }
}
