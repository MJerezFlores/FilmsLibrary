package filmLibrary.database;

import java.sql.*;

public class Database {
    private String username;
    private String password;
    private Connection connection;

    public Database(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResultSet read(String query) throws SQLException {
        connection = getConnection();
        Statement st = connection.createStatement();
        return st.executeQuery(query);
    }

    public void update(String query) throws SQLException {
        connection = getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/libraryonline?"
                        + "user="+username+"&password="+password+"");
    }

}