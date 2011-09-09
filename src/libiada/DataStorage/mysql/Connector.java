package libiada.DataStorage.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 22.08.11
 * Time: 17:40
 */
public class Connector {
    private Connection con = null;

    public Connector(String driver, String url, String login, String pass) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQLConnection.Cannot find this db driver classes.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("MySQLConnection.Cannot connect to this db.");
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        try {
            return con.createStatement();
        } catch (SQLException e) {
            System.err.println("MySQLConnection.There are problems with the getStatement ");
            e.printStackTrace();
        }
        return null;
    }
}
