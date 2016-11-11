package filmLibrary.serializer;


import com.google.gson.*;
import filmLibrary.model.ListFilm;

import java.lang.reflect.Type;

public class ListFilmSerializer implements JsonSerializer<ListFilm> {

    @Override
    public JsonElement serialize(ListFilm listFilm, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jobject = new JsonObject();
        jobject.add("id", new JsonPrimitive(listFilm.getId()));
        jobject.add("idUser", new JsonPrimitive(listFilm.getNickname()));
        jobject.add("title", new JsonPrimitive(listFilm.getTitle()));
        return jobject;
    }
}