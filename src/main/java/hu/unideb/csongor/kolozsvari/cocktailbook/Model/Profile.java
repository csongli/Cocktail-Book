package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the users profile, which contains their favourite cocktails, and based on this their taste on the flavor map.
 */
public class Profile {
    /** List of favourite cocktails.*/
    List<Cocktail> favouriteCocktails;

    /** The coordinate of the users taste on the flavor map.*/
    Coordinate myFlavorMapPoint;

    /** The radius in which recommendations are given.*/
    double recommendationRadius;

    /**
     * Empty constructor.
     */
    public Profile() {
        System.out.println("Profile constructor");
        this.favouriteCocktails = new ArrayList<>();
        this.myFlavorMapPoint = new Coordinate((float) 0,(float) 0);
        this.recommendationRadius = 1;
    }

    /**
     * Constructor for the profile class.
     * @param favouriteCocktails list of favourite cocktails.
     * @param myFlavorMapPoint the coordinate of the users taste on the flavor map.
     * @param recommendationRadius radius in which recommendations are given.
     */
    public Profile(List<Cocktail> favouriteCocktails, Coordinate myFlavorMapPoint, double recommendationRadius) {
        this.favouriteCocktails = favouriteCocktails;
        this.myFlavorMapPoint = myFlavorMapPoint;
        this.recommendationRadius = recommendationRadius;
    }

    /**
     * Getter method for favouriteCocktails.
     * @return the favourite cocktails of the user.
     */
    public List<Cocktail> getFavouriteCocktails() {
        return favouriteCocktails;
    }

    /**
     * Getter method for myFlavorMapPoint.
     * @return the coordinate of the users taste on the flavor map.
     */
    public Coordinate getMyFlavorMapPoint() {
        return myFlavorMapPoint;
    }

    /**
     * Getter method for recommendationRadius.
     * @return the radius in which recommendations are given.
     */
    public double getRecommendationRadius() {
        return recommendationRadius;
    }

    /**
     * Setter method for {@code favouriteCocktails}.
     * @param favouriteCocktails the {@code Cocktail} to set.
     */
    public void setFavouriteCocktails(List<Cocktail> favouriteCocktails) {
        this.favouriteCocktails = favouriteCocktails;
    }

    /**
     * Setter method for {@code myFlavorMapPoint}.
     * @param myFlavorMapPoint the {@code Coordinate} to set.
     */
    public void setMyFlavorMapPoint(Coordinate myFlavorMapPoint) {
        this.myFlavorMapPoint = myFlavorMapPoint;
    }

    /**
     * Setter method for {@code recommendationRadius}.
     * @param recommendationRadius the radius to set.
     */
    public void setRecommendationRadius(double recommendationRadius) {
        this.recommendationRadius = recommendationRadius;
    }

    /**
     * The Profile toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "Profile{" +
                "favouriteCocktails=" + favouriteCocktails +
                ", myFlavorMapPoint=" + myFlavorMapPoint +
                ", recommendationRadius=" + recommendationRadius +
                '}';
    }
}
