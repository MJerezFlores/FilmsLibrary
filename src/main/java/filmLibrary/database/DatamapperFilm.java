package filmLibrary.database;

import filmLibrary.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperFilm extends Datamapper<Film> {

    public Film getFilm(int id) {
        return load(createLoadQuery(id));
    }

    public void updateFilm(Film film) {
        update(createUpdateQuery(film.getId(), film.getTitle(), film.getSynopsis(), film.getYear(), film.getDirector(),
                film.getActors(), film.getRating(), film.getPath(), film.getUrlImage()));
    }

    public void deleteFilm(int id) {
        update(createDeleteQuery(id));
    }

    public void createFilm(Film film) {
        update(createFilmQuery(film.getTitle(), film.getSynopsis(), film.getYear(), film.getDirector(),
                film.getActors(), film.getRating(), film.getPath(), film.getUrlImage()));
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

    private String createFilmQuery(String title, String synopsis, int year, String director, String actors, float rating, String path, String urlImage) {
        return "INSERT INTO film (title, synopsis, year, director, actors, rating, path, urlImage) VALUES ('"+title+"', '"+synopsis+"', '"+year+"', '"+director+"', '"+actors+"', '"+rating+"', '"+path+"', '"+urlImage+"')";
    }

    private String createLoadQuery(int id) {
        return ("SELECT * FROM film WHERE id = '"+id+"'");
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM film where id = '"+id+"' ";
    }

    private String createUpdateQuery(int id, String title, String synopsis, int year, String director, String actors,
                                     float rating, String path, String urlImage) {
        return "UPDATE film SET title='"+title+"', synopsis='"+synopsis+"',year='"+year+"',director='"+director+"'," +
                "actors='"+actors+"', rating='"+rating+"', path='"+path+"', urlImage='"+urlImage+"' WHERE id='"+id+"'";
    }


}

