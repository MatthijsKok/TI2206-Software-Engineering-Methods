import game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.GameCanvas;

public class BubbleTrouble extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
    	theStage.setTitle("BubbleTrouble");
    	 
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        root.getChildren().add(GameCanvas.getInstance().getCanvas());
        
        final Game game = new Game();
     
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	double t = (currentNanoTime - startNanoTime) / 1000000000.0;
            	//lastNanoTime = System.nanoTime();
            	
            	// Update the game
            	game.update(t);
            	
            	// Clear canvas
                GraphicsContext gc = GameCanvas.getInstance().getContext();
            	gc.setFill(Color.ALICEBLUE);
                gc.fillRect(0, 0, 512, 512);
                
                // And redraw
            	game.draw();
            }
        }.start();
     
        theStage.show();
    }
}