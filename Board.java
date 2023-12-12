import java.awt.*;
import javax.imageio.*;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.*;
import java.io.*;

/* Both Player and Ghost inherit Mover.  Has generic functions relevant to both*/
class Mover {
    int frameCount=0; /* Framecount is used to count animation frames*/
    boolean[][] state;  /* State contains the game map */

    /* gridSize is the size of one square in the game.
     max is the height/width of the game.
     increment is the speed at which the object moves,
     1 increment per move() call */

    int gridSize; 
    int max;
    int increment;

    //Mover() Constructer
    public Mover() {
        gridSize=20;
        increment = 4;
        max = 400;
        state = new boolean[20][20];
        for(int i =0;i<20;i++) {
            for(int j=0;j<20;j++) {
                state[i][j] = false;
            }
        }
    }
      /* Updates the state information */
    public void updateState(boolean[][] state) {
        for(int i =0;i<20;i++) {
            for(int j=0;j<20;j++) {
                this.state[i][j] = state[i][j];
            }
        }
    }


}