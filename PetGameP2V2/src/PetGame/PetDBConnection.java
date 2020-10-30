package PetGame;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class PetDBConnection {

    Connection conn = null;
    String url = "jdbc:derby:PetGameDB; create=true";
    String username = "rxc2457";
    String password = "rxc2457";
    Statement statement = null;

    public PetDBConnection() {

    }

    public void connectPetGameDB() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            if (!this.checkExistedTable("PetGame")) {
                this.createTable();
            }
        } catch (SQLException e) {

        }
    }

    public void createTable() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.executeQuery("CREATE TABLE PetGame (USERNAME VARCHAR(50), PASSWORD VARCHAR(50), ANIMAL VARCHAR(30), NAME VARCHAR(30), MONEY FLOAT, AGE FLOAT, HEALTH FLOAT, HUNGER FLOAT, HAPPINESS FLOAT, ENERGY FLOAT, HUNGERDECAY FLOAT, HAPPINESSDECAY FLOAT, ENERGYDECAY FLOAT, ALIVE BOOLEAN");
        } catch (SQLException e) {
            System.out.println("Table creation error " + e);
        }
    }

    public String[] loginDB(String loginuser, String loginpass) {
        String[] data = new String[11];
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = this.statement.executeQuery("SELECT NAME, ANIMAL, MONEY, AGE, HEALTH, HUNGER, HAPPINESS, ENERGY, HUNGERDECAY, HAPPINESSDECAY, ENERGYDECAY, ALIVE WHERE (USERNAME = " + loginuser + ") AND (PASSWORD = " + loginpass + ")");
            while (rs.next()) {
                data[0] = rs.getString("NAME");
                data[1] = rs.getString("ANIMAL");
                data[2] = rs.getString("MONEY");
                data[3] = rs.getString("AGE");
                data[4] = rs.getString("HEALTH");
                data[5] = rs.getString("HUNGER");
                data[6] = rs.getString("HAPPINESS");
                data[7] = rs.getString("ENERGY");
                data[8] = rs.getString("HUNGERDECAY");
                data[9] = rs.getString("HAPPINESSDECAY");
                data[10] = rs.getString("ENERGYDECAY");
                data[11] = rs.getString("ALIVE");
            }
        } catch (SQLException e) {
            System.out.println("Query error " + e);
        }
        return data;
    }

    public void newUser(String username, String password, String animal, String name, double money, double age, double health, double hunger, double happiness, double energy, double hungerdecay, double happinessdecay, double energydecay, boolean alive) {
        try {
            this.statement = this.conn.createStatement();
            this.statement.executeQuery("INSERT INTO PetGame VALUES (" + "'" + username + "', '" + password + "', '" + name + "', " + money + ", " + age + ", " + health + ", " + hunger + ", " + happiness + ", " + energy + ", " + hungerdecay + ", " + happinessdecay + ", " + energydecay + ", " + alive + ")");
        } catch (SQLException e) {

        }
    }

    public boolean checkUser(String un, String pw) {
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = this.statement.executeQuery("SELECT * FROM PetGame WHERE (USERNAME = " + un + ") AND (PASSWORD = " + pw + ") ");
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error at table check " + e);
        }
        return false;
    }

    public boolean checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, name, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error at table check " + e);
        }
        return false;
    }

}
