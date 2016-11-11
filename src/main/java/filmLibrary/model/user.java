package filmLibrary.model;

public class User {
    private final String nickname;
    private final String email;
    private final String pass;

    public User(String nickname, String email, String pass){
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}

