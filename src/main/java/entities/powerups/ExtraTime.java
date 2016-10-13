package entities.powerups;

public class ExtraTime implements StaticPowerUp {

    int timeRemaining;

    public int getTimeRemaining() {
        return 0;
    }

    public int decreaseTimeRemaining() {
        return 0;
    }

    @Override
    public void applyEffect() {
    }

}
