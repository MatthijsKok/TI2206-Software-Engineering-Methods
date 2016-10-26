package entities.powerups;

/**
 * Represents an AbstractPowerUp which effect is instant.
 */
abstract class AbstractInstantPowerUp extends AbstractPowerUp {
    /**
     * Enables the effect of the specific power up.
     */
    abstract /* default */ void applyEffect();

    @Override
    /* default */ final void activate() {
        applyEffect();
    }
}
