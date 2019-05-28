import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;


public class gotchaHW extends PApplet {

   
    int score = 0;
    int timer;
    int gameDuration = 30 * 1000;
   
    final int canvasWidth  = 500;
    final int canvasHeight = 500;
    final static int maxDisks = 10;
    float[] yValue = {70, 140, 200, 237, 233, 56, 122, 403, 418, 396, 344};
  


    
	PImage bg;
	PImage ellipse;

	ArrayList<Disk> dList = new ArrayList<Disk>();
   public static void main(String[] args) {
	   PApplet.main(gotchaHW.class.getName());
   }
    public void settings() {
        size(canvasWidth, canvasHeight);
        smooth();
    }
    // setup() runs one time at the beginning of your program
    @Override()
    public void setup() {
        // Create a disk
    	bg = loadImage("/Users/ashleybattiste/Desktop/pixelbg.gif");
    	ellipse = loadImage("/Users/ashleybattiste/Desktop/star.gif");
    	ellipse.resize(100, 100);
    	timer = millis() + gameDuration;
    	for (int s = 0; s<maxDisks; s++) {
    		
    	}
         for (int i = 0; i < maxDisks; i++) {
        
			// With ArrayList
             dList.add(new Disk(random(0, 255), random(0, 255), random(0, 255),
                     random(0, 255), yValue[i], 2 + (2 * i)));

             System.out.println("\nNumber of Disks: " + dList.size() + "\n");
         }
    }

    // draw() is called repeatedly in an infinite loop.
    // By default, it is called about 60 times per second.
    @Override()
    public void draw() {
        // Erase the background, if you don't, the previous shape(s) will 
        // still be displayed
    	
        eraseBackground();

        // Move the shape, i.e. calculate the next x and y position
        // where the shape will be drawn.
        //TODO 
        for (int j = 0; j < maxDisks; j++) {
            dList.get(j).calcCoords();
            dList.get(j).drawShape();
            dList.get(j).displayPointValue();
       
        //TODO text("Score: " + this.score, ...);
            if (millis() >= timer) {  // Game over
                // Clear the canvas
            	
                background(bg);
                
                // Output the final score
                // TODO: Output final score
                fill(255);
                textSize(20);
                text("Final Score:" + score, 300, 200);
                
                // Let the user click when finished reading score
                // TODO: Output message to click mouse to exit
                text("Please click to exit!", 300, 100);
                if (this.mousePressed) {        
                  // Exit
                  System.exit(0);
                }
            }
    }
    }

    public void eraseBackground() {      
        // White background:
    	
        background(bg);
    }

    // mousePressed() is a PApplet method that you will override.
    // This method is called from PApplet one time when the mouse is pressed.
    @Override()
    public void mousePressed() {
        // Draw a circle wherever the mouse is
        int mouseWidth  = 25;
        int mouseHeight = 25;
        fill(0, 255, 0);
        ellipse(this.mouseX, this.mouseY, mouseWidth, mouseHeight);
        for (int i=0; i < 10; i++) {
        // Check whether the click occurred within range of the shape
        if ((this.mouseX >= dList.get(i).x-dList.get(i).targetRange) && (this.mouseX <= dList.get(i).x+dList.get(i).targetRange) && 
         (this.mouseY >= dList.get(i).y-dList.get(i).targetRange) && (this.mouseY <= dList.get(i).y+dList.get(i).targetRange)) {
            
            // Update score:
        
            score = score + dList.get(i).pointValue;
            text("score = " + score,10,10);
            System.out.println("DBG:  HIT!");
            }
        }
    }
    // Create a Disk class that you will use to create one or more disks with each
    // disk having a color, speed, position, etc.
    class Disk {
        // Size of disk
       float shapeWidth  =  80;
       float shapeHeight = 50;
       
        // Point value of disk
        int pointValue;

        // Position of disk - keep track of x and y position of disk
        float x = 100;
        float y = 100;

        // Horizontal speed of disk
        float xSpeed = 2;

        // It's hard to click a precise pixel on a disk, to make it easier we can
        // allow the user to click somewhere on the disk.
        // You might want to make the scoring space be a rectangle fitted tightly
        // to the disk - it's easier than calculating a rounded boundary.
        int targetRange = Math.round((min(shapeWidth+50, shapeHeight+50)/2));


        float red;
        float green;
        float blue;
        

        // The constructor could be extended to accept other disk characteristics
        Disk(float red, float green, float blue, float x, float y, 
                float xSpeed)  {
            this.red   = red;
            this.green = green;
            this.blue  = blue;
            this.pointValue= (int) (2*xSpeed);
            this.xSpeed= xSpeed-10;
            this.x = x;
            this.y = y;
            //this.shapeWidth = random(30, 70);
            //this.shapeHeight = random(30, 70);
            
        }

        Disk() {
            this(0, 0, 255, 300, 250, 2);
            
        }

      

		public void calcCoords() {      
            // Compute the x position where the shape will be drawn
            this.x += this.xSpeed;
        	
            if (x > canvasWidth -10 || (x<=-10)){                //Did the ball hit the side?
                xSpeed=-xSpeed;                                  //If it did reverse the direction
              }
        	
           
        }

        public void drawShape() {
        	
        	int i =0;
 
        	for (i=0; i<maxDisks; i++) {
        	image(ellipse, x, y);
        	
            //ellipse(random(100),random(100),40, 40);
            //ellipse(x,y,shapeWidth,shapeHeight);
            text("score = " + score, 50,30 );
            }
        	
        }
        public void displayPointValue() {
            // Draw the text at computed x, y location
            textSize(15);
            fill(0);
            textAlign(CENTER);
            
            text(pointValue,x+50,y+60);
        }
    }
}
