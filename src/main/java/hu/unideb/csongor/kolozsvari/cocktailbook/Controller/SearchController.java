package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Ingredient;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Pair;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Quantity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//todo javadoc
public class SearchController {

    private List<String> collectIngredientNames(Set<Pair<Ingredient, Quantity>> ingredients){
        return ingredients.stream()
                .map(ingredient -> ingredient.getKey().getName())
                .collect(Collectors.toList());
    }

    private List<Cocktail> searchByIngredients(List<Cocktail> cocktails, List<String> ingredients){
        return cocktails.stream()
                .filter(e -> collectIngredientNames(e.getIngredients()).contains(ingredients))
                .collect(Collectors.toList());
    }
}
