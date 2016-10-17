package entities.powerups;
import com.sun.javafx.geom.Vec2d;
import entities.Character;
import graphics.Sprite;

/**
 * Interface that all power-ups should implement.
 */
abstract class PowerUp {

    /**
     * The character which picked up the power-up.
     */
    private Character target;

    /**
     * The sprite of the power-up.
     */
    private Sprite sprite;

    /**
     * Enables the effect of the specific power-up.
     * @param character The character that picked up the power-up.
     */
    void setTarget(Character character) {
        target = character;
    }

    /**
     * @return The character which picked up the power-up.
     */
    Character getTarget() {
        return target;
    }

    /**
     * Sets the sprite of this power-up.
     * @param sprite The sprite to set.
     */
    void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Draws the sprite of the power-up.
     * @param position The position to draw the sprite on.
     */
    public void draw(final Vec2d position) {
        if (sprite != null) {
            sprite.draw(position);
        }
    }
}
