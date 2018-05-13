package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;


import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//todo javadoc
public class ProfileController {

    public static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    Profile profile;

    public ProfileController(Profile profile) {
        this.profile = profile;
    }

    public void addCocktailToFavourites(Cocktail cocktail){
        logger.info("Adding " + cocktail.getName() + " to favorites.");
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.add(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
        logger.info("FavoriteCocktails: " + profile.getFavouriteCocktails() );
    }
    public void removeCocktailFromFavourites(Cocktail cocktail){
        logger.info("Removing " + cocktail.getName() + " from favorites.");
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.remove(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
        logger.info("FavoriteCocktails: " + profile.getFavouriteCocktails() );

    }
    public void calculateFlavorMapPoint(List<Cocktail> favourites){
        //todo log dis calculates flavor profile point on flavor map graph
        float xCoordinate=0,yCoordinate=0;
        for(int i=0; i < favourites.size();i++){
            xCoordinate += favourites.get(i).getFlavorMapCoordinate().getX();
            yCoordinate += favourites.get(i).getFlavorMapCoordinate().getY();
        }
        xCoordinate = xCoordinate/favourites.size();
        yCoordinate = yCoordinate/favourites.size();
        profile.setMyFlavorMapPoint(new Coordinate(xCoordinate,yCoordinate));
    }
    public void showMoreRecommendations(){
        if(profile.getRecommendationRadius() < 20.0f){
            profile.setRecommendationRadius(profile.getRecommendationRadius() + 0.5f);
        }
    }
    public void showLessRecommendations(){
        if(profile.getRecommendationRadius() > 0.5f){
            profile.setRecommendationRadius(profile.getRecommendationRadius() - 0.5f);
        }
    }

    public Profile getProfile(){
        return this.profile;
    }

    public void setProfile(Profile profile){
        this.profile = profile ;
    }

    public void getFavorites(){

    }
}
