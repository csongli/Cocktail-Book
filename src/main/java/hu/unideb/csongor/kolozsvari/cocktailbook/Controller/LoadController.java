package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

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

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance.JSONUtils;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for handling the loading and saving of user settings.
 */
public class LoadController {

    /** Cocktail database. */
    private final String COCKTAILS_FILE = "data/cocktails.json";
    /** Ingredient database. */
    private final String INGREDIENTS_FILE = "data/ingredients.json";
    /** Profile database. */
    private final String PROFILE_FILE = "userData/profile.json";

    /** Logger. */
    public static final Logger logger = LoggerFactory.getLogger(LoadController.class);
    /** Utility object for reading and writing JSONs. */
    private final JSONUtils jsonUtils = new JSONUtils();

    /**
     * Saves the profile.
     * @param profile the profile that will be saved.
     */
    public void saveProfile(Profile profile){
        String jsonToWrite = jsonUtils.serializeProfile(profile);
        File file = new File(PROFILE_FILE);
        try {
            FileUtils.writeStringToFile(file, jsonToWrite, StandardCharsets.UTF_8, false);
        } catch (IOException e) {
            logger.error("IOException while writing profile file: {}", file.getPath());
            logger.error(e.getMessage());
        }
    }

    /**
     * Converts a {@code CocktailEntity} object to a {@code Cocktail} object.
     * @param entity the entity to convert.
     * @return the converted cocktail.
     */
    public Cocktail convertToCocktail(CocktailEntity entity){
        Set<Pair<Ingredient, Quantity>> ingredients = entity.getIngredients().stream()
                .map((e) -> new Pair<>(getIngredientFromName(e.getKey()), e.getValue()))
                .collect(Collectors.toSet());

        return new Cocktail(ingredients, entity.getFlavorMapCoordinate(),
                entity.getName(), entity.getDescription(), entity.getImgPath());
    }

    /**
     * Returns the {@code Ingredient} object that has the ingredient name as the name.
     * @param ingredientName - the name of the ingredient.
     * @return the ingredient which has the name given in the parameter.
     */
    public static Ingredient getIngredientFromName(String ingredientName){
        logger.info("Getting ingredient from name : " + ingredientName);
        logger.info("All ingredient names are: ");
        Ingredient.getAllIngredients().stream().forEach((e) -> logger.info(e.getName()));

        Optional<Ingredient> ingredient = Ingredient.getAllIngredients().stream()
                .filter((e)-> e.getName().toLowerCase().equals(ingredientName.toLowerCase())).findFirst();
        return  ingredient.get();
    }

    /**
     * Reads all cocktails from the database.
     */
    public void readCocktails(){
        logger.info("Reading all cocktails!");
        String cocktailsJson = jsonUtils.readJSONfromFile(COCKTAILS_FILE);
        List<CocktailEntity> allCocktailEntities = jsonUtils.deserializeCocktailList(cocktailsJson);
        logger.info("Cocktails read\n");
        List<Cocktail> allCocktails  = allCocktailEntities.stream().map((e) -> convertToCocktail(e))
                .collect(Collectors.toList());

        logger.info("Converted back to cocktails: ");
        Cocktail.setAllCocktails(allCocktails);
    }

    /**
     * Reads all ingredients from the database.
     */
    public void readIngredients(){
        logger.info("Reading all ingredients!");
        String ingredientsJson = jsonUtils.readJSONfromFile(INGREDIENTS_FILE);
        List<Ingredient> allIngredients = jsonUtils.deserializeIngredientList(ingredientsJson);
        logger.info("Gathered all ingredients: " + allIngredients );

        Ingredient.setAllIngredients(allIngredients);
    }

    /**
     * Initializes the profile which holds the user settings.
     * @return the initialized {@code Profile} object.
     */
    public Profile initializeProfile() {
        logger.info("Initializing profile.");
        File file = new File(PROFILE_FILE);
        String profileJson = new String();
        Profile profile = new Profile();
        if(file.exists()){
            logger.info("Profile file exists, reading.");
            try {
                profileJson = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
                JSONUtils jsonUtils = new JSONUtils();
                profile = jsonUtils.deserializeProfile(profileJson);
                logger.info("Deserialized profile: " + profile.toString());
            } catch (IOException e) {
                logger.error("Error, profile file doesn't exist!");
            } catch (Exception e ){
                logger.error("Error while deserializing profile file!");
            }
        } else {
            logger.info("Profile file doesn't exist, creating it.");
            profile = new Profile();
            saveProfile(profile);
        }
        return profile;
    }
}
