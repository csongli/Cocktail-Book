package hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Pair;

import java.lang.reflect.Type;

/** Utility class for serializing {@code Pair} generic objects. */
public class PairSerializer implements JsonSerializer<Pair> {

    /**
     * Constructor for the Pair Serializer.
     */
    public PairSerializer() {
        super();
    }

    /**
     * The serializing method for generic pair objects.
     * @param value the object to serialize.
     * @param type the {@code type} of the objects to serialize.
     * @param context the serialization context.
     * @return the serialized {@code JsonElement} object.
     */
    @Override
    public JsonElement serialize(final Pair value, final Type type, final JsonSerializationContext context) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("key", context.serialize(value.getKey()));
        jsonObj.add("value", context.serialize(value.getValue()));
        return jsonObj;
    }
}