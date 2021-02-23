import java.util.*;
import java.io.*;

public class 2048 {
   int highscore;
   Scanner keyboard;
   
   public static void main(String[] args) throws IOException {
      setup();
      System.out.println("Press space + enter to start the game!");
      while(keyboard.nextLine() != " ") {
         
      }
      System.out.println("Game started.");
   }
   
   public void intro() {
      keyboard = new Scanner(System.in);
      Scanner input = new Scanner(new File("highscore.txt"));
      highscore = input.next();
      System.out.println("Welcome to 2048! Current highscore: " + highscore);
      input.close();
   }
}