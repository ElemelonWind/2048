import java.util.*;
import java.io.*;

public class twentyfortyeight {
   static int highscore;
   static Scanner keyboard;
   static int[][] board;
   static boolean firstTurn = true;
   static int moves = 0;
   
   public static void main(String[] args) throws IOException {
      intro();
      System.out.println("Press any key + enter to start the game!");
      while(!keyboard.hasNextLine()) {
         
      }
      System.out.println("Game started");
      setup();
      while(!winCheck()) {
         String next = keyboard.nextLine();
         
         if(next.trim().equalsIgnoreCase("W")) {
            System.out.println("Moving up");
            moveUp();
            moves++;
         }
         
         else if(next.trim().equalsIgnoreCase("S")) {
            System.out.println("Moving down");
            moveDown();
            moves++;
         }
         
         else if(next.trim().equalsIgnoreCase("A")) {
            System.out.println("Moving left");
            moveLeft();
            moves++;
         }
         
         else if(next.trim().equalsIgnoreCase("D")) {
            System.out.println("Moving right");
            moveRight();
            moves++;
         }
         
         if(winCheck()) {
            System.out.println("You won! Highscore not counted because you are phenomenal :)");
            return;
         }
         
         if(!firstTurn) {
            System.out.println("Adding square");
            add();
         }
         
         else 
            firstTurn = false;
         
         if(loseCheck()) {
            System.out.println("Oof, you lost. Score: " + score());
            if(score() > highscore) {
               System.out.println("You beat the high score! Good job.");
               PrintWriter outputFile = new PrintWriter(new FileWriter("highscore.txt"));
               outputFile.print("" + score());
               outputFile.close();
            }
            return;
         }
      }
   }
   
   public static void intro() throws IOException {
      keyboard = new Scanner(System.in);
      Scanner input = new Scanner(new File("highscore.txt"));
      highscore = Integer.parseInt(input.next());
      System.out.println("Welcome to 2048! Current highscore: " + highscore);
      System.out.println("Use WASD + enter to move.");
      input.close();
   }
   
   public static void setup() {
      board = new int[4][4];
      for(int r = 0; r < 4; r++) {
         for(int c = 0; c < 4; c++) {
            board[r][c] = 0;
         }
      }
      
      int x = 0;
      while(x < 2) {
         int rando = (int)(Math.random() * 4);
         int rando2 = (int)(Math.random() * 4);
         if(board[rando][rando2] != 2) {
            board[rando][rando2] = 2;
            x++;
         }
      }
      
      print();
   }
   
   public static void print() {
      for(int r = 0; r < 4; r++) {
         for(int c = 0; c < 4; c++) {
            if(board[r][c] == 0) {
               System.out.print("[ ]");
            }
            else {
               System.out.print("[" + board[r][c] + "]");
            }
         }
         
         System.out.println();
      }
   }
   
   public static void moveUp() {
      for(int c = 0; c < 4; c++) {
         for(int r = 1; r < 4; r++) {
            boolean combined = false;
            for(int x = 0; x < r; x++) {
               if(board[r-1-x][c] == 0) {
                  board[r-1-x][c] = board[r-x][c];
                  board[r-x][c] = 0;
               }
               
               else if(board[r-1-x][c] == board[r-x][c] && !combined) {
                  board[r-1-x][c] *= 2;
                  board[r-x][c] = 0;
                  combined = true;
               }
            }
         }
      }
      print();
   }
   
   public static void moveDown() {
      for(int c = 0; c < 4; c++) {
         for(int r = 2; r >= 0; r--) {
            boolean combined = false;
            for(int x = 0; x < 3-r; x++) {
               if(board[r+1+x][c] == 0) {
                  board[r+1+x][c] = board[r+x][c];
                  board[r+x][c] = 0;
               }
               
               else if(board[r+1+x][c] == board[r+x][c] && !combined) {
                  board[r+1+x][c] *= 2;
                  board[r+x][c] = 0;
                  combined = true;
               }
            }
         }
      }
      print();
   }
   
   public static void moveRight() {
      for(int r = 0; r < 4; r++) {
         for(int c = 2; c >= 0; c--) {
            boolean combined = false;
            for(int x = 0; x < 3-c; x++) {
               if(board[r][c+1+x] == 0) {
                  board[r][c+1+x] = board[r][c+x];
                  board[r][c+x] = 0;
               }
               
               else if(board[r][c+1+x] == board[r][c+x] && !combined) {
                  board[r][c+1+x] *= 2;
                  board[r][c+x] = 0;
                  combined = true;
               }
            }
         }
      }
      print();
   }
   
   public static void moveLeft() {
      for(int r = 0; r < 4; r++) {
         for(int c = 1; c < 4; c++) {
            boolean combined = false;
            for(int x = 0; x < c; x++) {
               if(board[r][c-1-x] == 0) {
                  board[r][c-1-x] = board[r][c-x];
                  board[r][c-x] = 0;
               }
               
               else if(board[r][c-1-x] == board[r][c-x] && !combined) {
                  board[r][c-1-x] *= 2;
                  board[r][c-x] = 0;
                  combined = true;
               }
            }
         }
      }
      print();
   }
   
   public static void add() {
      int rando = (int)(Math.random() * 4);
      int rando2 = (int)(Math.random() * 4);
      while(board[rando][rando2] != 0) {
         rando = (int)(Math.random() * 4);
         rando2 = (int)(Math.random() * 4);
      }
      
      double chance = moves * .002;
      double rando3 = Math.random();
      if(chance > rando3)
         board[rando][rando2] = 4;
      else 
         board[rando][rando2] = 2;
      print();
   }
   
   public static boolean loseCheck() {
      for(int r = 0; r < 4; r++) {
         for(int c = 0; c < 4; c++) {
            if(board[r][c] == 0) 
               return false;
         }
      }
      return true;
   }
   
   public static boolean winCheck() {
      for(int r = 0; r < 4; r++) {
         for(int c = 0; c < 4; c++) {
            if(board[r][c] == 2024) 
               return true;
         }
      }
      return false;
   }
   
   public static int score() {
      int score = 0;
      for(int r = 0; r < 4; r++) {
         for(int c = 0; c < 4; c++) {
            score += board[r][c];
         }
      }
      return score;
   }
}