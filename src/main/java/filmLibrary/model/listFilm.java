package filmLibrary.model;

import java.util.LinkedList;
import java.util.List;

public class ListFilm extends LinkedList<Film>{

    private int id;
    private String title;
    private final String nickname;
    private List<Film> list = new LinkedList<>();

    public ListFilm(int id, String nickname, String title){
        this.id = id;
        this.nickname = nickname;
        this.title = title;
    }

    public ListFilm(String nickname, String title){
        this.nickname = nickname;
        this.title = title;
    }

    public String getNickname(){
        return nickname;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
