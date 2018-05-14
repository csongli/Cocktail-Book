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

import java.util.Objects;

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
     * Method for calculating if a coordinate is in a givan radius or not.
     * @param other the other point
     * @param radius the radius
     * @return true if the coordinate is in the given radius, false if not.
     */
    public boolean isInRadius(Coordinate other, double radius){
        return ((this.x + radius >= other.getX()) && (this.x - radius <= other.getX ()) &&
                ((this.y + radius >= other.getY()) && (this.y - radius <= other.getY())));
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

    /**
     * Setter method for {@code x} coordinate.
     * @param x the value of the x coordinate on the flavor map.
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * Setter method for {@code y} coordinate.
     * @param y the value of the y coordinate on the flavor map.
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * The Coordinate equals method.
     * @param o the objectmvn
     * @return true if the two coordinates are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    /**
     * The Coordinate hascode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * The Coordinate toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
