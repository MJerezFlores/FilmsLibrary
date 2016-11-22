package filmLibrary.database;

import filmLibrary.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatamapperFilm extends Datamapper<Film> {

    public Film getFilm(int id) {
        return load(createLoadQuery(id));
    }

    public List<Film> getFilms() {
        return multipleLoad(createLoadAllQuery());
    }

    public void updateFilm(Film film) {
        update(createUpdateQuery(film.getId(), film.getTitle(), film.getSynopsis(), film.getYear(), film.getDirector(),
                film.getActors(), film.getRating(), film.getPath(), film.getUrlImage(), film.getGenre()));
    }

    public void deleteFilm(int id) {
        update(createDeleteQuery(id));
    }

    public void createFilm(Film film) {
        update(createFilmQuery(film.getTitle(), film.getSynopsis(), film.getYear(), film.getDirector(),
                film.getActors(), film.getRating(), film.getPath(), film.getUrlImage(), film.getGenre()));
    }

    public List<Film> searchByGenre(String genre) {
        return multipleLoad(searchGenreQuery(genre));
    }

    public List<Film> searchByTitle(String title) {
        return multipleLoad(searchTitleQuery(title));
    }

    public List<Film> searchByOrder(String order) {
        return multipleLoad(searchOrderQuery(order));
    }

    @Override
    protected Film mapElementSimple(ResultSet rs) {
        try {
            while(rs.next()) {
                return new Film.Builder(rs.getInt("id"), rs.getString("title"))
                        .synopsis(rs.getString("synopsis"))
                        .year(rs.getInt("year"))
                        .director(rs.getString("director"))
                        .actors(rs.getString("actors"))
                        .rating(rs.getFloat("rating"))
                        .path(rs.getString("path"))
                        .urlImage(rs.getString("urlImage"))
                        .genre(rs.getString("genre"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    private String createFilmQuery(String title, String synopsis, int year, String director, String actors, float rating, String path, String urlImage, String genre) {
        return "INSERT INTO film (title, synopsis, year, director, actors, rating, path, urlImage, genre) VALUES ('"+title+"', '"+synopsis+"', '"+year+"', '"+director+"', '"+actors+"', '"+rating+"', '"+path+"', '"+urlImage+"', '"+genre+"')";
    }

    private String createLoadQuery(int id) {
        return ("SELECT * FROM film WHERE id = '"+id+"'");
    }

    private String createLoadAllQuery() {
        return "SELECT * FROM film";
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM film where id = '"+id+"' ";
    }

    private String createUpdateQuery(int id, String title, String synopsis, int year, String director, String actors,
                                     float rating, String path, String urlImage, String genre) {
        return "UPDATE film SET title='"+title+"', synopsis='"+synopsis+"',year='"+year+"',director='"+director+"'," +
                "actors='"+actors+"', rating='"+rating+"', path='"+path+"', urlImage='"+urlImage+"', genre='"+genre+"' WHERE id='"+id+"'";
    }

    @Override
    protected Film mapElementExtraLoop(ResultSet rs) {
        try {
                return new Film.Builder(rs.getInt("id"), rs.getString("title"))
                        .synopsis(rs.getString("synopsis"))
                        .year(rs.getInt("year"))
                        .director(rs.getString("director"))
                        .actors(rs.getString("actors"))
                        .rating(rs.getFloat("rating"))
                        .path(rs.getString("path"))
                        .urlImage(rs.getString("urlImage"))
                        .genre(rs.getString("genre"))
                        .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<String> getCategories() {
        return loadGenres(createCategoriesQuery());
    }

    private String createCategoriesQuery() {
        return "SELECT DISTINCT genre FROM film";
    }

    protected static String mapElementGenre(ResultSet rs) {
        try {
            return rs.getString("genre");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Film> getFilms(String filter) {
        return multipleLoad(createLoadGenreQuery(filter));
    }

    private String createLoadGenreQuery(String genre) {
        return "SELECT * FROM film WHERE genre = '"+genre+"'";
    }


    private String searchGenreQuery(String genre) {
        return "SELECT * FROM film WHERE genre = '"+genre+"'";
    }

    private String searchTitleQuery(String title) {
        return "SELECT * FROM film WHERE title LIKE '%"+title+"%'";
    }

    private String searchOrderQuery(String order) {
        return "SELECT * FROM film ORDER BY "+order+"";
    }
}

