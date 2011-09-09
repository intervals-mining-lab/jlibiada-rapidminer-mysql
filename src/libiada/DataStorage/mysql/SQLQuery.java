package libiada.DataStorage.mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 22.08.11
 * Time: 17:49
 */
public class SQLQuery {
    private Statement statement = null;

    public SQLQuery(Statement statement) {
        this.statement = statement;
    }

    public void execute(String query) {
        try{
            statement.execute(query);
        } catch (SQLException e) {
            System.err.println("SQLQuery.There are problems with the query " + query);
            e.printStackTrace();
        }
    }

    public Vector<Vector<Object>> query(String query) {
        Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
        try{
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            while (rs.next()) {
                Vector<Object> newRow = new Vector<Object>();

                for (int i = 1; i <= cols; i++) {
                    newRow.add(rs.getObject(i));
                }
                retVector.add(newRow);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("SQLQuery.There are problems with the query " + query);
            e.printStackTrace();
        }
        return retVector;
    }
}
