import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import filmLibrary.controllers.FilmController;
import filmLibrary.controllers.ListsController;
import filmLibrary.controllers.UserController;
import filmLibrary.deserializer.ListFilmSerializer;
import filmLibrary.model.*;
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
        port(7070);
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        Spark.staticFiles.externalLocation(projectDir + staticDir);

//        Spark.staticFiles.location("/public");


        get("/api/films/comments/:filmID", (req, res) ->{
           return new Gson().toJson(filmController.getComments(req.params("filmID")));
        });

        delete("/api/films/comments/:id", (req, res) ->{
            return filmController.deleteComment(Integer.parseInt(req.params("id")));
        });

        post("/api/films/comments/add", (req, res) ->{
            return filmController.addComment(new Gson().fromJson(req.body(), Comment.class));
        });


        get("/api/user/:userID", (req, res) -> {
            User user = userController.getUser(req.params(":userID"));
            user.setIpConection(req.ip());
            return new Gson().toJson(user);
        });

        get("/api/user/:nickname/:pass", (req, res)-> {
            if (userController.existUser(req.params("nickname"), req.params("pass")) == null ) {
                res.status(400);
                return res.body();
            }else {
                User user = userController.existUser(req.params("nickname"), req.params("pass"));
                user.setIpConection(req.ip());
                return new Gson().toJson(user);
            }
        });

        post("/api/user/add", (req, res) ->{
            return userController.createUser(new Gson().fromJson(req.body(), User.class));
        });

        get("/api/film/all/:nickname", (req, res)-> {
            return new Gson().toJson(filmController.getFilms(req.params("nickname")));
        });

        get("/api/films/:filmID", (req, res) -> {
            FilmPublic film = filmController.getFilmGeneral(Integer.parseInt(req.params("filmID")));
            return new Gson().toJson(film);
        });

        post("/api/films/add/:filmID/:nickname", (req, res) -> {
            FilmPublic filmPublic= filmController.getFilmGeneral(Integer.parseInt(req.params("filmID")));
            filmController.createFromFilmPublic(Integer.parseInt(req.params("filmID")), req.params("nickname"), filmPublic);
            return "";
        });

        get("/api/films/main/:nickname", (req, res) -> {
            return new Gson().toJson(filmController.getMainFilms(req.params("nickname")));
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

        get("/api/films" , (req, res) -> {
            return new Gson().toJson(filmController.getFilms());
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


        get("/api/search/categories/:nickname", (req, res) -> {
            return new Gson().toJson(filmController.getCategories(req.params(":nickname")));
        });


        get("/api/search/genre/:genre", (req, res) -> {
            return new Gson().toJson(filmController.getFilmsGenre(req.params(":genre")));
        });

        get("/api/search/title/:title/:nickname", (req, res) -> {
            return new Gson().toJson(filmController.getFilmsTitle(req.params(":title"), req.params(":nickname")));
        });

        get("/api/search/title/:nickname", (req, res) -> {
            return new Gson().toJson(filmController.getFilmsTitle(req.params(":title"), req.params(":nickname")));
        });

        get("/api/search/order/:order/:nickname", (req, res) -> {
            return new Gson().toJson(filmController.getFilmsOrder(req.params(":order"), req.params(":nickname")));
        });

        post("/api/add-ip/:userID", (req, res) -> {
            Map<String, String> map = new Gson().fromJson(req.body(), Map.class);
            return userController.addIP(map.get("local"),map.get("remote"), req.params(":userID"));
        });



//        get("/api/search/:genre/:title/:order", (req, res) -> {
//            Gson gson = new Gson();
//            Map<String, String[]> map = req.queryMap()
//                    .get("genre", req.params("genre"))
//                    .get("title", req.params("title"))
//                    .get("order", req.params("order"))
//                    .toMap();
//
//
//            FilmSearch search = gson.fromJson(req.body(), FilmSearch.class);
//            return gson.toJsonTree(filmController.searchFilms(search), ListFilm.class);
//        });


        get("/*", (req, res)->  FileUtils.readFileToString(new File("./src/main/resources/public/html/frame.html"), "UTF-8"));


    }

}
