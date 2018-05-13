package hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Pair;

import java.lang.reflect.Type;

public class PairSerializer implements JsonSerializer<Pair> {
    public PairSerializer() {
        super();
    }

    @Override
    public JsonElement serialize(final Pair value, final Type type, final JsonSerializationContext context) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("key", context.serialize(value.getKey()));
        jsonObj.add("value", context.serialize(value.getValue()));
        return jsonObj;
    }
}