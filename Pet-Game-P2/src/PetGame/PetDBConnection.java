package PetGame;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class PetDBConnection {

    Connection conn = null;
    String url = "jdbc:derby://localhost:1527/PetGameDB";
    String username = "rxc2457";
    String password = "rxc2457";
    Statement statement = null;

    String playerUsername;
    String playerPassword;

    public static void main(String[] args) {
        PetDBConnection db = new PetDBConnection();
        db.connectPetGameDB();
        db.createTable();
    }

    public PetDBConnection() {
    }

    // Connects to the local database and create the petgame table if it does not exist.
    public void connectPetGameDB() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            if (!this.checkExistedTable("PetGame")) {
                this.createTable();
            }
        } catch (SQLException e) {

        }
    }

    // Creates the petgame table
    public void createTable() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            this.statement = this.conn.createStatement();
            this.statement.executeQuery("CREATE TABLE PetGame (USERNAME VARCHAR(50), PASSWORD VARCHAR(50), ANIMAL VARCHAR(30), NAME VARCHAR(30), MONEY FLOAT, AGE FLOAT, HEALTH FLOAT, HUNGER FLOAT, HAPPINESS FLOAT, ENERGY FLOAT, HUNGERDECAY FLOAT, HAPPINESSDECAY FLOAT, ENERGYDECAY FLOAT, ALIVE BOOLEAN");
        } catch (SQLException e) {
            System.out.println("Table creation error " + e);
        }
    }

    /**
     * Searches table for player's username and password
     *
     * @param loginuser
     * @param loginpass
     * @return an array of strings which is the pet game's values
     */
    public String[] loginDB(String loginuser, String loginpass) {
        this.playerUsername = loginuser;
        this.playerPassword = loginpass;

        String[] data = new String[11];
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = this.statement.executeQuery("SELECT NAME, ANIMAL, MONEY, AGE, HEALTH, HUNGER, HAPPINESS, ENERGY, HUNGERDECAY, HAPPINESSDECAY, ENERGYDECAY, ALIVE WHERE (USERNAME = '" + loginuser + "') AND (PASSWORD = '" + loginpass + "')");
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

    // Will create a new user in the database
    public void newUser(String username, String password, String animal, String name, double money, double age, double health, double hunger, double happiness, double energy, double hungerdecay, double happinessdecay, double energydecay, boolean alive) {
        this.playerUsername = username;
        this.playerPassword = password;
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            this.statement = this.conn.createStatement();
            this.statement.executeQuery("INSERT INTO PetGame VALUES (" + "'" + username + "', '" + password + "', '" + name + "', " + money + ", " + age + ", " + health + ", " + hunger + ", " + happiness + ", " + energy + ", " + hungerdecay + ", " + happinessdecay + ", " + energydecay + ", " + alive + ")");
        } catch (SQLException e) {

        }
    }

    /**
     * Checks if the user exists in the database if not then it will return
     * false
     *
     * @param un
     * @param pw
     * @return
     */
    public boolean checkUser(String un, String pw) {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            this.statement = this.conn.createStatement();
            ResultSet rs = this.statement.executeQuery("SELECT * FROM PetGame WHERE (USERNAME = " + un + ") AND (PASSWORD = " + pw + ") ");
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error at table check " + e);
        }
        return false;
    }

    /**
     * Checks if the table exists in the database
     *
     * @param name
     * @return
     */
    public boolean checkExistedTable(String name) {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
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

    // Saves the game's values into the database for the next time the user logs into the game
    public void saveGame(Game game) {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            this.statement = this.conn.createStatement();
            this.statement.executeQuery("INSERT INTO PetGame VALUES (" + "'" + playerUsername + "', '" + playerPassword + "', '" + game.getPlayer().getPet().getName() + "', " + game.getPlayer().getMoney() + ", " + game.getPlayer().getPet().getAge() + ", " + game.getPlayer().getPet().getHealthLevel() + ", " + game.getPlayer().getPet().getHungerLevel() + ", " + game.getPlayer().getPet().getHappinessLevel() + ", " + game.getPlayer().getPet().getEnergyLevel() + ", " + game.getPlayer().getPet().getHungerDecay() + ", " + game.getPlayer().getPet().getHappinessDecay() + ", " + game.getPlayer().getPet().getEnergyDecay() + ", " + game.getPlayer().getPet().getIsAlive() + ") WHERE (USERNAME = '" + playerUsername + "') AND (PASSWORD = '" + playerPassword + "')");
        } catch (SQLException e) {

        }
    }

}
