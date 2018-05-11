package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;


import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;

import java.util.List;

//todo javadoc
public class ProfileController {
    Profile profile;

    public ProfileController(Profile profile) {
    }

    private void addCocktailToFavourites(Cocktail cocktail){
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.add(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
    }
    private void removeCocktailFromFavourites(Cocktail cocktail){
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.remove(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
    }
    private Coordinate calculateFlavorMapPoint(List<Cocktail> favourites){
        //calculates flavor profile point on flavor map graph\
        float xCoordinate=0,yCoordinate=0;
        for(int i=0;i<=favourites.size();i++){
            xCoordinate += favourites.get(i).getFlavorMapCoordinate().getX();
            yCoordinate += favourites.get(i).getFlavorMapCoordinate().getY();
        }
        xCoordinate = xCoordinate/favourites.size();
        yCoordinate = yCoordinate/favourites.size();
        return new Coordinate(xCoordinate,yCoordinate);
    }
    private void showMoreRecommendations(){
        //increase radius
    }
    private void showLessRecommendations(){

    }
}
