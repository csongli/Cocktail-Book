package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

/**
 * Represents a pair which has a key and a value signed to it.
 * @param <U> key of the pair.
 * @param <V> value of the pair.
 */
public class Pair<U, V> {

    /**The key of the pair. */
    private final U key;

    /**The value of the pair. */
    private final V value;

    /**
     * Constructor for the pair class.
     * @param first key of the pair
     * @param value value of the pair
     */
    public Pair(U first, V value) {
        this.key = first;
        this.value = value;
    }

    /**
     * The Pair equals method.
     * @param o the object
     * @return true if objects are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        return value != null ? value.equals(pair.value) : pair.value == null;
    }

    /**
     * The Pair hashcode method.
     * @return the hashed object.
     */
    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    /**
     * The Pair toString method.
     * @return the object formatted as a string.
     */
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    /**
     * Generic pair constructor.
     * @param a parameter a of the first type.
     * @param b parameter b of the first type.
     * @param <U> key of pair.
     * @param <V> value of pair.
     * @return the new pair.
     */
    public static <U, V> Pair <U, V> of(U a, V b) {
        return new Pair<>(a, b);
    }

    /**
     * Getter method for key.
     * @return the key of the pair.
     */
    public U getKey() {
        return key;
    }

    /**
     * Getter method for value.
     * @return the value of the pair.
     */
    public V getValue() {
        return value;
    }
}