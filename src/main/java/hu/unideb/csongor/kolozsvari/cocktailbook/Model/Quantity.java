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
 * Represents the quantity of an ingredient need for a cocktail. Contains the type of the quantity and its amount.
 */
public class Quantity {
    /** Type of the quantity used.*/
    @Expose(serialize = true, deserialize = true)
    private QuantityType type;

    /** Amount of quantity.*/
    @Expose(serialize = true, deserialize = true)
    private Float amount;

    /**
     * Constructor for quantity class.
     * @param type the type of the quantity used.
     * @param amount the amount of quantiy used.
     */
    public Quantity(QuantityType type, Float amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Empty constructor.
     */
    public Quantity() {
    }

    /**
     * Getter method for type.
     * @return the quantity type.
     */
    public QuantityType getType() {
        return type;
    }

    /**
     * Getter method for amount.
     * @return the quantity amount.
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * Setter method for {@code type}.
     * @param type the type to set.
     */
    public void setType(QuantityType type) {
        this.type = type;
    }

    /**
     * Setter method for {@code amount}.
     * @param amount the amount to set.
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * The Quantity equals method.
     * @param o the object to check.
     * @return true if objects are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return type == quantity.type &&
                Objects.equals(amount, quantity.amount);
    }

    /**
     * The Quantity hashcode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, amount);
    }
}
