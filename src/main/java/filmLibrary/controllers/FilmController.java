package filmLibrary.controllers;

import filmLibrary.database.DatamapperComments;
import filmLibrary.database.DatamapperFilm;
import filmLibrary.database.DatamapperFilmPublic;
import filmLibrary.model.Comment;
import filmLibrary.model.Film;
import filmLibrary.model.FilmPublic;
import filmLibrary.model.Search;

import java.util.List;

public class FilmController {

    private DatamapperFilm datamapperFilm;
    private DatamapperFilmPublic datamapperFilmPublic;
    private DatamapperComments datamapperComment;

    public FilmController() {
        this.datamapperFilm = new DatamapperFilm();
        this.datamapperFilmPublic = new DatamapperFilmPublic();
        this.datamapperComment = new DatamapperComments();
    }

    public String create(Film film) {
        try {
            datamapperFilm.createFilm(film);
            return "Film created";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String delete(int filmID) {
        try {
            datamapperFilm.deleteFilm(filmID);
            return "Film deleted";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String modify(Film film) {
        try {
            datamapperFilm.updateFilm(film);
            return "Film updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Film getFilm(int filmID) {
        return datamapperFilm.getFilm(filmID);
    }

    public FilmPublic getFilmGeneral(int filmID) {
        return datamapperFilmPublic.getFilmGeneral(filmID);

    }

    public List<Film> getFilms(String nickname) {
        return datamapperFilm.getAllFilms(nickname);
    }

    public List<FilmPublic> getFilms() {
        return datamapperFilmPublic.getAllFilms();
    }

    public List<String> getCategories(String nickname) {
        return datamapperFilm.getCategories(nickname);
    }


    public List<Film> getSearchCategories(String filter) {
        return datamapperFilm.getFilms(filter);
    }


    public List<Film> searchFilms(Search search) {
        return datamapperFilm.search(search);
    }

    public List<Film> getFilmsGenre(String genre) {
        return datamapperFilm.searchByGenre(genre);
    }

    public List<Film> getFilmsTitle(String title, String nickname) {
        return datamapperFilm.searchByTitle(title, nickname);
    }

    public List<Film> getFilmsOrder(String order, String nickname) {
        return datamapperFilm.searchByOrder(order, nickname);
    }


    public void createFromFilmPublic(int filmID, String nickname, FilmPublic filmPublic) {
        datamapperFilmPublic.createFromPublicFilm(filmID, nickname, filmPublic);
    }

    public List<Film> getMainFilms(String nickname) {
        return datamapperFilm.getMainFilms(nickname);
    }

    public List<Comment> getComments(String filmID) {
        return datamapperComment.getComments(filmID);
    }

    public String addComment(Comment comment) {
        try {
            datamapperComment.createComment(comment);
            return "Film commented";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteComment(int id) {
        try {
            datamapperComment.deleteComment(id);
            return "Comment deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
