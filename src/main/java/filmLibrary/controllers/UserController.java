package filmLibrary.controllers;

import filmLibrary.database.DatamapperUser;
import filmLibrary.model.User;

public class UserController {

    private DatamapperUser datamapperUser;

    public UserController() {
        this.datamapperUser = new DatamapperUser();
    }

    public User existUser(String nickname, String pass) throws Exception {
        return datamapperUser.existUser(nickname, pass);
    }


    public String createUser(User user){
        try {
            datamapperUser.createUser(user);
            return "User created";
        }catch (Exception e){
            return e.getMessage();
        }

    }

}