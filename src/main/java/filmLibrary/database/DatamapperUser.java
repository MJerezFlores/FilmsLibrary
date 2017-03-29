package filmLibrary.database;

import filmLibrary.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatamapperUser extends Datamapper<User>{

    public User existUser(String nickname, String pass){
        return load(createLoadQuery(nickname, pass));
    }

    public void createUser(User user){
        if(existUser(user.getNickname(), user.getPass()) == null)
            update(createUserQuery(user.getNickname(),user.getEmail(),user.getPass()));
    }

    public void addIpUser(String local, String remote,String nickname) {
        update(createAddIpQuery(local,remote, nickname));
    }

    public User getUser(String nickname) {
        return load(createLoadQuery(nickname));
    }

    @Override
    protected User mapElementSimple(ResultSet rs) {
        try {
            while(rs.next()){
                return new User(rs.getString("nickname"), rs.getString("email"), rs.getString("password"), rs.getString("ipLocal"), rs.getString("ipRemote"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected User mapElementExtraLoop(ResultSet rs) {
        try {
            while(rs.next()){
                return new User(rs.getString("nickname"), rs.getString("email"), rs.getString("password"), rs.getString("ipLocal"), rs.getString("ipRemote"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createUserQuery(String nickname, String email, String password) {
        return "INSERT INTO user (nickname, email, password) VALUES ('"+nickname+"', '"+email+"', '"+password+"')";
    }

    private String createLoadQuery(String nickname, String pass) {
        return "(SELECT * FROM user WHERE nickname = '"+nickname+"' AND password = '"+pass+"')";
    }

    private String createLoadQuery(String nickname) {
        return "SELECT * FROM user WHERE nickname = '"+nickname+"'";
    }

    private String createDeleteQuery(String nickname, String pass) {
        return "(DELETE FROM user WHERE nickname = '"+nickname+"', password = '"+pass+"')";
    }

    private String createAddIpQuery(String local,String remote, String nickname) {
        return "UPDATE user SET ipLocal='"+local+"', ipRemote='"+remote+"' WHERE nickname='"+nickname+"' ";
    }


}
