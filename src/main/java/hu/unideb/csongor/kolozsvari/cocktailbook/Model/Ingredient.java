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

    /**The list of all ingredients. */
    @Expose(serialize = false, deserialize = false)
    private static List<Ingredient> allIngredients = new ArrayList<>();

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
     * Setter method for {@code allIngredients}.
     * @param allIngredients list of all ingredients.
     */
    public static void setAllIngredients(List<Ingredient> allIngredients) {
        Ingredient.allIngredients = allIngredients;
    }

    /**
     * Getter method for allIngredients.
     * @return a list of all the ingredients.
     */
    public static List<Ingredient> getAllIngredients() {
        return allIngredients;
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

    /**
     * Setter method for {@code type}.
     * @param type the type of the ingredient.
     */
    public void setType(IngredientType type) {
        this.type = type;
    }

    /**
     * Setter method for {@code subType}.
     * @param subType the sub type of the ingredient.
     */
    public void setSubType(IngredientSubType subType) {
        this.subType = subType;
    }

    /**
     * Setter method for {@code name}.
     * @param name the name of the ingredient.
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Setter method for {@code imgPath}.
     * @param imgPath the file path of the image for the ingredient.
     * */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * The Ingredient toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "Ingredient{" +
                "type=" + type +
                ", subType=" + subType +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    /**
     * The Ingredient equals method.
     * @param o the object
     * @return true if objects are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return type == that.type &&
                subType == that.subType &&
                Objects.equals(name, that.name) &&
                Objects.equals(imgPath, that.imgPath);
    }

    /**
     * The Ingredient hashcode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(type, subType, name, imgPath);
    }
}
