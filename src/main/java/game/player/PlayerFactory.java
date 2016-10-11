package game.player;

import util.Config;
import util.KeyboardInputManager;

/**
 * The factory creating player objects.
 */
public final class PlayerFactory {

    private PlayerFactory() {

    }

    /**
     * Creates a new player instance with the keyboard mapping corresponding to the id.
     * @param id the id of the player.
     * @return the created player.
     */
    public static Player createPlayer(int id) {
        String leftKey, rightKey, shootKey;

        switch (id) {
            case 0:
                leftKey = Config.get("playerOneLeftKey");
                rightKey = Config.get("playerOneRightKey");
                shootKey = Config.get("playerOneShootKey");
                break;
            case 1:
                leftKey = Config.get("playerTwoLeftKey");
                rightKey = Config.get("playerTwoRightKey");
                shootKey = Config.get("playerTwoShootKey");
                break;
            default:
                leftKey = "";
                rightKey = "";
                shootKey = "";
                break;
        }

        Player player = new Player(id, leftKey, rightKey, shootKey);

        KeyboardInputManager.getInstance().addObserver(player);

        return player;
    }
}
