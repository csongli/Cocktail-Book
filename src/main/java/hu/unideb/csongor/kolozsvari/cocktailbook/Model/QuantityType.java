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

    private String toPrint;

    QuantityType(String toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public String toString() {
        return toPrint;
    }
}
