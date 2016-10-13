package game.player;

import util.Config;

/**
 * The factory creating player objects.
 */
public final class PlayerFactory {

    private PlayerFactory() {

    }

    /**
     * Creates a new player instance with the keyboard mapping corresponding to the playerId.
     * @param playerId the id of the player.
     * @return the created player.
     */
    public static Player createPlayer(final int playerId) {
        String leftKey, rightKey, shootKey;

        switch (playerId) {
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
