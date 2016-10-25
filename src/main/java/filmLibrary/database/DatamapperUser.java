package filmLibrary.database;

import filmLibrary.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperUser extends Datamapper<User>{

    public User getUser(int id){
        return load(createLoadQuery(id));
    }

    private String createLoadQuery(int id) {
        return "SELECT * FROM user WHERE id = '"+id+"'";
    }
    @Override
    protected User mapElement(ResultSet rs) {
        try {
            while(rs.next()){
                return new User(rs.getString("nickname"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        updateOrDelete(createDeleteQuery(id));
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM user WHERE id = '"+id+"' ";
    }

    public void updateUser (int id, String nickname, String email, String password){
        updateOrDelete(createChangeTitleQuery(id, nickname, email, password));
    }

    private String createChangeTitleQuery(int id, String nickname, String email, String password) {
        return "UPDATE user SET nickname='"+nickname+"', email='"+email+"', password='"+password+"' WHERE id='"+id+"'";
    }
}
