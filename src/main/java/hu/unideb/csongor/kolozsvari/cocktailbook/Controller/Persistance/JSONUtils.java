package hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance;

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

public class JSONUtils {
    public static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    final GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().serializeNulls().enableComplexMapKeySerialization();
    final Gson gson = gsonBuilder.registerTypeAdapter(Pair.class, new PairSerializer()).setPrettyPrinting().create();

    public String serializeProfile(Profile profile){
        String json = gson.toJson(profile);
        return json;
    }

    public String serializeCocktail(Cocktail cocktail){
        String json = gson.toJson(cocktail);
        return json;
    }

    public String serializeIngredient(Ingredient ingredient){
        String json = gson.toJson(ingredient);
        return json;
    }

    public Profile deserializeProfile(String json){
        Profile profile = new Profile();
        profile = gson.fromJson(json, Profile.class);
        logger.info("The deserialized profile:\n{}", profile);
        return profile;
    }

    public Cocktail deserializeCocktail(String json){
        Cocktail cocktail = new Cocktail();
        cocktail = gson.fromJson(json, Cocktail.class);
        logger.info("The deserialized cocktail:\n{}", cocktail);
        return cocktail;
    }

    public Ingredient deserializeIngredient(String json){
        Ingredient ingredient = gson.fromJson(json, Ingredient.class);
        logger.info("The deserialized ingredient:\n{}", ingredient);
        return ingredient;
    }

    public List<CocktailEntity> deserializeCocktailList(String json){
        Type listType = new TypeToken<ArrayList<CocktailEntity>>(){}.getType();
        List<CocktailEntity> cocktailEntities = gson.fromJson(json, listType);
        logger.info("Deserialized all cocktailEntities: " + cocktailEntities);
        return cocktailEntities;
    }

    public List<Ingredient> deserializeIngredientList(String json){
        Type listType = new TypeToken<ArrayList<Ingredient>>(){}.getType();
        List<Ingredient> ingredients = gson.fromJson(json, listType);
        logger.info("Deserialized all ingredients: " + ingredients);
        return ingredients;
    }

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
