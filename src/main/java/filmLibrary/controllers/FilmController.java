package filmLibrary.controllers;
import filmLibrary.database.DatamapperFilm;
import filmLibrary.model.Film;

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

}
