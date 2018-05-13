package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import hu.unideb.csongor.kolozsvari.cocktailbook.View.MainWindowController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

//todo javadoc
public class SearchController {

    public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private List<Ingredient> ingredients;

    public SearchController(){
        ingredients = new ArrayList<Ingredient>();
    }

    public List<String> collectIngredientNames(Set<Pair<Ingredient, Quantity>> ingredients){
        return ingredients.stream()
                .map(ingredient -> ingredient.getKey().getName())
                .collect(Collectors.toList());
    }

    public List<String> collectIngredientNames(List<Ingredient> ingredients){
        return ingredients.stream()
                .map(ingredient -> ingredient.getName())
                .collect(Collectors.toList());
    }

    public boolean isIngredient(String ingredientName){
        return collectIngredientNames(Ingredient.getAllIngredients()).contains(ingredientName);
    }

    public List<Ingredient> getSearchedIngredients(){
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public void clearSearchList(){
        ingredients.clear();
    }

    public List<Cocktail> search(){
        logger.info("Searching for cocktails that can be made from: " + collectIngredientNames(ingredients));
        return Cocktail.getAllCocktails().stream()
                .filter(e -> collectIngredientNames(ingredients).containsAll( collectIngredientNames(e.getIngredients())))
                .collect(Collectors.toList());
    }

    public List<Cocktail> searchByRadius(Coordinate flavorMapCoordinate, double radius){
        logger.info("Searching by radius: " + radius);
        logger.info("FlavorPoint: " + flavorMapCoordinate.toString());

        List<Cocktail> cocktails = Cocktail.getAllCocktails().stream()
               .filter( e -> e.getFlavorMapCoordinate().isInRadius(flavorMapCoordinate, radius))
                /*
                .filter(e -> ((e.getFlavorMapCoordinate().getX() <= (flavorMapCoordinate.getX() + radius)) ||
                              (e.getFlavorMapCoordinate().getX() >= (flavorMapCoordinate.getX() - radius)) ) &&
                        ((e.getFlavorMapCoordinate().getY() <= (flavorMapCoordinate.getY() + radius)) ||
                        (e.getFlavorMapCoordinate().getY() >= (flavorMapCoordinate.getY() - radius)) ) )
                */
                .collect(Collectors.toList());

        cocktails.stream().forEach((e) -> logger.debug(e.toString()));
        return cocktails;
    }




}
