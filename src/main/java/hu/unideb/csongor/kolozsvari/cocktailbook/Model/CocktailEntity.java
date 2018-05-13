package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import com.google.gson.annotations.Expose;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Cocktail object for serializing cocktail objects.
 */
public class CocktailEntity {
        @Expose(serialize = true, deserialize = true)
        private Set<Pair<String,Quantity>> ingredients;

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

    public CocktailEntity(Cocktail cocktail) {
        this.description = cocktail.getDescription();
        this.flavorMapCoordinate = cocktail.getFlavorMapCoordinate();
        this.name = cocktail.getName();
        this.imgPath = cocktail.getImgPath();
        this.ingredients = cocktail.getIngredients().stream()
                .map((e) -> new Pair<>(e.getKey().getName(), e.getValue()))
                .collect(Collectors.toSet());
    }

    public Set<Pair<String, Quantity>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Pair<String, Quantity>> ingredients) {
        this.ingredients = ingredients;
    }

    public Coordinate getFlavorMapCoordinate() {
        return flavorMapCoordinate;
    }

    public void setFlavorMapCoordinate(Coordinate flavorMapCoordinate) {
        this.flavorMapCoordinate = flavorMapCoordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

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
}
