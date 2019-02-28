import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
    public static void main(String[]args){
      String filename = "data1.dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);

        filename = "data2.dat";

        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);

        filename = "data3.dat";

        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
}
