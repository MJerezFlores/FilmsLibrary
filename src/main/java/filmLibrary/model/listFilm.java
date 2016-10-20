package filmLibrary.model;

import java.util.LinkedList;
import java.util.List;

public class ListFilm extends LinkedList<Film>{

    private int id;
    private String title;
    private List<Film> list = new LinkedList<>();


    public ListFilm(int id, String title){
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
