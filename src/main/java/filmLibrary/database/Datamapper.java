package filmLibrary.database;
import filmLibrary.model.Search;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Datamapper<T> {

    Database database = new Database("root", "1234");

    protected T load(String query) {
        try {
            T element = mapElementSimple(database.read(query));
            database.closeConnection();
            return element;
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
        return null;
    }

    protected List<T> multipleLoad(String query) {
        try {
            LinkedList list = new LinkedList();
            ResultSet resultSet = database.read(query);
            while(resultSet.next()){
                list.add(mapElementExtraLoop(resultSet));
            }
            database.closeConnection();
            return list;
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

    protected List<String> loadGenres(String query) {
        try {
            LinkedList list = new LinkedList();
            ResultSet resultSet = database.read(query);
            while(resultSet.next()){
                list.add(DatamapperFilm.mapElementGenre(resultSet));
            }
            database.closeConnection();
            return list;
        } catch (Exception e) {
            System.out.println("ExceptionBaseDeDatos: " + e.getMessage());
        }
        return null;
    }



    protected abstract T mapElementSimple(ResultSet resultSet);
    protected abstract T mapElementExtraLoop(ResultSet resultSet);

    public List<T> search(Search search){
        return multipleLoad("SELECT * FROM film WHERE " + search.getDataBaseExpression());
    };




}
