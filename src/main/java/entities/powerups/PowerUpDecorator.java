package entities.powerups;

abstract class PowerUpDecorator implements DynamicPowerUp {

    protected DynamicPowerUp tempDynamicPowerUp;

    public PowerUpDecorator(DynamicPowerUp newDynamicPowerUp) {

        tempDynamicPowerUp = newDynamicPowerUp;

    }

}
