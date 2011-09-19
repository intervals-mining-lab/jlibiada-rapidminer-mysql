package libiada.IntervalAnalysis.Buildings.Counter;

public class BuildingCountLimitAlphabet extends BuildingsCount{
    public BuildingCountLimitAlphabet() {
    }

    protected double f(int l, int m, int s) {
        if (l + s <= m) {
            return 0;
        }
        return super.f(l, m, s);
    }
}