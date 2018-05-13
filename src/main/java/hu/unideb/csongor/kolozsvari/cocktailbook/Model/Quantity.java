package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

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
