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
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `characteristic_names` WHERE name='" + name + "';");
        Integer charactId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                charactId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `characteristic_names`;");
            if ((vec.size() == 0) || (vec.get(0).get(0) == null))
                charactId = 1;
            else
                charactId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            String groupId = getGroupId(group);
            String typeId = getTypeId(type);
            query.execute("INSERT INTO characteristic_names VALUES (" + charactId + ", '" + name + ");");
        }
        return charactId;
    }

    private String getTypeId(String name) {
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `characteristic_types` WHERE name='" + name + "';");
        Integer typeId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `characteristic_types`;");
            if ((vec.size() == 0) || (vec.get(0).get(0) == null))
                typeId = 1;
            else
                typeId = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO characteristic_types VALUES (" + typeId + ", '" + name + "', 'description');");
        }
        return typeId.toString();
    }

    private String getGroupId(String name) {
        Vector<Vector<Object>> vec = query.query("SELECT id FROM `characteristic_groups` WHERE name='" + name + "';");
        Integer groupId = -1;
        if (vec.size() > 0) {
            if (vec.get(0).size() > 0) {
                groupId = Integer.parseInt(String.valueOf(vec.get(0).get(0)));
            }
        } else {
            vec = query.query("SELECT MAX(id) FROM `characteristic_groups`;");
            groupId = (vec.get(0).get(0)==null) ? 1 : Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;
            query.execute("INSERT INTO characteristic_groups VALUES (" + groupId + ", '" + name + "', 'description');");
        }
        return groupId.toString();
    }

    public void write(int chainIndex, String value, String name, String group, String type) {
        int charactIndex = addCharacteristic(name, group, type);

        Vector<Vector<Object>> vec = query.query("SELECT MAX(id) FROM `characteristics`;");
        Integer id = Integer.parseInt(String.valueOf(vec.get(0).get(0)))+1;

        value = value.replaceAll("'", "\"");

        query.execute("INSERT INTO characteristics VALUES (" + id + ", " + charactIndex + ", " + chainIndex + ", -1, '" + value + "');");
    }
}
