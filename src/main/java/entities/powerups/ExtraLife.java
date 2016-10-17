package entities.powerups;

/**
 * Adds a life to the players total.
 */
class ExtraLife extends InstantPowerUp {

    @Override
    void applyEffect() {
        getTarget().increaseLife();
    }
}
