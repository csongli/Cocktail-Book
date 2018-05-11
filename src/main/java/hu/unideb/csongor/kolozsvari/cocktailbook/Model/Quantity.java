package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

import com.google.gson.annotations.Expose;

/**
 * Represents the quantity of an ingredient need for a cocktail. Contains the type of the quantity and its amount.
 */
public class Quantity {
    /** Type of the quantity used.*/
    @Expose(serialize = true, deserialize = true)
    private QuantityType type;

    /** Amount of quantity*/
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

    /** Setter method for {@code type}. */
    public void setType(QuantityType type) {
        this.type = type;
    }

    /** Setter method for {@code amount}. */
    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
