import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import filmLibrary.controllers.FilmController;
import filmLibrary.controllers.ListsController;
import filmLibrary.controllers.UserController;
import filmLibrary.deserializer.ListFilmSerializer;
import filmLibrary.model.Film;
import filmLibrary.model.FilmSearch;
import filmLibrary.model.ListFilm;
import filmLibrary.model.User;
import org.apache.commons.io.FileUtils;
import spark.Spark;

import java.io.File;
import java.util.Map;

import static spark.Spark.*;

public class main {

    private static final FilmController filmController = new FilmController();
    private static final ListsController listsController = new ListsController();
    private static final UserController userController = new UserController();

    public static void main(String[] args) {


        Spark.staticFiles.location("/public");


        get("api/user/:nickname/:pass", (req, res)-> {
            if (userController.existUser(req.params("nickname"), req.params("pass")) == null ) {
                res.status(400);
                return res.body();
            }else {
                return new Gson().toJson(userController.existUser(req.params("nickname"), req.params("pass")));
            }
        });

        post("api/user/add", (req, res) ->{
            return userController.createUser(new Gson().fromJson(req.body(), User.class));
        });

        get("api/film/all", (req, res)-> {
            return new Gson().toJson(filmController.getFilms());
        });

        get("/api/film/:filmID", (req, res) -> {
            Film film = filmController.getFilm(Integer.parseInt(req.params("filmID")));
            return new Gson().toJson(film);
        });

        delete("/api/film/:filmID", (req, res) -> {
            return filmController.delete(Integer.parseInt(req.params("filmID")));
        });

        post("/api/film/add", (req, res) ->{
            return filmController.create(new Gson().fromJson(req.body(), Film.class));
        });

        post("/api/film/edit", (req, res) -> {
            return filmController.modify(new Gson().fromJson(req.body(), Film.class));
        });

        get("/api/list/:listFilmID", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.getList(Integer.parseInt(req.params(":listFilmID"))));
        });

        delete("/api/list/:listFilmID", (req, res) -> {
            return listsController.delete(Integer.parseInt(req.params(":listFilmID")));
        });

        post("/api/list/:listFilmID/modify", (req, res) -> {
            Map<String, String> map = new Gson().fromJson(req.body(), Map.class);
            return listsController.modify(req.params(":listFilmID"), map.get("action"), map.get("parameter"));
        });

        get("/api/list/main/:nickname", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.mainLists(req.params(":nickname")));
        });

        get("/api/list/all/:nickname", (req, res) -> {
            return new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .toJson(listsController.lists(req.params(":nickname")));
        });

        post("/api/list/create", (req, res) -> {
            ListFilm listFilm = new GsonBuilder()
                    .registerTypeAdapter(ListFilm.class, new ListFilmSerializer())
                    .create()
                    .fromJson(req.body(), ListFilm.class);
            return listsController.create(listFilm);
        });


        get("/api/search/categories", (req, res) -> {
            return new Gson().toJson(filmController.getCategories());
        });


       get("/api/search/categories/:filter", (req, res) -> {
            return new Gson().toJson(filmController.getSearchCategories(req.params(":filter")));
       });

        post("/api/search", (req, res) -> {
            Gson gson = new Gson();
            FilmSearch search = gson.fromJson(req.body(), FilmSearch.class);
            return gson.toJsonTree(filmController.searchFilms(search), ListFilm.class);
        });

//
//                "{"+
//                    "'filter': 'rating',"+
//                    "'category': 'action',"+
//                    "'text': 'Dirty',"+
//                    "}"




        get("/api/search/filters", (req, res) -> "{"+
                "'filters': " + "[" +
                "{'filter': 'rating'}" +
                "{'filter': 'year''}]',"+
                "}");

        get("/*", (req, res)->  FileUtils.readFileToString(new File("./src/main/resources/public/html/frame.html"), "UTF-8"));



    }

}
