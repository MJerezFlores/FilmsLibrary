import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import filmLibrary.controllers.FilmController;
import filmLibrary.controllers.ListsController;
import filmLibrary.deserializer.ListFilmSerializer;
import filmLibrary.model.Film;
import filmLibrary.model.ListFilm;
import spark.Spark;

import java.util.Map;

import static spark.Spark.*;

public class main {

    private static final FilmController filmController = new FilmController();
    private static final ListsController listsController = new ListsController();

    public static void main(String[] args) {


        Spark.staticFiles.location("/public");

        get("/film/:filmID", (req, res) -> {
            Film film = filmController.getFilm(Integer.parseInt(req.params("filmID")));
            return new Gson().toJson(film);
        });

        delete("/film/:filmID", (req, res) -> {
            return filmController.delete(Integer.parseInt(req.params("filmID")));
        });

        post("/film/add", (req, res) ->{
            return filmController.create(new Gson().fromJson(req.body(), Film.class));
        }  );

        post("/film/edit", (req, res) -> {
            return filmController.modify(new Gson().fromJson(req.body(), Film.class));
        });


        get("/list/:listFilmID", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.getList(Integer.parseInt(req.params(":listFilmID"))));
        });

        delete("/list/:listFilmID", (req, res) -> {
            return listsController.delete(Integer.parseInt(req.params(":listFilmID")));
        });

        post("/list/:listFilmID/modify", (req, res) -> {
            Map<String, String> map = new Gson().fromJson(req.body(), Map.class);
            return listsController.modify(req.params(":listFilmID"), map.get("action"), map.get("parameter"));
        });

        get("/list/main/:nickname", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.mainLists(req.params(":nickname")));
        });

        get("/list/all/:nickname", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.lists(req.params(":nickname")));
        });

        post("/list/create", (req, res) -> {
            ListFilm listFilm = new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .fromJson(req.body(), ListFilm.class);
            return listsController.create(listFilm);
        });

        get("/search", (req, res) -> "{"+
                "'filter': 'rating',"+
                "'category': 'action',"+
                "'text': 'Dirty',"+
                "}");

        get("/search/categories", (req, res) -> "{"+
                "'categories': " + "[" +
                "{'category': 'action'}" +
                "{'category': 'romance''}" +
                "{'category': 'comedy'}]',"+
                "}");

        get("/search/filters", (req, res) -> "{"+
                "'filters': " + "[" +
                "{'filter': 'rating'}" +
                "{'filter': 'year''}]',"+
                "}");



    }


}
