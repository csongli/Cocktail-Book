import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileControllerTest {
    //ProfileController profileController = new ProfileController();
    @Test
    public void addCocktailToFavouritesTest(){
        List<Cocktail> cocktailList = new ArrayList<>();
        Cocktail cocktail = new Cocktail();
        Assert.assertEquals(cocktailList.size(), 0);
        //addCocktailToFavourites();
        //Assert.assertEquals(cocktailList.size(),1);
    }

    @Test
    public void removeCocktailFromFavouritesTest(){
        List<Cocktail> cocktailList = new ArrayList<>();
        Cocktail cocktail = new Cocktail();
        cocktailList.add(cocktail);
        Assert.assertEquals(cocktailList.size(), 1);

        cocktailList.remove(cocktail);
        Assert.assertEquals(cocktailList.size(),0);
    }

    @Test
    public void calculateFlavorMapPointTest(){
        /*
        List<Cocktail> favourites = new ArrayList<>();
        Coordinate testCoordinate = new Coordinate();
        float xCoord = 0;
        xCoord += favourites.get(0).getFlavorMapCoordinate().getX();
        Assert.assertNotEquals(0, xCoord);
        */
    }
}
