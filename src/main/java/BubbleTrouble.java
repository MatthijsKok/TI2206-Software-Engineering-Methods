import game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.GameCanvasManager;
import util.KeyboardInputManager;

public class BubbleTrouble extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
    	theStage.setTitle("Bubble Trouble");
    	 
        Group root = new Group();
        Scene scene = new Scene(root);
        theStage.setScene(scene);

        KeyboardInputManager.getInstance().addScene(scene);
        root.getChildren().add(GameCanvasManager.getInstance().getCanvas());
        
        final Game game = Game.getInstance();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
            	// Update the game
            	game.update();
            	
            	// Clear canvas
                GraphicsContext gc = GameCanvasManager.getInstance().getContext();
            	gc.setFill(Color.ALICEBLUE);
                gc.fillRect(0, 0, 512, 512);
                
                // And redraw
            	game.draw();
            }
        }.start();
     
        theStage.show();
    }
}