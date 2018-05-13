package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;


import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class for managing the profile.
 */
public class ProfileController {

    /** Logger. */
    public static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    /** The users profile. */
    Profile profile;

    /**
     * Constructor for ProfileController class.
     * @param profile the profile.
     */
    public ProfileController(Profile profile) {
        this.profile = profile;
    }

    /**
     * Adds a cocktail to the list of favourite cocktails.
     * @param cocktail the cocktail that will be added.
     */
    public void addCocktailToFavourites(Cocktail cocktail){
        logger.info("Adding " + cocktail.getName() + " to favorites.");
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.add(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
        logger.info("FavoriteCocktails: " + profile.getFavouriteCocktails() );
    }

    /**
     * Removes selected cocktail frome the favourites list.
     * @param cocktail the cocktail that will be removed.
     */
    public void removeCocktailFromFavourites(Cocktail cocktail){
        logger.info("Removing " + cocktail.getName() + " from favorites.");
        List<Cocktail> currentFavourites = profile.getFavouriteCocktails();
        currentFavourites.remove(cocktail);
        profile.setFavouriteCocktails(currentFavourites);
        logger.info("FavoriteCocktails: " + profile.getFavouriteCocktails() );

    }

    /**
     * Calculates the users point on the flavor map based on the favourites list.
     * @param favourites the list of the users favourite cocktails.
     */
    public void calculateFlavorMapPoint(List<Cocktail> favourites){
        logger.info("Calculating flavor map point");
        float xCoordinate=0,yCoordinate=0;
        for(int i=0; i < favourites.size();i++){
            xCoordinate += favourites.get(i).getFlavorMapCoordinate().getX();
            yCoordinate += favourites.get(i).getFlavorMapCoordinate().getY();
        }
        xCoordinate = xCoordinate/favourites.size();
        yCoordinate = yCoordinate/favourites.size();
        profile.setMyFlavorMapPoint(new Coordinate(xCoordinate,yCoordinate));
        logger.info("Flavor map point: " + profile.getMyFlavorMapPoint());
    }

    /**
     * Increases the radius in which recommendations are given.
     */
    public void showMoreRecommendations(){
        logger.info("Increasing recommendation radius");
        if(profile.getRecommendationRadius() < 20.0f){
            profile.setRecommendationRadius(profile.getRecommendationRadius() + 0.5f);
        }
        logger.info("Recommendation radius: " + profile.getRecommendationRadius());
    }

    /**
     * Decreases the radius in which recommendations are given.
     */
    public void showLessRecommendations(){
        logger.info("Decreasing recommendation radius");
        if(profile.getRecommendationRadius() > 0.5f){
            profile.setRecommendationRadius(profile.getRecommendationRadius() - 0.5f);
        }
        logger.info("Recommendation radius: " + profile.getRecommendationRadius());
    }

    /**
     * Getter method for profile.
     * @return the profile.
     */
    public Profile getProfile(){
        return this.profile;
    }

    /** Setter method for {@code profile}.
     * @param profile the profile
     * */
    public void initializeProfile(Profile profile){
        this.profile = profile ;
    }
}
