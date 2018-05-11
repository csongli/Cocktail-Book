package hu.unideb.csongor.kolozsvari.cocktailbook.Model;
import com.google.gson.annotations.Expose;

import java.util.Set;

/**
 * Represents a cocktail, that has a set of ingredients and a coordinate on the flavor map.
 */
public class Cocktail {
    /** A set of ingredients required for the creation of the cocktail. Every ingredient is represented as a pair of the ingredient and the quantity needed.*/
    @Expose(serialize = true, deserialize = true)
    private Set<Pair<Ingredient,Quantity>> ingredients;

    /** The coordinate of the cocktail on the flavor map*/
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

    /** Setter method for {@code ingredients}. */
    public void setIngredients(Set<Pair<Ingredient, Quantity>> ingredients) {
        this.ingredients = ingredients;
    }

    /** Setter method for {@code flavorMapCoordinate}. */
    public void setFlavorMapCoordinate(Coordinate flavorMapCoordinate) {
        this.flavorMapCoordinate = flavorMapCoordinate;
    }

    /** Setter method for {@code name}. */
    public void setName(String name) {
        this.name = name;
    }

    /** Setter method for {@code description}. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Setter method for {@code imgPath}. */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

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
