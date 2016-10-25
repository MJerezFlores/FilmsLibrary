package filmLibrary;

public class modifyListFilms {

    public String addFilm(String id, String parameter){
        return modifyInfo(id, parameter);
    }

    public String deleteFilm(String id, String parameter){
        return modifyInfo(id, parameter);
    }

    public String modifyTile(String id, String parameter){
        return modifyInfo(id, parameter);
    }

    public String modifyInfo(String id, String parameter){
        return "{" +
                "'id': " + id + "," +
                "'parameter':" + parameter +
                "}";
    };
}
