package filmLibrary.model;

public class User {
    private final String nickname;
    private final String email;
    private final String pass;
    private final String ipLocal;
    private final String ipRemote;
    public String ipConection;

    public User(String nickname, String email, String pass, String ipLocal, String ipRemote){
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.ipLocal = ipLocal;
        this.ipRemote = ipRemote;
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

    public String getIpLocal(){
        return ipLocal;
    }

    public String getIpRemote(){
        return ipRemote;
    }

    public void setIpConection(String ipConection) {
        this.ipConection = ipConection;
    }
}

