package util;

/**
 * The class Pair contains a 2 values.
 * @param <LeftValue> Type of the left value of the pair.
 * @param <RightValue> Type of the right value of the pair.
 */
public class Pair<LeftValue, RightValue> {

    /**
     * The left entry of the pair.
     */
    private LeftValue leftValue;
    /**
     * The right entry of the pair.
     */
    private RightValue rightValue;

    /**
     * Creates a new pair.
     * @param l Value of left entry.
     * @param r Value of right entry.
     */
    public Pair(LeftValue l, RightValue r) {
        this.leftValue = l;
        this.rightValue = r;
    }

    /**
     * @return The left value of the pair.
     */
    public LeftValue getLeftValue() {
        return leftValue;
    }

    /**
     * Sets the left value of the pair.
     * @param leftValue The value of the left entry of the pair.
     */
    public void setLeftValue(LeftValue leftValue) {
        this.leftValue = leftValue;
    }

    /**
     * @return The right value of the pair.
     */
    public RightValue getRightValue() {
        return rightValue;
    }

    /**
     * Sets the right value of the pair.
     * @param rightValue The value of the right entry of the pair.
     */
    public void setRightValue(RightValue rightValue) {
        this.rightValue = rightValue;
    }
}
