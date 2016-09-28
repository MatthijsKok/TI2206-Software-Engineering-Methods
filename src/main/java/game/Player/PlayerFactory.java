package game.Player;

import util.Config;

/**
 * The factory creating Player objects.
 */
public final class PlayerFactory {

    private PlayerFactory() {

    }

    /**
     * Creates a new Player instance with the keyboard mapping corresponding to the id.
     * @param id the id of the player.
     * @return the created Player.
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

        return new Player(leftKey, rightKey, shootKey);
    }
}
