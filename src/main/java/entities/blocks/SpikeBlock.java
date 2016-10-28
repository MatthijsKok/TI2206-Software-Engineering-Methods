package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Represents a SpikeBlock entity used for the ceiling in the game.
 */
public class SpikeBlock  extends AbstractBlock {
    /**
     * The sprite used for the spike block object in the game.
     */
    private static final Sprite SPIKE_SPRITE = new Sprite("images/blocks/spikes.png");

    /**
     * Creates a new SpikeBlock at position (x,y).
     * @param position Vec2d - the position of the block
     */
    public SpikeBlock(final Vec2d position) {
        super(position);
        setSprite(SPIKE_SPRITE);
        setShapeToSprite();
        setDepth(1);
    }
}
