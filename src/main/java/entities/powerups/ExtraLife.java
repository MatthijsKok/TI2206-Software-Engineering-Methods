package entities.powerups;

/**
 * Adds a life to the players total.
 */
class ExtraLife extends InstantPowerUp {

    /**
     * Increases the characters life.
     */
    void applyEffect() {
        getTarget().increaseLife();
    }
}
