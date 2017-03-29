package filmLibrary.database;

import filmLibrary.model.Film;
import filmLibrary.model.FilmBuilder;
import filmLibrary.model.FilmPublic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatamapperFilmPublic extends Datamapper<FilmPublic> {

    public FilmPublic getFilmGeneral(int id) {
        return load(createLoadGeneralQuery(id));
    }

    public List<FilmPublic> getAllFilms() {
        return multipleLoad(createLoadAllQuery());
    }

    public void createFromPublicFilm(int id, String nickname, FilmPublic filmPublic) {
        update(createUpdateQuery(id,nickname,filmPublic.getActors(),filmPublic.getDirector(), filmPublic.getGenre(),
                filmPublic.getSynopsis(), filmPublic.getTitle(), filmPublic.getUrlImage(), filmPublic.getYear()));
    }

    private String createUpdateQuery(int id, String nickname, String actors, String director, String genre, String synopsis, String title, String urlImage, int year) {
        return "INSERT INTO film (title, synopsis, year, director, actors, urlImage, genre, nickname, id)" +
                " VALUES ('"+title+"', '"+synopsis+"', '"+year+"', '"+director+"', '"+actors+"','"+urlImage+"', " +
                "'"+genre+"', '"+nickname+"', '"+id+"')";
    }

    private String createLoadGeneralQuery(int id) {
        return ("SELECT * FROM filmpublic WHERE id = '"+id+"'");
    }

    private String createLoadAllQuery() {
        return "SELECT * FROM filmpublic";
    }

    @Override
    protected FilmPublic mapElementSimple(ResultSet rs) {
        try {
            while(rs.next()) {
                return mapFilm(rs, new FilmPublic.Builder(rs.getInt("id"), rs.getString("title")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    protected Film mapElementFilmSimple(ResultSet rs) {
        try {
            while(rs.next()) {
                return mapFilm(rs, new Film.Builder(rs.getInt("id"), rs.getString("title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private <Type>Type mapFilm(ResultSet rs, FilmBuilder<Type> builder) throws SQLException {
        return (Type) builder.synopsis(rs.getString("synopsis"))
                .synopsis(rs.getString("synopsis"))
                .year(rs.getInt("year"))
                .director(rs.getString("director"))
                .actors(rs.getString("actors"))
                .urlImage(rs.getString("urlImage"))
                .genre(rs.getString("genre"))
                .nickname(rs.getString("nickname"))
                .build();
    }

    @Override
    protected FilmPublic mapElementExtraLoop(ResultSet rs) {
        try {
            return new FilmPublic.Builder(rs.getInt("id"), rs.getString("title"))
                    .synopsis(rs.getString("synopsis"))
                    .year(rs.getInt("year"))
                    .director(rs.getString("director"))
                    .actors(rs.getString("actors"))
                    .urlImage(rs.getString("urlImage"))
                    .genre(rs.getString("genre"))
                    .nickname(rs.getString("nickname"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
