package filmLibrary.database;

import filmLibrary.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperUser extends Datamapper<User>{

    public User getUser(int id){
        return load(createLoadQuery(id));
    }

    public void deleteUser(int id) {
        createDeleteUpdate(createDeleteQuery(id));
    }

    public void updateUser(int id, String nickname, String email, String password){
        createDeleteUpdate(createChangeTitleQuery(id, nickname, email, password));
    }

    public void createUser(int id, String nickname, String email, String password){
        createDeleteUpdate(createUserQuery(id, nickname, email, password));
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

    private String createUserQuery(int id, String nickname, String email, String password) {
        return "INSERT INTO user (id, nickname, email, password) VALUES ('"+id+"', '"+nickname+"', '"+email+"', '"+password+"')";
    }

    private String createLoadQuery(int id) {
        return "SELECT * FROM user WHERE id = '"+id+"'";
    }

    private String createDeleteQuery(int id) {
        return "DELETE FROM user WHERE id = '"+id+"' ";
    }

    private String createChangeTitleQuery(int id, String nickname, String email, String password) {
        return "UPDATE user SET nickname='"+nickname+"', email='"+email+"', password='"+password+"' WHERE id='"+id+"'";
    }
}
