package entities.character;

import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.balls.AbstractBall;
import geometry.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import util.CanvasManager;

/**
 * The shield class represents a shield that protects a entities.character.
 */
public class Shield extends AbstractEntity implements CollidingEntity {

    /**
     * The opacity of a shield.
     */
    private static final double SHIELD_OPACITY = 0.4;
    /**
     * The radius of a shield.
     */
    private static final double SHIELD_RADIUS = 32;
    /**
     * The amount of degrees in a circle.
     */
    private static final double FULL_CIRCLE = 360;
    /**
     * The collision shape of a shield.
     */
    private static final Circle SHIELD_SHAPE = new Circle(SHIELD_RADIUS);

    /**
     * Creates a new shield around a entities.character instance.
     * @param character Character this shield belongs to.
     */
    Shield(final Character character) {
        super(character.getPosition());
        bindPosition(character.getPosition());
        setShape(new Circle(SHIELD_SHAPE));
        setVisibility(false);
    }

    /**
     * Activates the shield.
     */
    void activate() {
        setVisibility(true);
    }

    @Override
    public void collideWith(final AbstractEntity entity) {
        if (entity instanceof AbstractBall) {
            collideWithBall();
        }
    }

    private void collideWithBall() {
        setVisibility(false);
    }

    @Override
    public void draw() {
        if (isVisible()) {
            GraphicsContext gc = CanvasManager.getContext();
            gc.setFill(Color.GOLD);
            gc.setGlobalAlpha(SHIELD_OPACITY);
            gc.fillArc(
                    getX() - SHIELD_RADIUS, getY() - SHIELD_RADIUS,
                    SHIELD_RADIUS * 2, SHIELD_RADIUS * 2,
                    0, FULL_CIRCLE, ArcType.ROUND);
            gc.setGlobalAlpha(1);
        }
    }
}
