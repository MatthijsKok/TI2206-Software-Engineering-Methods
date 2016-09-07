import game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
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
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
     
        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add( canvas );
     
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        
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
            	gc.setFill(Color.ALICEBLUE);
                gc.fillRect(0, 0, 512, 512);
                
                // And redraw
            	game.draw(gc);
            }
        }.start();
     
        theStage.show();
    }
}