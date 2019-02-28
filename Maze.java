import java.util.*;
import java.io.*;
public class Maze{
    private char[][] maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
      animate = false;
      File text = new File(filename);
      Scanner inf = new Scanner(text);

      int rows = 0;
      int cols = 0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          rows++;
          cols = line.length();
      }

      inf = new Scanner(text);
      maze = new char[rows][cols];
      int r = 0;
      while(inf.hasNextLine()){
        String line = inf.nextLine();
        for (int c = 0 ; c < line.length() ; c++){
          maze[r][c] = line.charAt(c);
        }
        r++;
      }

    }

    public String toString(){
      String ans = "";
      for (int r = 0 ; r < maze.length ; r++){
        for (int c = 0 ; c < maze[r].length ; c++){
          ans += maze[r][c];
        }
        ans += "\n";
      }
      return ans;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
            int[] s = new int[2];
            //find the location of the S.
            for (int r = 0 ; r < maze.length ; r++){
              for (int c = 0 ; c < maze[r].length ; c++){
                if (maze[r][c] == 'S'){
                  s[0] = r;
                  s[1] = c;
                }
              }
            }
            //erase the S
            maze[s[0]][s[1]] = ' ';
            //and start solving at the location of the s.
            //return solve(???,???);
            return solve(s[0],s[1],0);

    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int count){ //you can add more parameters since this is private

        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(100);
        }

        //COMPLETE SOLVE

        //place foot down
        if (maze[row][col] == ' '){
          maze[row][col] = '@';
        }

          //move in 4 directions and end if at end
          row--;
          if (maze[row][col] == ' '){
            return solve(row,col,count+1);
          }
          else if (maze[row][col] == 'E'){
            return count + 1;
          }
          row++;

          col++;
          if (maze[row][col] == ' '){
            return solve(row,col,count+1);
          }
          else if (maze[row][col] == 'E'){
            return count + 1;
          }
          col--;

          row++;
          if (maze[row][col] == ' '){
            return solve(row,col,count+1);
          }
          else if (maze[row][col] == 'E'){
            return count + 1;
          }
          row--;

          col--;
          if (maze[row][col] == ' '){
            return solve(row,col,count+1);
          }
          else if (maze[row][col] == 'E'){
            return count + 1;
          }
          col++;

          //can't move so and not solved backtrack, replace @
          if (maze[row][col] == '@'){
            maze[row][col] = '.';

            //backtrack in 4 directions
            row--;
            if (maze[row][col] == '@'){
              return solve(row,col,count-1);
            }
            row++;

            col++;
            if (maze[row][col] == '@'){
              return solve(row,col,count-1);
            }
            col--;

            row++;
            if (maze[row][col] == '@'){
              return solve(row,col,count-1);
            }
            row--;

            col--;
            if (maze[row][col] == '@'){
              return solve(row,col,count-1);
            }
            col++;
        }

        return -1; //so it compiles
    }
}
