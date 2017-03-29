package filmLibrary.model;

public class Comment{

    private final String nickname;
    private String comment;
    private int id;
    private final int idFilm;


    public Comment(int id, int idFilm, String nickname, String comment){
        this.nickname = nickname;
        this.comment = comment;
        this.id = id;
        this.idFilm = idFilm;
    }

    public Comment(int idFilm, String nickname, String comment){
        this.nickname = nickname;
        this.comment = comment;
        this.idFilm = idFilm;
    }

    public String getComment(){
        return comment;
    }

    public String getNickname(){
        return nickname;
    }

    public int getId(){
        return id;
    }

    public int getIdFilm(){
        return idFilm;
    }

}
