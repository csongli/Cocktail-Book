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
