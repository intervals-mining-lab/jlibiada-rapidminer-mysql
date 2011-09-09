package libiada.DataStorage.mysql.Chain;

import libiada.DataStorage.mysql.SQLQuery;
import libiada.FastChainAlgorithms.FastChain.FastAlphabet;
import libiada.FastChainAlgorithms.FastChain.FastChain;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 22.08.11
 * Time: 19:02
 */
public class SQLChainWriter {
    private SQLQuery query = null;

    public SQLChainWriter(SQLQuery query) {
        this.query = query;
    }

    public int write(FastChain ch, String typeName) {
        String building = ch.getBuilding();
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `ChainType` WHERE TypeName='" + typeName + "';");
        Integer typeId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT COUNT(id) FROM `ChainType`;");
            typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO ChainType VALUES (" + typeId.toString() + ", '" + typeName + "');");
        }
        vec = query.query("SELECT COUNT(id) FROM `Chains`;");
        Integer chainId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
        query.execute("INSERT INTO Chains VALUES (" + chainId.toString() + ", '" + building + "', " + typeId.toString() + ");");
        writeAlphabet(ch.getAlphabet(), chainId);
        return chainId;
    }

    private void writeAlphabet(FastAlphabet alphabet, int chainId) {
        Vector<Vector<Object>> vec = null;
        vec = query.query("SELECT COUNT(id) FROM `Alphabets`;");
        Integer alphabetIndex = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
        for (int i = 0; i < alphabet.size(); i++) {
            String event = alphabet.get(i);
            vec = query.query("SELECT id FROM `Elements` WHERE element='" + event + "';");
            Integer eventIndex = -1;
            Integer number = i + 1;
            if (vec.size() > 0) {
                if (vec.get(0).size() > 0) {
                    eventIndex = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
                }
            } else {
                vec = query.query("SELECT COUNT(id) FROM `Elements`;");
                eventIndex = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
                query.execute("INSERT INTO Elements VALUES (" + eventIndex.toString() + ", '" + event + "');");
            }
            query.execute("INSERT INTO Alphabets VALUES (" + Integer.toString(alphabetIndex+i) + ", " + chainId + ", " + number + ", " + eventIndex + ");");
        }
    }
}
