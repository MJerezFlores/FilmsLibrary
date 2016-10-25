package filmLibrary.database;
import java.sql.*;

public abstract class Datamapper<T> {

    Database database = new Database("root", "1234");

    protected T load(String query) {
        try {
            T element = mapElement(database.executeQuery(query));
            database.closeConnection();
            return element;
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
        return null;
    }

    protected void createDeleteUpdate(String query) {
        try {
            database.executeUpdate(query);
            System.out.println("Succesfully executed");
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
    }

    protected abstract T mapElement(ResultSet resultSet);

}
