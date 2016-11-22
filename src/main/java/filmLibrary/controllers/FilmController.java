package filmLibrary.controllers;

import filmLibrary.database.DatamapperFilm;
import filmLibrary.model.Film;
import filmLibrary.model.Search;

import java.util.List;

public class FilmController {

    private DatamapperFilm datamapperFilm;

    public FilmController() {
        this.datamapperFilm = new DatamapperFilm();
    }

    public String create(Film film){
        try {
            datamapperFilm.createFilm(film);
            return "Film created";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String delete(int filmID) {
        try {
            datamapperFilm.deleteFilm(filmID);
            return "Film deleted";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    public String modify(Film film) {
        try {
            datamapperFilm.updateFilm(film);
            return "Film updated";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public Film getFilm(int filmID){
        return datamapperFilm.getFilm(filmID);
    }

    public List<Film> getFilms() {
        return datamapperFilm.getFilms();
    }

    public List<String> getCategories() {
        return datamapperFilm.getCategories();
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

    public List<Film> getFilmsTitle(String title) {
        return datamapperFilm.searchByTitle(title);
    }

    public List<Film> getFilmsOrder(String order) {
        return datamapperFilm.searchByOrder(order);
    }
}
