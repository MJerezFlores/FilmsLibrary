package filmLibrary.database;

import filmLibrary.model.ListFilm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperListFilm extends Datamapper<ListFilm>{

    public ListFilm getList(int id){
        return load(createLoadQuery(id));
    }

    private String createLoadQuery(int id) {
        return "SELECT * FROM listfilm WHERE id = '"+id+"'";
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

    public void deleteListFilm(int id) {
        updateOrDelete(createDeleteQuery(id));
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM listfilm WHERE id = '"+id+"' ";
    }

    public void changeTitle (int id, String title){
        updateOrDelete(createChangeTitleQuery(id, title));
    }

    private String createChangeTitleQuery(int id, String title) {
        return "UPDATE listfilm SET title='"+title+"' WHERE id='"+id+"'";
    }

}
