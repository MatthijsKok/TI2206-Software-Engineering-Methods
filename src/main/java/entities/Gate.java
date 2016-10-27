package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import graphics.Sprite;

/**
     * Plant, the mid level gate.
     */
    public class Gate extends AbstractBlock {

        /**
         * Travel speed of the gate.
         */
        private static final double TRAVEL_SPEED = 100; // px/s

        /**
         * The sprite used for the block object in the game.
         */
        private static final Sprite GATE_SPRITE = new Sprite("plant.png");

        /**
         * collision shape of the gate. Created around the original sprite.
         */
        private static final Rectangle GATE_SHAPE = new Rectangle(GATE_SPRITE);

        /**
         * Creates a new FloorBlock at position (x,y).
         * @param position the position of the block
         */
        public Gate(final Vec2d position) {
            super(position);
            setSprite(GATE_SPRITE);
            setShape(new Rectangle(GATE_SHAPE));
            setDepth(1);
        }

        /**
         * moves the gate down and removes the entity.
         */
        public void setYSpeedAndDie() {
            setYSpeed(TRAVEL_SPEED);
//            if (getY() > getLevel().getHeight()) {
//                getLevel().removeEntity( getLevel().getEntities().stream()
//                        .filter(e -> e instanceof Gate).findAny().orElse(null));
//            }
        }
    }
