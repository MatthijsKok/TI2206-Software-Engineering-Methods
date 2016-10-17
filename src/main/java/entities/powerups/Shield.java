package entities.powerups;

/**
 * Gives the player a shield that allows it to be hit one time.
 */
class Shield extends DuringPowerUp {

    @Override
    void enableEffect() {
        //getTarget().setInvincible(true);
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    void disableEffect() {
        //getTarget().setInvincible(false);
    }

}
