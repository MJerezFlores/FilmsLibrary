package filmLibrary.database;

import filmLibrary.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperFilm extends Datamapper<Film> {

    public Film getFilm(int id) {
        return load(createLoadQuery(id));
    }

    private String createLoadQuery(int id) {
        return ("SELECT * FROM film WHERE id = '"+id+"'");
    }

    @Override
    protected Film mapElement(ResultSet rs) {
        try {
            while(rs.next()){
                return new Film.Builder(rs.getInt("id"), rs.getString("title"))
                        .synopsis(rs.getString("synopsis"))
                        .year(rs.getInt("year"))
                        .director(rs.getString("director"))
                        .actors(rs.getString("actors"))
                        .rating(rs.getFloat("rating"))
                        .path(rs.getString("path"))
                        .urlImage(rs.getString("urlImage"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void deleteFilm(int id) {
        updateOrDelete(createDeleteQuery(id));
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM film where id = '"+id+"' ";
    }

    public void updateFilm(int id, String title, String synopsis, int year, String director,
                           String actors, float rating, String path, String urlImage) {
        String query = createUpdateQuery(id, title, synopsis, year, director, actors, rating, path, urlImage);
        updateOrDelete(query);
    }

    private String createUpdateQuery(int id, String title, String synopsis, int year, String director, String actors,
                                     float rating, String path, String urlImage) {
        return "UPDATE film SET title='"+title+"', synopsis='"+synopsis+"',year='"+year+"',director='"+director+"'," +
                "actors='"+actors+"', rating='"+rating+"', path='"+path+"', urlImage='"+urlImage+"' WHERE id='"+id+"'";
    }

}

