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
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `chain_types` WHERE name='" + typeName + "';");
        Integer typeId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT COUNT(id) FROM `chain_types`;");
            typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO chain_types VALUES (" + typeId.toString() + ", '" + typeName + "', 'test');");
        }
        vec = query.query("SELECT COUNT(id) FROM `chains`;");
        Integer chainId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
        query.execute("INSERT INTO chains VALUES (" + chainId.toString() + ", '" + building + "', " + typeId.toString() + ");");
        writeAlphabet(ch.getAlphabet(), chainId);
        return chainId;
    }

    private void writeAlphabet(FastAlphabet alphabet, int chainId) {
        Vector<Vector<Object>> vec = null;
        for (int i = 0; i < alphabet.size(); i++) {
            String event = alphabet.get(i);
            vec = query.query("SELECT id FROM `elements` WHERE value='" + event + "';");
            Integer eventIndex = -1;
            Integer number = i + 1;
            if (vec.size() > 0) {
                if (vec.get(0).size() > 0) {
                    eventIndex = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
                }
            } else {
                vec = query.query("SELECT COUNT(id) FROM `elements`;");
                eventIndex = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
                query.execute("INSERT INTO elements VALUES (" + eventIndex.toString() + ", '" + event + "', 'description');");
            }
            query.execute("INSERT INTO alphabet VALUES (" + chainId + ", " + eventIndex + ", " + number + ");");
        }
    }
}
