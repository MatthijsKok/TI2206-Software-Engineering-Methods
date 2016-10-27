package game;

import game.player.Player;
import game.player.PlayerFactory;
import game.state.GameLostState;
import game.state.GameState;
import game.state.GameWonState;
import game.state.InProgressState;
import game.state.LevelLostState;
import game.state.LevelWonState;
import game.state.NotStartedState;
import javafx.animation.AnimationTimer;
import level.Level;
import level.LevelTimer;
import ui.HeadsUpDisplay;
import ui.MultiPlayerHUD;
import ui.SinglePlayerHUD;
import util.KeyboardInputManager;
import util.SceneManager;
import util.logging.Logger;
import util.sound.MultiSoundEffect;
import util.sound.Music;
import util.sound.SoundEffect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that handles things on a game related level.
 */
public final class Game {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();
    /**
     * Defines how many nano seconds there are in one second.
     */
    private static final double NANO_SECONDS_IN_SECOND = 1000000000.0;
    /**
     * Defines the maximum time span a frame can simulate.
     */
    private static final double MAX_FRAME_DURATION = 0.033333333;

    /**
     * The state of the game.
     */
    private static GameState state = new NotStartedState();
    /**
     * The UI of the game.
     */
    private static HeadsUpDisplay hud;
    /**
     * A list containing all the players that play the game.
     */
    private static final List<Player> PLAYERS = new ArrayList<>();
    /**
     * A list containing all the levels in the game.
     */
    private static List<Level> levels = new ArrayList<>();
    /**
     * The current level.
     */
    private static int currentLevel = 0;
    /**
     * The last time recorded.
     */
    private static long lastNanoTime;
    /**
     * The timer that handles the main update loop.
     */
    private static AnimationTimer timer = null;

    private Game() {

    }

    private static void setUpAnimationLoop() {
        timer = new AnimationTimer() {
            public void handle(final long currentNanoTime) {
                update();
                draw();
            }
        };
    }

    /**
     * Removes the old players and creates new ones.
     *
     * @param count the amount of players.
     */
    public static void setPlayerCount(int count) {
        PLAYERS.forEach(Player::clearCharacter);
        PLAYERS.clear();

        for (int i = 0; i < count; i++) {
            PLAYERS.add(PlayerFactory.createPlayer(i));
        }
    }

    /**
     * Gets the players in the game.
     *
     * @return The list of players in the game.
     */
    public static List<Player> getPlayers() {
        return PLAYERS;
    }

    /**
     * Creates new levels from a list of level files.
     * @param levelFiles the list of files to create levels from.
     */
    public static void setLevelsFromFiles(List<String> levelFiles) {
        setLevels(levelFiles.stream()
                .map(Level::new)
                .collect(Collectors.toList()));
    }

    /**
     * Sets the game's levels to these levels.
     * @param levels the list of levels.
     */
    public static void setLevels(List<Level> levels) {
        Game.levels = levels;
    }

    /**
     * @return Level - The current level that is playing.
     */
    public static Level getCurrentLevel() {
        return levels.get(currentLevel);
    }

    /**
     * @return Boolean - Whether the game has a next level.
     */
    private static boolean hasNextLevel() {
        return currentLevel + 1 < levels.size();
    }

    /**
     * @return Returns the state of the game.
     */
    public static GameState getState() {
        return state;
    }

    /**
     * Sets the state of the game.
     * @param state GameState - The state the game should go to.
     */
    public static void setState(GameState state) {
        Game.state = state;
    }

    /**
     * Loads and starts the first level.
     * @throws IOException when the first level's file is not found.
     */
    public static void start() throws IOException {
        if (timer == null) {
            setUpAnimationLoop();
        }

        PLAYERS.forEach(Player::resetLives);

        currentLevel = 0;
        getCurrentLevel().load();
        setState(new InProgressState(getCurrentLevel()));
        timer.start();
        lastNanoTime = System.nanoTime();
    }

    /**
     * Stops the game and returns to the main menu.
     */
    public static void stop() {
        setState(new NotStartedState());
        if (timer != null) {
            timer.stop();
        }

        getCurrentLevel().unload();

        Music.stopMusic();
        Music.setMusic("menu_gusty_garden.mp3");
        Music.startMusic();

        SceneManager.goToScene("MainMenu");
    }

    /**
     * Win the current level.
     * @throws IOException When the level file is not found.
     */
    public static void winLevel() throws IOException {
        SoundEffect.TIME_ALMOST_UP.getAudio().stop();
        Music.stopMusic();

        PLAYERS.forEach(Player::clearCharacter);

        if (hasNextLevel()) {
            MultiSoundEffect.LEVEL_WON.playRandom();
            getCurrentLevel().unload();
            currentLevel++;
            getCurrentLevel().load();
            setState(new LevelWonState(getCurrentLevel()));
        } else {
            SoundEffect.GAME_WON.play();
            setState(new GameWonState());
        }
    }

    /**
     * Lose the current level.
     * @param timeUp Boolean - Whether the level was lost
     *               by a lack of time.
     * @throws IOException When the level file cannot be loaded.
     */
    public static void loseLevel(final boolean timeUp) throws IOException {
        if (timeUp) {
            PLAYERS.forEach(player -> player.increaseLives(-1));
        }

        PLAYERS.forEach(Player::clearCharacter);

        long playersAlive = PLAYERS.stream()
                .filter(player -> player.getLives() > 0)
                .count();

        if (playersAlive > 0) {
            getCurrentLevel().unload();
            getCurrentLevel().load();
            setState(new LevelLostState(getCurrentLevel()));
        } else {
            MultiSoundEffect.GAME_OVER.playRandom();
            setState(new GameLostState());
        }
    }

    /**
     * Updates the game.
     */
    private static void update() {
        LOGGER.debug("Updating the game...");
        long currentNanoTime = System.nanoTime();

        // Gives the time difference in seconds
        double dt = Math.min(
                (currentNanoTime - lastNanoTime) / NANO_SECONDS_IN_SECOND,
                MAX_FRAME_DURATION);
        LOGGER.trace("Time difference since last update: " + dt + " seconds.");

        lastNanoTime = currentNanoTime;

        if (SceneManager.getCurrentScene().equals(SceneManager.getScene("Game"))) {
            state.update(dt);
        }

        // Clear the keyboard
        KeyboardInputManager.update();

        try {
            LOGGER.writeLogRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the current level.
     */
    private static void draw() {
        LOGGER.debug("Drawing the game...");

        getCurrentLevel().draw();

        if (hud != null) {
            hud.draw();
        }

        state.draw();
    }

    /**
     * Sets the timer the Game's HUD should draw.
     * @param timer LevelTimer - The timer to draw.
     */
    public static void setTimer(LevelTimer timer) {
        switch (PLAYERS.size()) {
            case 1:
                hud = new SinglePlayerHUD(timer, PLAYERS.get(0));
                break;
            case 2:
                hud = new MultiPlayerHUD(timer, PLAYERS.get(0), PLAYERS.get(1));
                break;
            default:
                hud = new HeadsUpDisplay(timer);
                break;
        }
    }
}
