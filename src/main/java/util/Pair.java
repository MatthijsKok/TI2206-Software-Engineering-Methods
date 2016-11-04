package util;

/**
 * The Pair class represents a key value pair.
 * @param <Key> Type of the key of the pair.
 * @param <Value> Type of the value of the pair.
 */
public class Pair<Key, Value> {

    /**
     * The key of the pair.
     */
    private Key key;
    /**
     * The value of the pair.
     */
    private Value value;

    /**
     * Creates a new key value pair.
     * @param key Key of the pair.
     * @param value Value of the pair.
     */
    public Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return The key of the pair.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key of the pair.
     * @param key The key of the pair.
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return The value of the pair.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the right value of the pair.
     * @param value The value of the pair.
     */
    public void setValue(Value value) {
        this.value = value;
    }
}
