package hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance;

/*-
MIT License

Copyright (c) 2018 Csongor Kolozsvári

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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