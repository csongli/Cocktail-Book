import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.LoadController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchControllerTest {

    //ProfileController profileController = new ProfileController();
    @Test
    public void searchAddRemoveTest(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        SearchController searchController = new SearchController();
        Cocktail cocktail = new Cocktail();

        List<Ingredient> allIngredients = new ArrayList<>();
        Ingredient lemon = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lemon", "someimage");
        Ingredient kahlua = new Ingredient(IngredientType.Liqueur, IngredientSubType.Other, "Kahlua", "someimage");
        allIngredients.add(lemon);
        allIngredients.add(kahlua);

        searchController.addIngredient(lemon);
        searchController.addIngredient(kahlua);

        Assert.assertEquals(searchController.getSearchedIngredients().size(), 2);
        searchController.removeIngredient(lemon);
        Assert.assertEquals(searchController.getSearchedIngredients().size(), 1);
        searchController.removeIngredient(kahlua);
        Assert.assertEquals(searchController.getSearchedIngredients().size(), 0);

        Ingredient.setAllIngredients(allIngredients);

        Assert.assertEquals(searchController.isIngredient(lemon.getName()), true);
        Assert.assertEquals(searchController.isIngredient(kahlua.getName()),true);

        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lime", "someimage");
        Assert.assertEquals(searchController.isIngredient(lime.getName()),false);

        searchController.clearSearchList();
        Assert.assertEquals(searchController.getSearchedIngredients().size(), 0);

        searchController.addIngredient(lemon);
        searchController.clearSearchList();

        Assert.assertEquals(searchController.getSearchedIngredients().size(), 0);

        searchController.addIngredient(kahlua);
        searchController.clearSearchList();

        Assert.assertEquals(searchController.getSearchedIngredients().size(), 0);

        searchController.addIngredient(lemon);
        searchController.addIngredient(kahlua);
        Assert.assertEquals(searchController.getSearchedIngredients().size(), 2);
        searchController.clearSearchList();

        Assert.assertEquals(searchController.getSearchedIngredients().size(), 0);
    }

    @Test
    public void searchByRadiusTest(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        SearchController searchController = new SearchController();
        LoadController loadController = new LoadController();
        loadController.readIngredients();
        loadController.readCocktails();


        Coordinate zeroes = new Coordinate(0f,0f);
        List<Cocktail> resultCocktails  = searchController.searchByRadius(zeroes, 0);
        Assert.assertEquals(resultCocktails.size(),0);

        resultCocktails  = searchController.searchByRadius(zeroes, 10);
        Assert.assertNotEquals(resultCocktails.size(),0);

        profileController.showLessRecommendations();
        resultCocktails  = searchController.searchByRadius(zeroes, profile.getRecommendationRadius());
        Assert.assertEquals(resultCocktails.size(),0);

        profileController.showMoreRecommendations();
        profileController.showMoreRecommendations();
        profileController.showMoreRecommendations();

        resultCocktails  = searchController.searchByRadius(zeroes, profile.getRecommendationRadius());
        Assert.assertNotEquals(resultCocktails.size(),0);
    }

    @Test
    public void searchByIngredientsTest(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        SearchController searchController = new SearchController();
        Cocktail cocktail = new Cocktail();

        List<Ingredient> allIngredients = new ArrayList<>();
        Ingredient lemon = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lemon", "someimage");
        Ingredient kahlua = new Ingredient(IngredientType.Liqueur, IngredientSubType.Other, "Kahlua", "someimage");
        allIngredients.add(lemon);
        allIngredients.add(kahlua);

        searchController.addIngredient(lemon);
        searchController.addIngredient(kahlua);

        List<Cocktail> allCocktails = new ArrayList<>();
        Set<Pair<Ingredient,Quantity>> myset = new HashSet<Pair<Ingredient,Quantity>>();
        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lime", "someimage");
        Quantity quantity = new Quantity(QuantityType.Ounce, (float)4);
        myset.add(new Pair(lime, quantity));

        Coordinate flavorpoint = new Coordinate((float) 5, (float) 5);
        Cocktail mycocktail = new Cocktail(myset, flavorpoint,"Cocktail1", "shake without ice", "omega");
        Cocktail another = new Cocktail(myset, flavorpoint,"Cocktail", "shake without ice", "omega");

        allCocktails.add(mycocktail);
        allCocktails.add(another);

        Cocktail.setAllCocktails(allCocktails);

        //no cocktail with lemon and kahlua
        Assert.assertEquals(searchController.search().size(), 0);
        searchController.addIngredient(lime);

        //cocktail with lime exists
        Assert.assertEquals(searchController.search().size() > 0, true);
        Assert.assertEquals(searchController.search().size() , 2);
    }

    @Test
    public void collectIngredientNamesTests(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        SearchController searchController = new SearchController();
        Cocktail cocktail = new Cocktail();


        Set<Pair<Ingredient,Quantity>> myset = new HashSet<Pair<Ingredient,Quantity>>();
        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lime", "someimage");
        Quantity quantity = new Quantity(QuantityType.Ounce, (float)4);
        myset.add(new Pair(lime, quantity));

        Coordinate flavorpoint = new Coordinate((float) 5, (float) 5);
        Cocktail mycocktail = new Cocktail(myset, flavorpoint,"Margarita", "shake without ice", "omega");

        Assert.assertEquals(mycocktail.getIngredients().size(), 1);
        List<String> names = new ArrayList<>();
        names.add("Lime");

        Assert.assertEquals(searchController.collectIngredientNames(mycocktail.getIngredients()), names);

        Ingredient lemon = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "Lemon", "someimage");
        Ingredient kahlua = new Ingredient(IngredientType.Liqueur, IngredientSubType.Other, "Kahlua", "someimage");

        myset.add(new Pair(lemon,quantity));
        myset.add(new Pair(kahlua,quantity));
        names.add("Kahlua");
        names.add("Lemon");
        Cocktail cocktailNew = new Cocktail(myset, flavorpoint,"A drink", "shake without ice", "omega");
        Assert.assertEquals(searchController.collectIngredientNames(cocktailNew.getIngredients()).containsAll(names), true);
    }
}
