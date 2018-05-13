package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import com.google.gson.annotations.Expose;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Cocktail object for serializing cocktail objects.
 */
public class CocktailEntity {
        /**The ingredients of the cocktail. */
        @Expose(serialize = true, deserialize = true)
        private Set<Pair<String,Quantity>> ingredients;

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

    /**
     * Constructor for cocktail entity class.
     * @param cocktail the cocktail.
     */
    public CocktailEntity(Cocktail cocktail) {
        this.description = cocktail.getDescription();
        this.flavorMapCoordinate = cocktail.getFlavorMapCoordinate();
        this.name = cocktail.getName();
        this.imgPath = cocktail.getImgPath();
        this.ingredients = cocktail.getIngredients().stream()
                .map((e) -> new Pair<>(e.getKey().getName(), e.getValue()))
                .collect(Collectors.toSet());
    }

    /**
     * Getter method for ingredients.
     * @return the set of ingredients.
     */
    public Set<Pair<String, Quantity>> getIngredients() {
        return ingredients;
    }

    /**
     * Setter method for {@code ingredients}.
     * @param ingredients the ingredients of the cocktail.
     */
    public void setIngredients(Set<Pair<String, Quantity>> ingredients) {
        this.ingredients = ingredients;
    }


    /**
     * Getter method for flavorMapCoordinate.
     * @return a coordinate on the flavor map.
     */
    public Coordinate getFlavorMapCoordinate() {
        return flavorMapCoordinate;
    }

    /**
     * Setter method for {@code flavorMapCoordinate}.
     * @param flavorMapCoordinate coordinate of the cocktail on the flavor map.
     */
    public void setFlavorMapCoordinate(Coordinate flavorMapCoordinate) {
        this.flavorMapCoordinate = flavorMapCoordinate;
    }

    /**
     * Getter method for name.
     * @return the name of the cocktail.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for {@code name}.
     * @param name the name of the cocktail.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for description.
     * @return the description of the cocktail.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for {@code description}.
     * @param description the description of the cocktail.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for imgPath.
     * @return the path of the image.
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Setter method for {@code imgPath}.
     * @param imgPath file path of the image of the cocktail.
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * The Cocktail toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "CocktailEntity{" +
                "ingredients=" + ingredients +
                ", flavorMapCoordinate=" + flavorMapCoordinate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    /**
     * The CocktailEntity equals method.
     * @param o the object
     * @return true if objects are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailEntity entity = (CocktailEntity) o;
        return Objects.equals(ingredients, entity.ingredients) &&
                Objects.equals(flavorMapCoordinate, entity.flavorMapCoordinate) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(imgPath, entity.imgPath);
    }

    /**
     * The CocktailEntity hashcode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(ingredients, flavorMapCoordinate, name, description, imgPath);
    }
}
