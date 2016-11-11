package filmLibrary.database;

import filmLibrary.model.ListFilm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatamapperListFilm extends Datamapper<ListFilm>{

    public ListFilm getList(int id){
        return load(createLoadQuery(id));
    }

    public void deleteListFilm(int id) {
        update(createDeleteListOnFilmQuery(id));
        update(createDeleteListQuery(id));
    }

    public void changeTitle (int id, String title){
        update(createChangeTitleQuery(id, title));
    }

    public void createListFilm (ListFilm listFilm){
        update(createQuery(listFilm.getNickname(), listFilm.getTitle()));
    }

    public void addFilmInList(int idList, int idFilm){
        if (existAlreadyFilm(idList, idFilm)){
            update(addFilmQuery(idList, idFilm));
        }
    }

    public List<ListFilm> getMainLists(String nickname, int limit) {
        return multipleLoad(getFirstListsQuery(nickname, limit));
    }

    public void deleteFilmInList(int idList, int idFilm){
        update(createDeleteFilmQuery(idList, idFilm));
    }

    @Override
    protected ListFilm mapElement(ResultSet rs) {
        try {
            ListFilm listFilm = new ListFilm(rs.getInt("id"), rs.getString("nickname"), rs.getString("title"));
            List idFilms = loadIdFilmsOnList(rs.getInt("id"));
            for (Object id: idFilms) {
                listFilm.add(new DatamapperFilm().getFilm((Integer) id));
            }
            return listFilm;
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

    private String createLoadQuery(int id) {
        return "SELECT * FROM listfilm WHERE id = '"+id+"'";
    }

    private String createDeleteListQuery(int id) {
        return "DELETE FROM listfilm WHERE id = '"+id+"'";
    }

    private String createDeleteListOnFilmQuery(int idList) {
        return "DELETE FROM listhavefilm WHERE idList = '"+idList+"'";
    }

    private String createChangeTitleQuery(int id, String title) {
        return "UPDATE listfilm SET title='"+title+"' WHERE id='"+id+"'";
    }

    private String createQuery(String nickname, String title) {
        return "INSERT INTO listfilm (nickname, title) VALUES ('"+nickname+"', '"+title+"')";
    }

    private String findFilmOnListQuery(int idList, int idFilm) {
        return "SELECT * FROM listhavefilm WHERE idList= '"+idList+"' AND idFilm = '"+idFilm+"'";
    }


    private String getFirstListsQuery(String nickname, int limit) {
        return "SELECT * FROM listfilm WHERE nickname= '" + nickname + "' LIMIT " + limit;
    }
}
