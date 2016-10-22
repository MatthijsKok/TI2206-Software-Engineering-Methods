package entities.character;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Utility class that determines the sprites for characters.
 */
final class CharacterSprites {

    /**
     * The idle sprites of characters.
     */
    private static final Sprite[] IDLE_SPRITES = {
            new Sprite("player/mario_idle.png", 1, new Vec2d(8, 32 - 16)),
            new Sprite("player/yoshi_idle.png", 1, new Vec2d(8, 32 - 16))
    };

    /**
     * The running sprites of characters.
     */
    private static final Sprite[] RUNNING_SPRITES = {
            new Sprite("player/mario_running.png", 8, new Vec2d(11, 35 - 16)),
            new Sprite("player/yoshi_running.png", 8, new Vec2d(11, 37 - 16))
    };

    private CharacterSprites() {

    }

    /**
     * Returns the idle sprite corresponding to id.
     * @param playerId the playerId of the player.
     * @return the sprite of the entities.character.
     */
    static /* default */ Sprite getIdleSprite(final int playerId) {
        return new Sprite(IDLE_SPRITES[playerId]);
    }

    /**
     * Returns the running sprite corresponding to playerId.
     * @param playerId the playerId of the player.
     * @return the sprite of the entities.character.
     */
    static /* default */ Sprite getRunningSprite(final int playerId) {
        return new Sprite(RUNNING_SPRITES[playerId]);
    }
}
