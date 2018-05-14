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

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProfileControllerTest {

    //ProfileController profileController = new ProfileController();
    @Test
    public void addCocktailToFavouritesTest(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        Cocktail cocktail = new Cocktail();

        Assert.assertEquals(profile.getFavouriteCocktails().size(), 0);
        profileController.addCocktailToFavourites(cocktail);

        Assert.assertEquals(profile.getFavouriteCocktails().size(), 1);

        profileController.addCocktailToFavourites(cocktail);
        Assert.assertEquals(profile.getFavouriteCocktails().size(), 2);

        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(cocktail);

        profile = new Profile(cocktails, new Coordinate(), 1.5);
        Assert.assertEquals(profile.getFavouriteCocktails().size(), 1);

        profileController.initializeProfile(profile);
        profileController.removeCocktailFromFavourites(cocktail);
        Assert.assertEquals(profile.getFavouriteCocktails().size(), 0);


    }

    @Test
    public void removeCocktailFromFavouritesTest(){
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        Cocktail cocktail = new Cocktail();

        Assert.assertEquals(profile.getFavouriteCocktails().size(), 0);
        profileController.addCocktailToFavourites(cocktail);

        Assert.assertEquals(profile.getFavouriteCocktails().size(), 1);
        profileController.removeCocktailFromFavourites(cocktail);
        Assert.assertEquals(profile.getFavouriteCocktails().size(), 0);
    }

    @Test
    public void calculateFlavorMapPointTest(){
        float delta = 0.0000001f;
        Profile profile = new Profile();
        ProfileController profileController = new ProfileController(profile);
        //    public Cocktail(Set<Pair<Ingredient, Quantity>> ingredients, Coordinate flavorMapCoordinate, String name, String description, String imgPath) {

        Coordinate zeroes = new Coordinate(0f,0f);
        Coordinate ones = new Coordinate(1f,1f);
        Coordinate twos = new Coordinate(1f,1f);
        Coordinate minusones = new Coordinate(-1f,-1f);
        Coordinate minustwos = new Coordinate(-2f,-2f);

        Cocktail zeroesCocktail = new Cocktail(null, zeroes, "zeroes", "", "");
        Cocktail onesCocktail = new Cocktail(null, ones, "zeroes", "", "");
        Cocktail twosCocktail = new Cocktail(null, twos, "zeroes", "", "");
        Cocktail minusonesCocktail = new Cocktail(null, minusones, "zeroes", "", "");
        Cocktail minustwosCocktail = new Cocktail(null, minustwos, "zeroes", "", "");

        profileController.addCocktailToFavourites(zeroesCocktail);
        profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
        Assert.assertEquals(profile.getMyFlavorMapPoint(), zeroes);

        profileController.addCocktailToFavourites(onesCocktail);
        profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
        Assert.assertEquals(profile.getMyFlavorMapPoint(), new Coordinate(0.5f,0.5f));

        profileController.removeCocktailFromFavourites(zeroesCocktail);
        profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
        Assert.assertEquals(profile.getMyFlavorMapPoint(), new Coordinate(1f,1f));

        profileController.addCocktailToFavourites(minusonesCocktail);
        profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
        Assert.assertEquals(profile.getMyFlavorMapPoint(), new Coordinate(0f,0f));

        profileController.addCocktailToFavourites(minustwosCocktail);
        profileController.addCocktailToFavourites(minustwosCocktail);
        profileController.removeCocktailFromFavourites(minusonesCocktail);

        profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
        Assert.assertEquals(profile.getMyFlavorMapPoint(), new Coordinate(-1f,-1f));








    }
}
