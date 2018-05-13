import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.LoadController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LoadControllerTest {

    @Test
    public void loadCocktailsIngredients() {
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        SearchController searchController = new SearchController();
        LoadController loadController = new LoadController();

        Assert.assertEquals(Cocktail.getAllCocktails().size(), 0);
        Assert.assertEquals(Ingredient.getAllIngredients().size(), 0);
        loadController.readIngredients();
        Assert.assertNotEquals(Ingredient.getAllIngredients().size(), 0);
        loadController.readCocktails();
        Assert.assertNotEquals(Cocktail.getAllCocktails().size(), 0);

        Set<Pair<Ingredient, Quantity>> myset = new HashSet<Pair<Ingredient, Quantity>>();
        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.None, "Lime", "lime.jpg");
        Quantity quantity = new Quantity(QuantityType.Ounce, (float) 4);
        myset.add(new Pair(lime, quantity));
        Coordinate flavorpoint = new Coordinate((float) 5, (float) 5);
        Cocktail cocktail = new Cocktail(myset, flavorpoint, "Margarita", "shake without ice", "omega");

        CocktailEntity entity = new CocktailEntity(cocktail);
        Cocktail converted = loadController.convertToCocktail(entity);
        Assert.assertEquals(cocktail.getName(), converted.getName());
        Assert.assertEquals(cocktail, converted);
    }
}
