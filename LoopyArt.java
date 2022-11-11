import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *  This program draws several kinds of random "art".  A new artwork is
 *  displayed every time the user clicks the window.
 */
public class LoopyArt extends Application {

    /**
     * Draws one work of random art, with the type selected at random.
     */
    public void drawPicture(GraphicsContext g) {
    	
    	int width = (int) g.getCanvas().getWidth();		// width of canvas
    	int height = (int) g.getCanvas().getHeight();   // height of canvas
    	
        int artType; // For randomly selecting which type of art to draw.
        artType = (int)(4*Math.random() + 1);
        
        if(artType == 1) {
        	pseudoPollock(g, width, height);
        }
        
        System.out.println("Draw art type number " + artType);
        
        /* Draw the art!! */

    } // end drawPicture()

    
    /**
     * 
     * @param g graphics context used to draw.
     * @param canvasWidth the width of the canvas.
     * @param canvasHeight the height of the canvas.
     */
    public void pseudoPollock(GraphicsContext g, int canvasWidth, int canvasHeight) {
    	
    	final int NUMBER_OF_LINES = (int) (2000 * Math.random() + 500); // random amount of lines between 500 and 2000
    	int randX1, randY1; // randomly selected x,y-coordinates
    	int randX2, randY2;
    	
    	// fill background with random gray color
    	g.setFill( Color.gray(Math.random()) );
    	g.fillRect(0, 0, canvasWidth, canvasHeight);
    	
    	
    	for(int i = 0; i < NUMBER_OF_LINES; i++) {

    		randX1 = (int) (canvasWidth * Math.random());
    		randY1 = (int) (canvasHeight * Math.random());
    		
    		randX2 = (int) (canvasWidth * Math.random());
    		randY2 = (int) (canvasHeight * Math.random());

    		// get a random pastel color, random line width, then draw line
    		g.setStroke( Color.hsb(360*Math.random(), 0.3, 1) );
    		g.setLineWidth(Math.random());
    		g.strokeLine(randX1, randY1, randX2, randY2);
    	}
    	
    } // end pseudoPollock()
    
    /**
     * 
     * @param g graphics context used to draw.
     * @param canvasWidth the width of the canvas.
     * @param canvasHeight the height of the canvas.
     */
    public void pseudoKandinsky(GraphicsContext g, int canvasWidth, int canvasHeight) {
    	
    }

    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------

    public void start(Stage stage) {
        int width = 800;   // The width of the image.  You can modify this value!
        int height = 600;  // The height of the image. You can modify this value!
        Canvas canvas = new Canvas(width,height);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        drawPicture(graphics);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Loopy Random Art"); // STRING APPEARS IN WINDOW TITLEBAR!
        stage.show();
        stage.setResizable(false);
        canvas.setOnMousePressed( e -> drawPicture(graphics) );
    } 

    public static void main(String[] args) {
        launch();
    }

} // end of Art