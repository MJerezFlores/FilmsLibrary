package filmLibrary;

import filmLibrary.database.DatamapperListFilm;

public class ModifyListFilms {

    public void addFilm(int id, String filmID){
        new DatamapperListFilm().addFilmInList(id,Integer.parseInt(filmID));
    }

    public void deleteFilm(int id, String filmID){
        new DatamapperListFilm().deleteFilmInList(id,Integer.parseInt(filmID));
    }

    public void modifyTitle(int id, String title){
        new DatamapperListFilm().changeTitle(id,title);
    }

}
