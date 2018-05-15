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
        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.None, "Lime", "lime.png");
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
