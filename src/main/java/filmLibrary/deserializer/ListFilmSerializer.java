package filmLibrary.deserializer;

import com.google.gson.*;
import filmLibrary.model.Film;
import filmLibrary.model.ListFilm;

import java.lang.reflect.Type;

public class ListFilmSerializer implements JsonDeserializer<ListFilm>, JsonSerializer<ListFilm>{

    @Override
    public ListFilm deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jobject = (JsonObject) jsonElement;

        ListFilm listFilm =  new ListFilm(
                jobject.get("nickname").getAsString(),
                jobject.get("title").getAsString()
        );
        if(jobject.has("films"))
            jobject.get("films").getAsJsonArray().forEach(jsonElement1 -> listFilm.add(new Gson().fromJson(jsonElement, Film.class)));

        return listFilm;
    }

    @Override
    public JsonElement serialize(ListFilm listFilm, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jobject = new JsonObject();
        jobject.add("id", new JsonPrimitive(listFilm.getId()));
        jobject.add("nickname", new JsonPrimitive(listFilm.getNickname()));
        jobject.add("title", new JsonPrimitive(listFilm.getTitle()));
        jobject.add("films", new Gson().toJsonTree(listFilm, ListFilm.class));
        return jobject;
    }
}
