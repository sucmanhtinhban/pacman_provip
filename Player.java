/* This is the pacman object */

import java.util.HashSet;
import java.util.Set;

class Player extends Mover
{
    /* Direction is used in demoMode, currDirection and desiredDirection are used in non demoMode*/ 
    char direction;
    char currDirection;
    char desiredDirection;

    /* Keeps track of pellets eaten to determine end of game */
    int pelletsEaten;

    /* Last location */
    int lastX;
    int lastY;
    
    /* Current location */
    int x;
    int y;
    
    /* Which pellet the pacman is on top of */
    int pelletX;
    int pelletY;

    /* teleport is true when travelling through the teleport tunnels*/
    boolean teleport;
    
    /* Stopped is set when the pacman is not moving or has been killed */
    boolean stopped = false;


    /* Constructor places pacman in initial location and orientation */
    public Player(int x, int y)
    {
      teleport=false;
      pelletsEaten=0;
      pelletX = x/gridSize-1;
      pelletY = y/gridSize-1;
      this.lastX=x;
      this.lastY=y;
      this.x = x;
      this.y = y;
      currDirection='L';
      desiredDirection='L';
    }


    /* This function is used for demoMode.  It is copied from the Ghost class.  See that for comments */
    public char newDirection()
    { 
        int random;
        char backwards='U';
        int newX=x;
        int newY=y;
        int lookX=x,lookY=y;
        Set<Character> set = new HashSet<Character>();


        switch(direction)
        {
        case 'L':
            backwards='R';
            break;     
        case 'R':
            backwards='L';
            break;     
        case 'U':
            backwards='D';
            break;     
        case 'D':
            backwards='U';
            break;     
        }
        char newDirection = backwards;
        while (newDirection == backwards || !isValidDest(lookX,lookY))
        {
        if (set.size()==3)
        {
            newDirection=backwards;
            break;
        }
        newX=x;
        newY=y;
        lookX=x;
        lookY=y;
        random = (int)(Math.random()*4) + 1;
        if (random == 1)
        {
            newDirection = 'L';
            newX-=increment; 
            lookX-= increment;
        }
        else if (random == 2)
        {
            newDirection = 'R';
            newX+=increment; 
            lookX+= gridSize;
        }
        else if (random == 3)
        {
            newDirection = 'U';
            newY-=increment; 
            lookY-=increment;
        }
        else if (random == 4)
        {
            newDirection = 'D';
            newY+=increment; 
            lookY+=gridSize;
        }
        if (newDirection != backwards)
        {
            set.add(new Character(newDirection));
        }
        } 
        return newDirection;
    }


    /* This function is used for demoMode.  It is copied from the Ghost class.  See that for comments */
  public boolean isChoiceDest()
  {
    if (  x%gridSize==0&& y%gridSize==0 )
    {
      return true;
    }
    return false;
  }

  /* This function is used for demoMode.  It is copied from the Ghost class.  See that for comments */
    public void demoMove()
    {
        lastX=x;
        lastY=y;
        if (isChoiceDest())
        {
        direction = newDirection();
        }
        switch(direction)
        {
        case 'L':
            if ( isValidDest(x-increment,y))
            {
            x -= increment;
            }
            else if (y == 9*gridSize && x < 2 * gridSize)
            {
            x = max - gridSize*1;
            teleport = true; 
            }
            break;     
        case 'R':
            if ( isValidDest(x+gridSize,y))
            {
            x+= increment;
            }
            else if (y == 9*gridSize && x > max - gridSize*2)
            {
            x = 1*gridSize;
            teleport=true;
            }
            break;     
        case 'U':
            if ( isValidDest(x,y-increment))
            y-= increment;
            break;     
        case 'D':
            if ( isValidDest(x,y+gridSize))
            y+= increment;
            break;     
        }
        currDirection = direction;
        frameCount ++;
    }


    
    
}
