package filmLibrary.model;

import java.util.UUID;

public class User {

    private final String nickname;
    private final String email;
    private final String pass;
    private final UUID id;

    public User(String nickname, String email, String pass){
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
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

