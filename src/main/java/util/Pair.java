package util;

/**
 * The class Pair contains a 2 values.
 *
 * @param <L>
 * @param <R>
 */
public class Pair<L, R> {
    /**
     * The left entry of the pair.
     */
    private L l;

    /**
     * The right entry of the pair.
     */
    private R r;

    /**
     * Creates a new pair.
     * @param l Value of left entry.
     * @param r Value of right entry.
     */
    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    /**
     * @return The left value of the pair.
     */
    public L getL() {
        return l;
    }

    /**
     * Sets the left value of the pair.
     * @param l The value of the left entry of the pair.
     */
    public void setL(L l) {
        this.l = l;
    }

    /**
     * @return The right value of the pair.
     */
    public R getR() {
        return r;
    }

    /**
     * Sets the right value of the pair.
     * @param r The value of the right entry of the pair.
     */
    public void setR(R r) {
        this.r = r;
    }
}
