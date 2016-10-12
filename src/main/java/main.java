import spark.Spark;

import java.lang.reflect.Method;

import static spark.Spark.*;

public class main {
    public static void main(String[] args) {


        Spark.staticFiles.location("/public");

        get("/film/:filmID", (req, res) -> { return "{"+
                "'id': "+ req.params(":filmID")+","+
                "'title': 'Hypnosis',"+
                "'synopsis': 'asdasdadasdadasdasdasdasdasdasdasdasd'',"+
                "'genre': 'Science Fiction'',"+
                "'year': '1994',"+
                "'director': 'Sarah',"+
                "'actor': [{'name': 'Brad Pitt'}{'name': 'Leonardo Dicaprio''}{'name': 'Chris Evans''}],"+
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''},"+
                "'path': [{'path': 'path1'}{'path': 'path2''}{'path': 'path3'}]"+
                "'rating': '9.4',"+
                "}";});

        delete("/film/:filmID", (req, res) -> { return "{"+
                "'id': "+ req.params(":filmID")+","+
                "'title': 'Hypnosis',"+
                "'img': 'url',"+
                "'rating': '8.2'"+
                "}";});

        post("/film/add", (req, res) -> "{"+
                "'id' : 234,"+
                "'title': 'Hypnosis',"+
                "'synopsis': 'asdasdadasdadasdasdasdasdasdasdasdasd'',"+
                "'genre': 'Science Fiction'',"+
                "'year': '1994',"+
                "'director': 'Sarah',"+
                "'actor': [{'name': 'Brad Pitt'}{'name': 'Leonardo Dicaprio''}{'name': 'Chris Evans''}],"+
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''},"+
                "'path': [{'path': 'path1'}{'path': 'path2''}{'path': 'path3'}]"+
                "'rating': '9.4',"+
                "}");

        post("/film/:filmID", (req, res) -> { return "{"+
                "'id': "+ req.params(":filmID")+","+
                "'title': 'Hypnosis',"+
                "'synopsis': 'asdasdadasdadasdasdasdasdasdasdasdasd'',"+
                "'genre': 'Science Fiction'',"+
                "'year': '1994',"+
                "'director': 'Sarah',"+
                "'actor': [{'name': 'Brad Pitt'}{'name': 'Leonardo Dicaprio''}{'name': 'Chris Evans''}],"+
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''},"+
                "'path': [{'path': 'path1'}{'path': 'path2''}{'path': 'path3'}]"+
                "'rating': '9.4',"+
                "}";});

        get("/list/:listFilmID", (req, res) -> {return "{"+
                "'id': "+ req.params(":listFilmID")+","+
                "'title' :'Schedule',"+
                "'films': [" +

                "{'title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "]}";});

        delete("/list/:listFilmID", (req, res) -> {return "{"+
                "'id': "+ req.params(":listFilmID")+","+
                "'title' :'Schedule',"+
                "'films': [" +

                "{'title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "]}";});

        post("/list/:listFilmID/:action/:parameter/modify", (req, res) -> {
            try {
                modifyListFilms modifyList = new modifyListFilms();
                Method method = modifyList.getClass().getMethod(req.params(":action"), String.class, String.class);
                return (String) method.invoke(modifyList,req.params(":listFilmID"), req.params(":parameter"));
            }catch (Exception e){
                return e.getMessage();
            }
        });

        post("/list/create", (req, res) -> "{"+
                "'title' :'NewList',"+
                "'films': [" +

                "{'title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "{title': 'Hypnosis'," +
                "'image': {'largerImage': 'asasasa', 'smallImage': 'asdasdasd''}," +
                "'rating': '9.4'}" +

                "]}");

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
