package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

/**
 * Lists the quantity types that are used for the ingredients of a cocktail.
 */
public enum QuantityType {
    Ounce("oz"),
    MiliLiters("ml"),
    Wedge("wedges"),
    BarSpoon("bsp"),
    Piece("pieces"),
    Grams("grams"),
    Dash("dashes");

    /**
     * The formatted version of the QuantityType.
     */
    private String toPrint;

    /**
     * The constructor for QuantityType.
     * @param toPrint the formatted version of the QuantityType.
     */
    QuantityType(String toPrint) {
        this.toPrint = toPrint;
    }

    /**
     * The QuantityType toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return toPrint;
    }
}
