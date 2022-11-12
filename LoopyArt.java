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
        else if(artType == 2) {
        	pseudoKandinsky(g, width, height);
        }
        else if(artType == 3) {
        	nightSky(g, width, height);
        }
        else if(artType == 4) {
        	colorBurst(g, width, height);
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
     * Creates an imitation Kandinsky painting by drawing rows and columns of
     * 		squares and ovals.
     * @param g graphics context used to draw.
     * @param canvasWidth the width of the canvas.
     * @param canvasHeight the height of the canvas.
     */
    public void pseudoKandinsky(GraphicsContext g, int canvasWidth, int canvasHeight) {

    	final int ROWS = 8; // the number of rows
    	final int COLS = 8; // the number of columns

    	int squareX, squareY;

    	double squareWidth, squareHeight;

    	// assign variables
    	squareWidth = (canvasWidth - 8) / COLS;
    	squareHeight = (canvasHeight - 8) / ROWS;

    	squareX = 0;
    	squareY = 0;

    	// Fill background with black
    	g.setFill(Color.BLACK);
    	g.fillRect(0, 0, canvasWidth, canvasHeight);


    	for(int row = 0; row < 8; row++) {

    		for(int col = 0; col < 8; col++) {

    			// pick random pastel color, draw square
    			g.setFill(Color.hsb(360*Math.random(), 0.3, 1)); 			
    			g.fillRect(squareX, squareY, squareWidth, squareHeight);

    			// pick random pastel color, draw oval
    			g.setFill(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
    			g.fillOval(squareX + (squareWidth/5), squareY + (squareHeight/5) , squareWidth/1.75, squareHeight/1.75);

    			g.setStroke(Color.BLACK);
    			g.setLineWidth(.99);
    			g.strokeOval(squareX + (squareWidth/5), squareY + (squareHeight/5) , squareWidth/1.75, squareHeight/1.75);

    			squareX += squareWidth + 1;


    		}
    		squareX = 0;
    		squareY += squareHeight + 1;
    	}
    } // end pseudoKandinsky()

    /**
     * Draws a night sky with a random amount of stars.
     * @param g The Graphics Context.
     * @param canvasWidth The canvas width.
     * @param canvasHeight The canvas height.
     */
    public void nightSky(GraphicsContext g, int canvasWidth, int canvasHeight) {
    	// width and height of the canvas
    	double canvasWidth1 = g.getCanvas().getWidth();
    	double canvasHeight1 = g.getCanvas().getHeight();

    	Color c;
    	int red, green, blue; 			// RGB values to draw the sky
    	int heightGradient, fineness;	// Fineness determines how the color changes in the sky, heightGradient is the height of the rectangle thats drawn
    	int x, y;						// 

    	fineness = 200;
    	heightGradient = (int) (canvasHeight1 / fineness);

    	y = (int) (canvasHeight1 - (canvasHeight1 / 2.25));

    	red = 170;
    	green = 0; 
    	blue = 255;

    	// background sky
    	while(y >= 0) {
    		c = Color.rgb(red, green, blue);
    		g.setFill(c);
    		g.fillRect(0, y, canvasWidth1, heightGradient);

    		y = y - heightGradient;
    		//blue = blue - 1;
    		red = red - 1;
    	}

    	// stars
    	g.setFill(Color.WHITE);
    	int randomStars = (int) (500 + (Math.random() * 1000));
    	for(int i = 0; i < randomStars; i++) {
    		int randomX, randomY; // Random x/y coordinates

    		randomX = (int) (Math.random() * canvasWidth1);
    		randomY = (int) (Math.random() * canvasHeight1);

    		g.fillRect(randomX, randomY, 2, 2);
    	}

    	// setting sun
    	g.setFill(Color.DARKORANGE);
    	g.fillOval(canvasWidth1 / 2.75, (canvasHeight1 - (canvasHeight1 / 2) ), 175, 175);

    	// moon
    	g.setFill(Color.YELLOW);
    	g.fillOval(canvasWidth - (canvasWidth / 10), canvasHeight / 10, 50, 50);
    	g.setStroke(Color.BLACK);
    	g.strokeOval(canvasWidth - (canvasWidth / 10), canvasHeight / 10, 50, 50);
    	
    	// foreground grass
    	g.setFill(Color.DARKGREEN);
    	g.fillRect(0, (canvasHeight1 - (canvasHeight1 / 2.25) ), canvasWidth1, canvasHeight1 );

    	// text
    	g.setFont(Font.font(20));
    	g.setFill(Color.ALICEBLUE);
    	g.fillText("Goodnight, World!", 10, (canvasHeight1 - (canvasHeight1 / 20)) );

    } // end nightSky()
    
    /**
     * Draws 500 randomly colored lines extending from the center of the canvas.
     * @param g The graphics context.
     * @param canvasWidth The canvas width.
     * @param canvasHeight The canvas height.
     */
    public void colorBurst(GraphicsContext g, int canvasWidth, int canvasHeight) {

    	double x1, y1, x2, y2; // x and y-coordinates for the lines

    	// Fill background with black
    	g.setFill(Color.BLACK);
    	g.fillRect(0, 0, canvasWidth, canvasHeight);

    	// get x/y-coordinate in middle of screen, this doesn't change
    	x1 = canvasWidth / 2;
    	y1 = canvasHeight / 2;
    	
    	for(int i = 0; i < 500; i++) {

    		// Set random pastel color
    		g.setStroke(Color.hsb(360*Math.random(), 0.3, 1));
    		
    		g.setLineWidth(Math.random());
    		
    		x2 = Math.random() * canvasWidth;
    		y2 = Math.random() * canvasHeight;
    		//
    		g.strokeLine(x1, y1, x2, y2);

    	}
    	
    } // end colorBurst()

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