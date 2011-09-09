package libiada.DataStorage.mysql.Chain;

import libiada.DataStorage.mysql.SQLQuery;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 22.08.11
 * Time: 21:46
 */
public class SQLCharacteristicWriter {
    private SQLQuery query = null;

    public SQLCharacteristicWriter(SQLQuery query) {
        this.query = query;
    }

    public void write(int chainIndex, double value, String name, String group, String type) {
        int charactIndex = addCharacteristic(name, group, type);

        Vector<Vector<Object>> vec = query.query("SELECT MAX(id) FROM `Characteristics`;");
        Integer id = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;

        query.execute("INSERT INTO Characteristics VALUES (" +  id + ", " + charactIndex + ", " + chainIndex + ", " + value + ", 'null');");
    }

    private int addCharacteristic(String name, String group, String type) {
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `CharacteristicNames` WHERE CharacteristicName='" + name + "';");
        Integer charactId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                charactId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `CharacteristicNames`;");
            charactId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            String groupId = getGroupId(group);
            String typeId = getTypeId(type);
            query.execute("INSERT INTO CharacteristicNames VALUES (" + charactId + ", '" + name + "', " + groupId + ", " + typeId + ");");
        }
        return charactId;
    }

    private String getTypeId(String name) {
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `CharacteristicType` WHERE TypeName='" + name + "';");
        Integer typeId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `CharacteristicType`;");
            typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO CharacteristicType VALUES (" + typeId + ", '" + name + "');");
        }
        return typeId.toString();
    }

    private String getGroupId(String name) {
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `CharacteristicGroups` WHERE GroupName='" + name + "';");
        Integer groupId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                groupId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `CharacteristicGroups`;");
            groupId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO CharacteristicGroups VALUES (" + groupId + ", '" + name + "');");
        }
        return groupId.toString();
    }

    public void write(int chainIndex, String value, String name, String group, String type) {
        int charactIndex = addCharacteristic(name, group, type);

        Vector<Vector<Object>> vec = query.query("SELECT MAX(id) FROM `Characteristics`;");
        Integer id = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;

        value = value.replaceAll("'", "\"");

        query.execute("INSERT INTO Characteristics VALUES (" + id + ", " + charactIndex + ", " + chainIndex + ", -1, '" + value + "');");
    }
}
