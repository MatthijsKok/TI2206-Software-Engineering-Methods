package entities.powerups;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * Created by Sterre on 13-10-16.
 */
public class ExtraTime implements StaticPowerUp {

    int timeRemaining;

    public int getTimeRemaining(){
        return 0;
    }

    public int decreaseTimeRemaining(){
        return 0;
    }

    @Override
    public void applyEffect(){
    }

}
