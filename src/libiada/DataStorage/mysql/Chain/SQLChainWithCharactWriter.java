package libiada.DataStorage.mysql.Chain;

import libiada.DataStorage.mysql.SQLQuery;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorFactory;
import libiada.FastChainAlgorithms.FastChain.FastChain;
import libiada.FastChainAlgorithms.FastChain.FastUniformChain;
import libiada.FastChainAlgorithms.FastChain.Calculators.FastCalculatorBase;
import libiada.IntervalAnalysis.LinkUp;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 25.08.11
 * Time: 16:22
 */
public class SQLChainWithCharactWriter {
    private SQLQuery query = null;

    public SQLChainWithCharactWriter(SQLQuery query) {
        this.query = query;
    }

    public int write(FastChain chain, String type) throws Exception {
        SQLChainWriter writer = new SQLChainWriter(query);
        int chIndex = writer.write(chain, type);
        characteristicsToDB(chain, query, chIndex);
        return chIndex;
    }

    private void characteristicsToDB(FastChain chain, SQLQuery query, int chIndex) throws Exception {
        SQLCharacteristicWriter characteristicWriter = new SQLCharacteristicWriter(query);

        FastCalculatorBase calculator = FastCalculatorFactory.getAverageRemoteness();
        double value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getFastShepherd();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getEventCount();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getFastTramontanoMacchiato();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getHentropy();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getLength();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        //TODO: "Regularity"
        /*calculator = FastCalculatorFactory.getRegularity();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());*/
        for (int i = 0; i < chain.alphabetPower(); i++) {
            uniformChainCharacteristicToDB(chain.getFastUniformChain(i), characteristicWriter, chIndex);
        }

        for (int i = 0; i < chain.alphabetPower(); i++)
            for (int j = 0; j < chain.alphabetPower(); j++) {
                if (i == j)
                    continue;
                binaryChainCharacteristicToDB(chain, chain.getAlphabet().get(i), chain.getAlphabet().get(j), characteristicWriter, chIndex);
        }

        for (int i = 0; i <= 2; i++)
            for (int j = 0; j < chain.alphabetPower(); j++) {
                positionedChainCharacteristicToDB(chain, chain.getAlphabet().get(j), i, characteristicWriter, chIndex);
        }
    }

    private HashSet<Integer> getPoses(int pos) {
        HashSet<Integer> poses = new HashSet<Integer>();
        for (int i = 0; i <= 2; i++) {
            if (i == pos)
                poses.add(i);
        }
        return poses;
    }

    private void positionedChainCharacteristicToDB(FastChain chain, String s, int pos, SQLCharacteristicWriter characteristicWriter, int chIndex) throws Exception {
        FastCalculatorBase calculator = FastCalculatorFactory.getPositionedAverageRemoteness(s, getPoses(pos), 3);
        double value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getPositionedPropability(s, getPoses(pos), 3);
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getPositionedEventCount(s, getPoses(pos), 3);
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());
    }

    private void binaryChainCharacteristicToDB(FastChain chain, String s1, String s2, SQLCharacteristicWriter characteristicWriter, int chIndex) throws Exception {
        FastCalculatorBase calculator = FastCalculatorFactory.getBinaryAverageRemoteness(s1, s2);
        double value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());
    }

    private void uniformChainCharacteristicToDB(FastUniformChain chain, SQLCharacteristicWriter characteristicWriter, int chIndex) throws Exception {
        FastCalculatorBase calculator = FastCalculatorFactory.getAverageRemoteness();
        double value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getPropability();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        calculator = FastCalculatorFactory.getHentropy();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());

        //TODO: "Regularity"
        /*calculator = FastCalculatorFactory.getRegularity();
        value = calculator.getValue(chain, LinkUp.End);
        characteristicWriter.write(chIndex, value, calculator.getName(), calculator.getGroup(), calculator.getType());*/
    }
}
