package filmLibrary.database;

import filmLibrary.model.ListFilm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperListFilm extends Datamapper<ListFilm>{

    public ListFilm getList(int id, int idUser){
        return load(createLoadQuery(id, idUser));
    }

    public void deleteListFilm(int id, int idUser) {
        createDeleteUpdate(createDeleteQuery(id, idUser));
    }

    public void changeTitle (int id,int idUser, String title){
        createDeleteUpdate(createChangeTitleQuery(id, idUser, title));
    }

    public void createListFilm (int id, int idUser, String title){
        createDeleteUpdate(createQuery(id, idUser, title));
    }

    public void addFilmInList(int idList, int idFilm){
        if (load(createQuery(idList, idFilm)) == null){
            createDeleteUpdate(createAddFilmQuery(idList, idFilm));
        }
    }

    public void deleteFilmInList(int idList, int idFilm){
        createDeleteUpdate(createDeleteFilmQuery(idList, idFilm));
    }

    @Override
    protected ListFilm mapElement(ResultSet rs) {
        try {
            while(rs.next()){
                return new ListFilm(rs.getInt("id"), rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createDeleteFilmQuery(int idList, int idFilm) {
        return "DELETE FROM listhavefilm WHERE idList = '"+idList+"' AND idFilm = '"+idFilm+"'";
    }

    private String createAddFilmQuery(int idList, int idFilm) {
        return "INSERT INTO listhavefilm (idList, idFilm) VALUES ('"+idList+"', '"+idFilm+"')";
    }

    private String createLoadQuery(int id, int idUser) {
        return "SELECT * FROM listfilm WHERE id = '"+id+"' AND idUser = '"+idUser+"'";
    }

    private String createDeleteQuery(int id, int idUser) {
        return "DELETE FROM listfilm WHERE id = '"+id+"', idUser = '"+idUser+"' ";
    }

    private String createChangeTitleQuery(int id, int idUser, String title) {
        return "UPDATE listfilm SET title='"+title+"' WHERE id='"+id+"' AND idUser = '"+idUser+"'";
    }

    private String createQuery(int id, int idUser, String title) {
        return "INSERT INTO listfilm (id, idUser, title) VALUES ('"+id+"', '"+idUser+"', '"+title+"')";
    }

    private String createQuery(int idList, int idFilm) {
        return "SELECT * FROM listhavefilm WHERE idList= '"+idList+"' AND idFilm = '"+idFilm+"'";
    }

}
