package entities.powerups;

/**
 * Gives the player a extra rope to shoot.
 */
class ExtraRope extends DuringPowerUp {

    /**
     * Increase the amount of ropes the character can shoot.
     */
    void enableEffect() {
        //getTarget().increaseRopes();
    }

    /**
     * Disables the effect of the specific power up.
     */
    void disableEffect() {
        //getTarget().decreaseRopes();
    }

}
