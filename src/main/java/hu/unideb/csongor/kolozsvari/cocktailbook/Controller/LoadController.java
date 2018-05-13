package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance.JSONUtils;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

//todo javadoc
public class LoadController {

    private final String COCKTAILS_FILE = "data/cocktails.json";
    private final String INGREDIENTS_FILE = "data/ingredients.json";
    private final String PROFILE_FILE = "userData/profile.json";

    public static final Logger logger = LoggerFactory.getLogger(LoadController.class);
    private final JSONUtils jsonUtils = new JSONUtils();

    public void saveProfile(Profile profile){
        //todo call
        String jsonToWrite = jsonUtils.serializeProfile(profile);
        File file = new File(PROFILE_FILE);
        try {
            FileUtils.writeStringToFile(file, jsonToWrite, StandardCharsets.UTF_8, false);
        } catch (IOException e) {
            logger.error("IOException while writing profile file: {}", file.getPath());
            logger.error(e.getMessage());
        }
    }

    public void loadProfile(){
        //todo, call when opening program
        File file = new File(PROFILE_FILE);
        if(file.exists()){
            //todo read profile settings into SearchController profile
        } else {
            //todo create and save profile ?
            //just call saveprofile()
        }

    }

    public Cocktail convertToCocktail(CocktailEntity entity){
        Set<Pair<Ingredient, Quantity>> ingredients = entity.getIngredients().stream()
                .map((e) -> new Pair<>(getIngredientFromName(e.getKey()), e.getValue()))
                .collect(Collectors.toSet());

        return new Cocktail(ingredients, entity.getFlavorMapCoordinate(),
                entity.getName(), entity.getDescription(), entity.getImgPath());
    }

    public static Ingredient getIngredientFromName(String ingredientName){
        logger.info("Getting ingredient from name : " + ingredientName);
        logger.info("All ingredient names are: ");
        Ingredient.getAllIngredients().stream().forEach((e) -> logger.info(e.getName()));

        Optional<Ingredient> ingredient = Ingredient.getAllIngredients().stream()
                .filter((e)-> e.getName().toLowerCase().equals(ingredientName.toLowerCase())).findFirst();

        if(!ingredient.isPresent()){
            //todo write normal error
            logger.error("You dun goofed");
            return null;
        }
        return  ingredient.get();
    }

    public void readCocktails(){
        logger.info("Reading all cocktails!");
        String cocktailsJson = jsonUtils.readJSONfromFile(COCKTAILS_FILE);
        List<CocktailEntity> allCocktailEntities = jsonUtils.deserializeCocktailList(cocktailsJson);

        //allCocktailEntities.forEach( );

        logger.info("Cocktails read\n");
        List<Cocktail> allCocktails  = allCocktailEntities.stream().map((e) -> convertToCocktail(e))
                .collect(Collectors.toList());

        logger.info("Converted back to cocktails: ");
        allCocktails.stream().forEach((e) -> logger.info(e.getName()));
        Cocktail.setAllCocktails(allCocktails);
    }

    public void readIngredients(){
        logger.info("Reading all ingredients!");
        /*List<Cocktail> allCocktails = Cocktail.getAllCocktails();
        List<Ingredient> allIngredients = allCocktails.stream()
                .flatMap((e) -> e.getIngredients().stream().map((e2) -> e2.getKey()))
                .distinct().collect(Collectors.toList());

        logger.info("Gathered all ingredients: " + allIngredients );
        */

        String ingredientsJson = jsonUtils.readJSONfromFile(INGREDIENTS_FILE);
        List<Ingredient> allIngredients = jsonUtils.deserializeIngredientList(ingredientsJson);
        logger.info("Gathered all ingredients: " + allIngredients );

        Ingredient.setAllIngredients(allIngredients);
    }

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
