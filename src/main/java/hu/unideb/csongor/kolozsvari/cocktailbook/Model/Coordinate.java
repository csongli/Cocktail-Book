package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import com.google.gson.annotations.Expose;

/**
 * Represents a Coordinate on the flavor map, which consists of two numbers that represent the sweetness and dryness of the cocktail.
 */
public class Coordinate {
    /** The X axis coordinate on the flavor map.*/
    @Expose(serialize = true, deserialize = true)
    private Float x;

    /** The Y axis coordinate on the flavor map.*/
    @Expose(serialize = true, deserialize = true)
    private Float y;

    /**
     * Constructor for the coordinate class.
     * @param x the x axis number of the coordinate on the flavor map.
     * @param y the y axis number of the coordinate on the flavor map.
     */
    public Coordinate(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Empty constructor.
     */
    public Coordinate() {
    }

    /**
     * Getter method for x.
     * @return x axis coordinate.
     */
    public Float getX() {
        return x;
    }

    /**
     * Getter method for y.
     * @return y axis coordinate.
     */
    public Float getY() {
        return y;
    }

    /** Setter method for {@code x} coordinate. */
    public void setX(Float x) {
        this.x = x;
    }

    /** Setter method for {@code y} coordinate. */
    public void setY(Float y) {
        this.y = y;
    }
}
