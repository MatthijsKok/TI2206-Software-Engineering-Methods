package entities.powerups;

/**
 * Represents an AbstractPowerUp which effect is instant.
 */
abstract class AbstractInstantPowerUp extends AbstractPowerUp {
    /**
     * Enables the effect of the specific power up.
     */
    /* default */ abstract void applyEffect();

    @Override
    protected final void activate() {
        applyEffect();
    }
}
