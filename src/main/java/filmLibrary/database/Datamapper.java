package filmLibrary.database;
import java.sql.*;
import java.util.List;

public abstract class Datamapper<T> {

    Database database = new Database("root", "1234");

    protected T load(String query) {
        try {
            T element = mapElement(database.read(query));
            database.closeConnection();
            return element;
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
        return null;
    }

    protected void update(String query) {
        try {
            database.update(query);
            System.out.println("Succesfully executed");
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
    }

    protected List loadIdFilmsOnList(int idList) {
        String query = DatamapperListFilm.findIdFilmInListQuery(idList);
        try {
            List<Integer> element = DatamapperListFilm.mapIdFilms(database.read(query));
            database.closeConnection();
            return element;
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
        return null;
    }

    protected abstract T mapElement(ResultSet resultSet);

}
