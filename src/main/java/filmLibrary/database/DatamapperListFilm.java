package filmLibrary.database;

import filmLibrary.model.ListFilm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatamapperListFilm extends Datamapper<ListFilm>{

    public ListFilm getList(int id, int idUser){
        return load(createLoadQuery(id, idUser));
    }

    public void deleteListFilm(int id, int idUser) {
        update(createDeleteListOnFilmQuery(id));
        update(createDeleteListQuery(id, idUser));
    }

    public void changeTitle (int id,int idUser, String title){
        update(createChangeTitleQuery(id, idUser, title));
    }

    public void createListFilm (int id, int idUser, String title){
        update(createQuery(id, idUser, title));
    }

    public void addFilmInList(int idList, int idFilm){
        if (existAlreadyFilm(idList, idFilm)){
            update(addFilmQuery(idList, idFilm));
        }
    }

    public void deleteFilmInList(int idList, int idFilm){
        update(createDeleteFilmQuery(idList, idFilm));
    }

    @Override
    protected ListFilm mapElement(ResultSet rs) {
        try {
            while(rs.next()){
                List idFilms = loadIdFilmsOnList(rs.getInt("id"));
                ListFilm listFilm = new ListFilm(rs.getInt("id"), rs.getString("title"));
                for (Object id: idFilms) {
                    listFilm.add(new DatamapperFilm().getFilm((Integer) id));
                }
                return listFilm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static List<Integer> mapIdFilms(ResultSet rs) {
        List<Integer> idFilms = new ArrayList<Integer>();
        try {
            while (rs.next()){
                idFilms.add(rs.getInt("idFilm"));
            }
            return idFilms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static String findIdFilmInListQuery(int idList) {
        return "SELECT idFilm FROM listhavefilm WHERE idList = '"+idList+"'";
    }

    private boolean existAlreadyFilm(int idList, int idFilm) {
        return load(findFilmOnListQuery(idList, idFilm)) == null;
    }

    private String createDeleteFilmQuery(int idList, int idFilm) {
        return "DELETE FROM listhavefilm WHERE idList = '"+idList+"' AND idFilm = '"+idFilm+"'";
    }

    private String addFilmQuery(int idList, int idFilm) {
        return "INSERT INTO listhavefilm (idList, idFilm) VALUES ('"+idList+"', '"+idFilm+"')";
    }

    private String createLoadQuery(int id, int idUser) {
        return "SELECT * FROM listfilm WHERE id = '"+id+"' AND idUser = '"+idUser+"'";
    }

    private String createDeleteListQuery(int id, int idUser) {
        return "DELETE FROM listfilm WHERE id = '"+id+"' AND idUser = '"+idUser+"' ";
    }

    private String createDeleteListOnFilmQuery(int idList) {
        return "DELETE FROM listhavefilm WHERE idList = '"+idList+"'";
    }

    private String createChangeTitleQuery(int id, int idUser, String title) {
        return "UPDATE listfilm SET title='"+title+"' WHERE id='"+id+"' AND idUser = '"+idUser+"'";
    }

    private String createQuery(int id, int idUser, String title) {
        return "INSERT INTO listfilm (id, idUser, title) VALUES ('"+id+"', '"+idUser+"', '"+title+"')";
    }

    private String findFilmOnListQuery(int idList, int idFilm) {
        return "SELECT * FROM listhavefilm WHERE idList= '"+idList+"' AND idFilm = '"+idFilm+"'";
    }


}
