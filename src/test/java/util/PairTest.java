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
        assertThat(pair.getKey(), is(l1));
    }

    @Test
    public void testGetR() {
        assertThat(pair.getValue(), is(r1));
    }

    @Test
    public void testSetL() {
        final char l2 = 'b';
        pair.setKey(l2);
        assertThat(pair.getKey(), is(l2));
    }

    @Test
    public void testSetR() {
        final int r2 = 2;
        pair.setValue(r2);
        assertThat(pair.getValue(), is(r2));
    }
}
