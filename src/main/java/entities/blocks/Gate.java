package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.DynamicEntity;
import entities.balls.ColoredBall;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * This class represents a gate that usually is placed in the middle of a game.
 * A character has to destroy the ball at his side to let the gate disappear.
 */
public class Gate extends AbstractBlock implements DynamicEntity {

    /**
     * Travel speed of the gate when it dies.
     */
    private static final double TRAVEL_SPEED = 100; // px/s
    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite GATE_SPRITE = new Sprite("blocks/plant.png");
    /**
     * The collision shape of the gate. Created around the original sprite.
     */
    private static final Rectangle GATE_SHAPE = new Rectangle(GATE_SPRITE);
    /**
     * The color of the balls of which need to be gone before this gate opens.
     */
    private final ColoredBall.Color color;
    /**
     * The threshold below which the gate can disappear.
     */
    private final double groundThreshold;

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position The position of the block.
     * @param color    The color of the ball this gate is for.
     */
    public Gate(final Vec2d position, final ColoredBall.Color color) {
        super(position);
        setSprite(GATE_SPRITE);
        setShape(new Rectangle(GATE_SHAPE));
        setDepth(1);

        this.color = color;
        groundThreshold = ((Rectangle) getShape()).getBottom();
    }

    @Override
    public void update(double timeDifference) {
        long ballCount = getLevel()
                .getEntities().stream()
                .filter(this::myColor)
                .count();

        if (ballCount == 0) {
            setYSpeed(TRAVEL_SPEED);

            if (getY() > groundThreshold) {
                getLevel().removeEntity(this);
            }
        }
    }

    /**
     * Boolean that returns true if the color of a ColoredBall
     * is the same as the color that is necessary to open the gate.
     * @param entity AbstractEntity entity.
     * @return       true if the entity is a ColoredBall and the colors are the same.
     */
    private boolean myColor(AbstractEntity entity) {
        return entity instanceof ColoredBall
                && ((ColoredBall) entity).getColor() == color;
    }
}
