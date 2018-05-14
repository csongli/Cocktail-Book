package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

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
