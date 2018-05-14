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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Utility class for reading and writing JSONs. */
public class JSONUtils {

    /** Logger. */
    public static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);
    /** GSON Builder object. */
    final GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().serializeNulls().enableComplexMapKeySerialization();
    /** JSON serializer and deserializer. */
    final Gson gson = gsonBuilder.registerTypeAdapter(Pair.class, new PairSerializer()).setPrettyPrinting().create();

    /**
     * Serializes a profile.
     * @param profile the {@code Profile} to serialize.
     * @return the JSON as a String.
     */
    public String serializeProfile(Profile profile){
        String json = gson.toJson(profile);
        return json;
    }

    /**
     * Deserializes a profile.
     * @param json the JSON containing the serialized {@code Profile}.
     * @return the Profile read from the JSON.
     */
    public Profile deserializeProfile(String json){
        Profile profile = new Profile();
        profile = gson.fromJson(json, Profile.class);
        logger.info("The deserialized profile:\n{}", profile);
        return profile;
    }

    /**
     * Deserializes a cocktail list.
     * @param json the JSON containing the serialized {@code Cocktail} objects.
     * @return the list of cocktails read from the JSON.
     */
    public List<CocktailEntity> deserializeCocktailList(String json){
        Type listType = new TypeToken<ArrayList<CocktailEntity>>(){}.getType();
        List<CocktailEntity> cocktailEntities = gson.fromJson(json, listType);
        logger.info("Deserialized all cocktailEntities: " + cocktailEntities);
        return cocktailEntities;
    }

    /**
     * Deserializes a ingredient list.
     * @param json the JSON containing the serialized {@code Ingredient} objects.
     * @return the list of ingredients read from the JSON.
     */
    public List<Ingredient> deserializeIngredientList(String json){
        Type listType = new TypeToken<ArrayList<Ingredient>>(){}.getType();
        List<Ingredient> ingredients = gson.fromJson(json, listType);
        logger.info("Deserialized all ingredients: " + ingredients);
        return ingredients;
    }

    /**
     * Reads a JSON into a String.
     * @param filePath relative path to the file containing the JSON.
     * @return String containing the JSON.
     */
    public String readJSONfromFile (String filePath){
        logger.info("Reading file: " + filePath);
        String result = "";
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath);
            result = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(filePath),"UTF-8");
        }
        catch(IOException e){
            logger.error("Exception while reading {}", filePath);
            logger.error(e.getMessage());
        }
        return result;
    }
}
