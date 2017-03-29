package filmLibrary.controllers;

import filmLibrary.ModifyListFilms;
import filmLibrary.database.DatamapperListFilm;
import filmLibrary.model.ListFilm;

import java.lang.reflect.Method;
import java.util.List;

public class ListsController {

    private DatamapperListFilm datamapperListFilm;

    public ListsController() {
        this.datamapperListFilm = new DatamapperListFilm();
    }

    public ListFilm getList(int filmID){
        return datamapperListFilm.getList(filmID);
    }

    public String delete(int filmID) {
        try {
            datamapperListFilm.deleteListFilm(filmID);
            return "List deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String modify(String listID, String action, String parameter) {
        try {
            ModifyListFilms modifyListFilms = new ModifyListFilms();
            Method method = modifyListFilms.getClass().getMethod(action, int.class ,String.class);
            method.invoke(modifyListFilms,Integer.parseInt(listID),parameter);
            return "List modified";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String create(ListFilm listFilm){
        try {
            datamapperListFilm.createListFilm(listFilm);
            return "List created";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public List<ListFilm> mainLists(String nickname) {
        return datamapperListFilm.getMainLists(nickname, 6);
    }

    public List<ListFilm> lists(String nickname) {
        return datamapperListFilm.getMainLists(nickname, 99);
    }
}
