/**
 * Created by Sterre on 07-09-16.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage)
    {
        theStage.setTitle( "Timeline Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image background = new Image("resources/background.jpg");
        final Image player     = new Image("resources/player.png");
        final Image wall       = new Image("resources/wall.png");

        System.out.println(background.getHeight());


        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);

                // background image clears canvas
                gc.drawImage(background, 0, 0);

                for (int i = 0; i < 512/64; i++) {
                    gc.drawImage(wall, i*64, 512 - 64);
                }

                //gc.scale(0.5, 0.5);
                gc.drawImage(player, x, 512 - 64 - player.getHeight());
                //gc.restore();

            }
        }.start();

        theStage.show();
    }
}