package hu.unideb.csongor.kolozsvari.cocktailbook.Model;

public class Pair<U, V> {
    private final U key;
    private final V value;

    public Pair(U first, V value) {
        this.key = first;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        return value != null ? value.equals(pair.value) : pair.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    public static <U, V> Pair <U, V> of(U a, V b) {
        return new Pair<>(a, b);
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}