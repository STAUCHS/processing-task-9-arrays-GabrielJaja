import processing.core.PApplet;
import processing.core.PImage;


public class Sketch extends PApplet {

  // Related arrays for (x,y) coordinate of snowflakes
  float[] snowX = new float [42]; // All x values of snowball
  float[] snowY = new float [42]; // All y values of snowball
  boolean[] ballHideStatus = new boolean[42];
  
  float circleX = 400; // How fast the cicle moves along the x-axis
  float circleY = 500; // How fast the circle moves along the y-axiszddadadds
  int intcircleSize = 30; // The size of the circle

  PImage gameOver; // Game over image that appears at the end game
  
  int speed = 2; // Initial speed of the firball
  int snowDiameter = 10; // Size of snowball

  int lives = 3; // The number of lives that the player has

  public void settings() {
    size(800, 600);
  }

  public void setup() {
    background(0);
    // Generate random x and y-values for snowflakes.
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width); // (width) is to scale, when enlarged.
      snowY[i] = random(height); // (height) is to scale, when enlarged.

      gameOver = loadImage("gameover.jpg"); // Game over image 
     }
  }

  public void draw() {
    if (lives == 0) { // After all lives are lost the game over image appears.
      image(gameOver, 100, 100);
    }
    else {
      background(0);
      
      // Calling mathods
      snow();
      circle();
      lives();
    }
 
  }
  // All other defined methods are written below:

  /**
   * @author: Gabriel Jaja
   * @method: snow: This value controls the rate at which the snow is falling and loops it several times.
   */
  public void snow() { // Method to control snowflakes.
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i]) {
        circle(snowX[i], snowY[i], snowDiameter);
        
        snowY[i] += speed;
        if (snowY[i] > height) { // Loop to make snow continously fall again.
          snowY[i] = 0;
        }
    
        if (dist(snowX[i], snowY[i], circleX, circleY) < 25 ) { // If the distance between the circle and the snow is 25, one life is lost.
          snowY[i] = 0;
          lives--;
        }
        
        // To make snow disappear when clicked on.
        if (mousePressed){
          if (dist(snowX[i], snowY[i], mouseX, circleY) < snowDiameter / 2) {
          ballHideStatus[i] = true;
          }
        }
      }
    }
  }

  /**
   * @author: Gabriel Jaja
   * @method: keyReleased: When the arrow keys are released, the speed is now set to 2.
   */
  public void keyReleased(){
    speed = 2;
    }

  /**
   * @author: GabrielJaja
   * @method: keyPressed: When the keyboard is pressed controls the speed of the snowflake.
   */
  public void keyPressed(){
    if (keyCode == 40){
      speed = 4;
    }else if (keyCode == 38){
      speed = 1;
    }
  }

  /**
   * @author: Gabriel Jaja
   * @method: lives: Each time a life is lost one of the squares disappear.
   */
  public void lives() {
    fill(255, 0, 0);
    for (int i = lives; i > 0; i--) {
      square(width - (i * 60), 40, 40);
    }
  }

/**
 * @author: Gabriel Jaja
 * @method: circle: Creates a player and moves it using the keyPressed function.
 */
  public void circle() {
    circle(circleX, circleY, intcircleSize);

    if (keyPressed) {
      if (key == 'w'){
        circleY -= 2;
      }
      if (key == 'a'){
        circleX -= 2;
      }
      if (key == 's'){
        circleY += 2;
      }
      if (key == 'd'){
        circleX += 2;
      }

    }


  }
}