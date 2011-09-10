package libiada.FastChainAlgorithms.FastChain.Calculators;

import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Interfaces.FastCalculatorBase;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 30.07.11
 * Time: 16:50
 */
public class FastShepherd extends FastCalculatorBase {
    @Override
    public double getValue(FastChain chain, LinkUp linkUp) throws Exception {
        double T1 = getPosCount(chain, "T", 0);
        double T2 = getPosCount(chain, "T", 1);
        double T3 = getPosCount(chain, "T", 2);
        double Tp = max(T1, T2, T3) / (min(T1, T2, T3) + 1);
        double C1 = getPosCount(chain, "C", 0);
        double C2 = getPosCount(chain, "C", 1);
        double C3 = getPosCount(chain, "C", 2);
        double Cp = max(C1, C2, C3) / (min(C1, C2, C3) + 1);
        double A1 = getPosCount(chain, "A", 0);
        double A2 = getPosCount(chain, "A", 1);
        double A3 = getPosCount(chain, "A", 2);
        double Ap = max(A1, A2, A3) / (min(A1, A2, A3) + 1);
        double G1 = getPosCount(chain, "G", 0);
        double G2 = getPosCount(chain, "G", 1);
        double G3 = getPosCount(chain, "G", 2);
        double Gp = max(G1, G2, G3) / (min(G1, G2, G3) + 1);
        double Tf = FastCalculatorFactory.getPropability().getValue(chain.getFastUniformChain("T"), linkUp);
        double Cf = FastCalculatorFactory.getPropability().getValue(chain.getFastUniformChain("C"), linkUp);
        double Af = FastCalculatorFactory.getPropability().getValue(chain.getFastUniformChain("A"), linkUp);
        double Gf = FastCalculatorFactory.getPropability().getValue(chain.getFastUniformChain("G"), linkUp);
        return 0.33*Tp + 0.18*Cp + 0.26*Ap + 0.31*Gp + 0.14*Tf + 0.12*Cf + 0.11*Af + 0.15*Gf;
    }

    private double getPosCount(FastChain chain, String event, int pos) throws Exception {
        HashSet<Integer> poses = new HashSet<Integer>();
        poses.add(pos);
        return FastCalculatorFactory.getPositionedEventCount(event, poses, 3).getValue(chain, LinkUp.Start);
    }

    private double min(double t1, double t2, double t3) {
        return Math.min(Math.min(t1, t2), t3);
    }

    private double max(double t1, double t2, double t3) {
        return Math.max(Math.max(t1, t2), t3);
    }

    @Override
    public double getValue(FastUniformChain chain, LinkUp linkUp) throws Exception {
        return 0;  //TODO: "Заполнить метод"
    }

    @Override
    public String getName() {
        return "Shepherd";  //TODO: "Заполнить метод"
    }

    @Override
    public String getType() {
        return "double";
    }

    @Override
    public String getGroup() {
        return "Bioinformatics";
    }
}
