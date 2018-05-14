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

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a cocktail, that has a set of ingredients and a coordinate on the flavor map.
 */
public class Cocktail {
    /** A set of ingredients required for the creation of the cocktail. Every ingredient is represented as a pair of the ingredient and the quantity needed.*/
    @Expose(serialize = true, deserialize = true)
    private Set<Pair<Ingredient,Quantity>> ingredients;

    /** The coordinate of the cocktail on the flavor map.*/
    @Expose(serialize = true, deserialize = true)
    private Coordinate flavorMapCoordinate;

    /** The name of the cocktail.*/
    @Expose(serialize = true, deserialize = true)
    private String name;

    /** The description of the cocktail.*/
    @Expose(serialize = true, deserialize = true)
    private String description;

    /** File path of the image for the cocktail.*/
    @Expose(serialize = true, deserialize = true)
    private String imgPath;

    @Expose(serialize = false, deserialize = false)
    private static List<Cocktail> allCocktails = new ArrayList<>();

    /**
     * Constructor for the cocktail class.
     * @param ingredients the ingredients of the cocktail.
     * @param flavorMapCoordinate the coordinate of the cocktail on the flavor map.
     * @param name the name of the cocktail.
     * @param description the description of the cocktail.
     * @param imgPath the file path of the image.
     */
    public Cocktail(Set<Pair<Ingredient, Quantity>> ingredients, Coordinate flavorMapCoordinate, String name, String description, String imgPath) {
        this.ingredients = ingredients;
        this.flavorMapCoordinate = flavorMapCoordinate;
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
    }

    /**
     * Empty constructor for cocktails.
     */
    public Cocktail() { }

    /**
     * Getter method for allCocktails.
     * @return a list of all the cocktails.
     */
    public static List<Cocktail> getAllCocktails() {
        return allCocktails;
    }

    /**
     * Setter method for {@code allCocktails}.
     * @param allCocktails list of all cocktails.
     */
    public static void setAllCocktails(List<Cocktail> allCocktails) {
        Cocktail.allCocktails = allCocktails;
    }

    /**
     * Getter method for ingredients.
     * @return a set of ingredients.
     */
    public Set<Pair<Ingredient, Quantity>> getIngredients() {
        return ingredients;
    }

    /**
     * Getter method for flavorMapCoordinate.
     * @return the coordinate of the cocktail on the flavor map.
     */
    public Coordinate getFlavorMapCoordinate() {
        return flavorMapCoordinate;
    }

    /**
     * Getter method for name.
     * @return the name of the cocktail.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for description.
     * @return the description of the cocktail.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for imgPath.
     * @return path of the image.
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Setter method for {@code ingredients}.
     * @param ingredients set of ingredient and quantity pairs for the cocktail.
     */
    public void setIngredients(Set<Pair<Ingredient, Quantity>> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Setter method for {@code flavorMapCoordinate}.
     * @param flavorMapCoordinate coordinate on theflavor map.
     */
    public void setFlavorMapCoordinate(Coordinate flavorMapCoordinate) {
        this.flavorMapCoordinate = flavorMapCoordinate;
    }

    /**
     * Setter method for {@code name}.
     * @param name the name of the cocktail.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for {@code description}.
     * @param description the description of the cocktail.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter method for {@code imgPath}.
     * @param imgPath the file path for the image of the cocktail.
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * Getter method for IngredientNames.
     * @return the list of ingredients.
     */
    public ArrayList<String> getIngredientNames(){
        return (ArrayList) ingredients.stream().map((e) -> e.getKey().getName()).collect(Collectors.toList());
    }

    /**
     * Getter method for IngredientQuantities.
     * @return a list of the ingredient quantities.
     */
    public ArrayList<String> getIngredientQuanities(){
        return (ArrayList) ingredients.stream().map((e) -> e.getValue().getAmount() + " " + e.getValue().getType())
                .collect(Collectors.toList());
    }

    /**
     * The Cocktail equals method.
     * @param o the object.
     * @return true if objects are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return Objects.equals(ingredients, cocktail.ingredients) &&
                Objects.equals(flavorMapCoordinate, cocktail.flavorMapCoordinate) &&
                Objects.equals(name, cocktail.name) &&
                Objects.equals(description, cocktail.description) &&
                Objects.equals(imgPath, cocktail.imgPath);
    }

    /**
     * The Cocktail hashcode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(ingredients, flavorMapCoordinate, name, description, imgPath);
    }

    /**
     * The Cocktail toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "Cocktail{" +
                "ingredients=" + ingredients +
                ", flavorMapCoordinate=" + flavorMapCoordinate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
