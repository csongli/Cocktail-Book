package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for handling the searches for cocktails.
 */
public class SearchController {

    /** Logger. */
    public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    /** List of ingredients. */
    private List<Ingredient> ingredients;

    /**
     * Constructor for SearchController class.
     */
    public SearchController(){
        ingredients = new ArrayList<Ingredient>();
    }

    /**
     * Method that returns ingredient names from a cocktails ingredients.
     * @param ingredients the ingredients of a cocktail.
     * @return list of ingredient names.
     */
    public List<String> collectIngredientNames(Set<Pair<Ingredient, Quantity>> ingredients){
        return ingredients.stream()
                .map(ingredient -> ingredient.getKey().getName())
                .collect(Collectors.toList());
    }

    /**
     * Method that returns ingredient names from a list of ingredients.
     * @param ingredients the ingredients of a cocktail.
     * @return list of ingredient names.
     */
    public List<String> collectIngredientNames(List<Ingredient> ingredients){
        return ingredients.stream()
                .map(ingredient -> ingredient.getName())
                .collect(Collectors.toList());
    }

    /**
     * Checks if ingredient is in the loaded ingredients list.
     * @param ingredientName the name of the ingredient.
     * @return true if ingredient is included in the ingredient list.
     */
    public boolean isIngredient(String ingredientName){
        return collectIngredientNames(Ingredient.getAllIngredients()).contains(ingredientName);
    }

    /**
     * Getter method for ingredients.
     * @return the list of ingredients included in the search parameters.
     */
    public List<Ingredient> getSearchedIngredients(){
        return ingredients;
    }

    /**
     * Adds an ingredient to the search parameters.
     * @param ingredient the ingredient that will be added.
     */
    public void addIngredient(Ingredient ingredient){
        logger.info("Adding ingredient to search:" +ingredient.getName());
        ingredients.add(ingredient);
        logger.info("Ingredients" + collectIngredientNames(ingredients));
    }

    /**
     * Removes an ingredient from the search parameters.
     * @param ingredient the ingredient that will be removed.
     */
    public void removeIngredient(Ingredient ingredient){
        logger.info("Removing ingredient from search:" +ingredient.getName());
        ingredients.remove(ingredient);
        logger.info("Ingredients" + collectIngredientNames(ingredients));
    }

    /**
     * Clears all ingredients from the search parameters.
     */
    public void clearSearchList(){
        ingredients.clear();
    }

    /**
     * Searches the cocktail list by the ingredient search parameters.
     * @return list of cocktails that contain all ingredients from the search parameters.
     */
    public List<Cocktail> search(){
        logger.info("Searching for cocktails that can be made from: " + collectIngredientNames(ingredients));
        return Cocktail.getAllCocktails().stream()
                .filter(e -> collectIngredientNames(ingredients).containsAll( collectIngredientNames(e.getIngredients())))
                .collect(Collectors.toList());
    }

    /**
     * Searches the cocktail list by the radius of the flavorMapCoordinate.
     * @param flavorMapCoordinate the users coordinate on the flavor map.
     * @param radius the radius of the circle in which we search on the flavor map.
     * @return list of cocktails which coordinates are in the radius of the circle on the flavor map.
     */
    public List<Cocktail> searchByRadius(Coordinate flavorMapCoordinate, double radius){
        logger.info("Searching by radius: " + radius);
        logger.info("FlavorPoint: " + flavorMapCoordinate.toString());

        List<Cocktail> cocktails = Cocktail.getAllCocktails().stream()
               .filter( e -> e.getFlavorMapCoordinate().isInRadius(flavorMapCoordinate, radius))
                .collect(Collectors.toList());

        cocktails.stream().forEach((e) -> logger.debug(e.toString()));
        return cocktails;
    }

}
