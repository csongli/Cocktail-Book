package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

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
