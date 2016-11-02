package util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the Pair class.
 */
public class PairTest {

    private final char l1 = 'a';
    private final int r1 = 1;

    private Pair<Character, Integer> pair = new Pair<>(l1, r1);

    @Test
    public void testGetL() {
        assertThat(pair.getL(), is(l1));
    }

    @Test
    public void testGetR() {
        assertThat(pair.getR(), is(r1));
    }

    @Test
    public void testSetL() {
        final char l2 = 'b';
        pair.setL(l2);
        assertThat(pair.getL(), is(l2));
    }

    @Test
    public void testSetR() {
        final int r2 = 2;
        pair.setR(r2);
        assertThat(pair.getR(), is(r2));
    }
}
