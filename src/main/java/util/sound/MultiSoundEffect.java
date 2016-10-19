package util.sound;

import java.util.Random;

/**
 * Class used for creating and playing sounds effects that can differ depending on conditions.
 */
public final class MultiSoundEffect {

    /**
     * Sound effect that will be played when the player loses a life.
     */
    public static final MultiSoundEffect PLAYER_LOSES_LIFE =
            new MultiSoundEffect(new String[]{"mario_life_lost.wav", "yoshi_life_lost.wav"});

    /**
     * Sound effect that will be played when the player loses a life.
     */
    public static final MultiSoundEffect PLAYER_OUT_OF_LIVES =
            new MultiSoundEffect(new String[]{"mario_oh_no.wav", "sad_yoshi.wav"});

    /**
     * Sound that plays when the game is over.
     */
    public static final MultiSoundEffect GAME_OVER =
            new MultiSoundEffect(new String[]{"game_over.wav", "game_over2.wav"});

    /**
     * The list containing the sound effects that can be played.
     */
    private SoundEffect[] effectList;

    /**
     * Creates a new SoundEffect Object.
     * @param soundEffectNames Array containing the names of the soundEffect files, for example 'effect.wav'.
     */
    private MultiSoundEffect(String[] soundEffectNames) {
        effectList = new SoundEffect[soundEffectNames.length];

        for (int i = 0; i < soundEffectNames.length; i++) {
            effectList[i] = new SoundEffect(soundEffectNames[i]);
        }
    }

    /**
     * Plays the given index of the MulitSoundEffect.
     * @param index The index that should be played.
     */
    public void play(int index) {
        if (index >= 0 && index < effectList.length) {
            effectList[index].play();
        }
    }

    /**
     * Plays a random sound contained in the random sound effect.
     */
    public void playRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(effectList.length);
        play(randomIndex);
    }
}
