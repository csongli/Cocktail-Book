package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import com.google.gson.annotations.Expose;

/**
 * Represents an ingredient of a cocktail, that has a certain type which may have subtypes.
 */
public class Ingredient {
    /** The type of the ingredient.*/
    @Expose(serialize = true, deserialize = true)
    private IngredientType type;

    /** The subtype of the ingredient.*/
    @Expose(serialize = true, deserialize = true)
    private  IngredientSubType subType;

    /** The name of the ingredient.*/
    @Expose(serialize = true, deserialize = true)
    private String name;

    /** File path of the image for the ingredient.*/
    @Expose(serialize = true, deserialize = true)
    private String imgPath;

    /**
     * Construcor for the ingredient class.
     * @param type the type of the ingredient.
     * @param subType the subtype of the ingredient.
     * @param name the name of the ingredient.
     * @param imgPath the file path of the image.
     */
    public Ingredient(IngredientType type, IngredientSubType subType, String name, String imgPath) {
        this.type = type;
        this.subType = subType;
        this.name = name;
        this.imgPath = imgPath;
    }

    /**
     * Empty constructor.
     */
    public Ingredient() {
    }

    /**
     * Getter method for type.
     * @return the type of the ingredient.
     */
    public IngredientType getType() {
        return type;
    }

    /**
     * Getter method for subType.
     * @return the subtype of the ingredient.
     */
    public IngredientSubType getSubType() {
        return subType;
    }

    /**
     * Getter method for name.
     * @return the name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for imgPath.
     * @return path of the image.
     */
    public String getImgPath() {
        return imgPath;
    }

    /** Setter method for {@code type}. */
    public void setType(IngredientType type) {
        this.type = type;
    }

    /** Setter method for {@code subType}. */
    public void setSubType(IngredientSubType subType) {
        this.subType = subType;
    }

    /** Setter method for {@code name}. */
    public void setName(String name) {
        this.name = name;
    }

    /** Setter method for {@code imgPath}. */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
