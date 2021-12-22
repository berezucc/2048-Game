/*
Vladislav A, Nikita B (Partially)
Game.java
Runs the functional aspects of the game itself
*/

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
  public int[][] board;
  public int score, highscore;
  public Random rand;
  public BufferedWriter bw;
  public Scanner scan;

  /*
  * Constructor
  * pre: File in Scanner must exist
  * post: Score, highscore, and board are initialized with 
  * their respective starting values.
  */
  public Game() {
    score = 0;
    scan();
    rand = new Random();

    board = new int[4][4];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        board[row][col] = 0;
      }
    }
    randomGridSpace();
    randomGridSpace();
  }

  /*
  * Scans the highscore save file and saves the contents to a variable.
  * pre: File must exist be able to be scanned.
  * post: Highscore is extracted from the file.
  */
  public void scan() {
    // Catches any file related expecptions
    try {
      scan = new Scanner(new File(".//Files//highscore.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Problem finding file");
      System.err.println("FileNotFoundException: " + e.getMessage());
    }
    
    highscore = scan.nextInt();
  }

  /*
  * Returns the board array.
  * pre: None
  * post: Board array returned.
  */
  public int[][] getBoard() {
    return board;
  }

  /*
  * Clears the board array and resets score.
  * pre: Board array must be initialized.
  * post: Board and score are reset to defaut.
  */
  public void wipe() {
    score = 0;
    for (int[] elem: board) {
      Arrays.fill(elem, 0);
    }
    randomGridSpace();
    randomGridSpace();
    saveHighScore();
  }

  /*
  * Sets the current highscore.
  * pre: Score and highscore are initialized.
  * post: The current highscore value is set.
  */
  public void setHighScore () {
    if (score > highscore) {
      highscore = score;
    }
  }

  /*
  * Writes the current highscore back to the save file.
  * pre: The file exists and is able to be written to.
  * post: Highscore is saved to the file.
  */
  public void saveHighScore () {
    // Catches any file related expecptions
    try {
      bw = new BufferedWriter(new FileWriter(".//Files//highscore.txt", false));
      bw.write(String.valueOf(highscore));
      bw.close();
    } catch (IOException e) {
      System.out.println("Problem opening or writing to file");
      System.err.println("IOException: " + e.getMessage());
    }
  }

  /*
  * Sets an empty board space to the value of 2.
  * pre: Board must have at least one empty space.
  * post: Board space is filled with a 2 value tile.
  */
  public void randomGridSpace() {
    int rRow, rCol;
    boolean empty = false;
    
    // Loops until an empty space is found
    while (!empty) {
      // Generates new random numbers until they correspond with an empty space
      rRow = rand.nextInt(4);
      rCol = rand.nextInt(4);
      // If the condition is met, exits the loop and set the board value
      if (board[rRow][rCol] == 0) {
        empty = true;
        board[rRow][rCol] = 2;
      }
    }
  }

  /*
  * Determines if the user is able to make an UP movement.
  * pre: Board must be initialized.
  * post: Whether or not a legal move exists is returned.
  */
  public boolean legalMoveUp() {
    // Iterates though all except the top row
    for (int row = 1; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        if (board[row][col] != 0) {
          // If the value can be merged above or is below a zero
          if (board[row-1][col] == 0 || board[row-1][col] == board[row][col]) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /*
  * Determines if the user is able to make a DOWN movement.
  * pre: Board must be initialized.
  * post: Whether or not a legal move exists is returned.
  */
  public boolean legalMoveDown() {
    // Iterates though all except the bottom row
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 4; col++) {
        // If the value can be merged down or is above a zero
        if (board[row][col] != 0) {
          if (board[row+1][col] == 0 || board[row+1][col] == board[row][col]) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /*
  * Determines if the user is able to make a RIGHT movement.
  * pre: Board must be initialized.
  * post: Whether or not a legal move exists is returned.
  */
  public boolean legalMoveRight() {
    // Iterates though all except the right column
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        if (board[row][col] != 0) {
          // If the value can be merged right or is left of a zero
          if (board[row][col+1] == 0 || board[row][col+1] == board[row][col]) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /*
  * Determines if the user is able to make a LEFT movement.
  * pre: Board must be initialized.
  * post: Whether or not a legal move exists is returned.
  */
  public boolean legalMoveLeft() {
    // Iterates though all except the left column
    for (int row = 0; row < 4; row++) {
      for (int col = 1; col < 4; col++) {
        if (board[row][col] != 0) {
          // If the value can be merged left or is right of a zero
          if (board[row][col-1] == 0 || board[row][col-1] == board[row][col]) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public void moveUp() {
    if (legalMoveUp()) {
      for (int col = 0; col < 4; col++) {
        for (int row = 0; row < 3; row++) {
          int currentTile = board[row][col];
          int combineVal = 0;
          int rVal = row + 1;
          boolean exit = false;
          
          while (!exit) {
            if (rVal == 4) {
              exit = true;
            } else if (board[rVal][col] != 0) {
              exit = true;
              combineVal = board[rVal][col];
            } else {
              rVal++;
            }
          }
          if (currentTile == 0 && combineVal != 0) {
            board[row][col] = board[rVal][col];
            board[rVal][col] = 0;
            row--;
          } else if (currentTile != 0 && combineVal == currentTile) {
            board[row][col] *= 2;
            score += board[row][col];
            board[rVal][col] = 0;
          }
        }
      }
      randomGridSpace();
    }
  }

  public void moveDown() {
    if (legalMoveDown()) {
      for (int col = 0; col < 4; col++) {
        for (int row = 3; row > 0; row--) {
          int currentTile = board[row][col];
          int combineVal = 0;
          int rVal = row-1;
          boolean exit = false;
          
          while (!exit) {
            if (rVal == -1) {
              exit = true;
            } else if (board[rVal][col] != 0) {
              exit = true;
              combineVal = board[rVal][col];
            } else {
              rVal--;
            }
          }
          if (currentTile == 0 && combineVal != 0) {
            board[row][col] = board[rVal][col];
            board[rVal][col] = 0;
            row++;
          } else if (currentTile != 0 && combineVal == currentTile) {
            board[row][col] *= 2;
            score += board[row][col];
            board[rVal][col] = 0;
          }
        }
      }
      randomGridSpace();
    }
  }

  public void moveRight() {
    if (legalMoveRight()) {
      for (int row = 0; row < 4; row++) {
        for (int col = 3; col > 0; col--) {
          int currentTile = board[row][col];
          int combineVal = 0;
          int cVal = col-1;
          boolean exit = false;
          
          while (!exit) {
            if (cVal == -1) {
              exit = true;
            } else if (board[row][cVal] != 0) {
              exit = true;
              combineVal = board[row][cVal];
            } else {
              cVal--;
            }
          }
          if (currentTile == 0 && combineVal != 0) {
            board[row][col] = board[row][cVal];
            board[row][cVal] = 0;
            col++;
          } else if (currentTile != 0 && combineVal == currentTile) {
            board[row][col] *= 2;
            score += board[row][col];
            board[row][cVal] = 0;
          }
        }
      }
      randomGridSpace();
    }
  }

  public void moveLeft() {
    if (legalMoveLeft()) {
      for (int row = 0; row < 4; row++) {
        for (int col = 0; col < 3; col++) {
          int currentTile = board[row][col];
          int combineVal = 0;
          int cVal = col+1;
          boolean exit = false;
          
          while (!exit) {
            if (cVal == 4) {
              exit = true;
            } else if (board[row][cVal] != 0) {
              exit = true;
              combineVal = board[row][cVal];
            } else {
              cVal++;
            }
          }
          if (currentTile == 0 && combineVal != 0) {
            board[row][col] = board[row][cVal];
            board[row][cVal] = 0;
            col--;
          } else if (currentTile != 0 && combineVal == currentTile) {
            board[row][col] *= 2;
            score += board[row][col];
            board[row][cVal] = 0;
          }
        }
      }
      randomGridSpace();
    }
  }

  /*
  * Returns the current score value.
  * pre: none
  * post: Score value is returned.
  */
  public int scoreValue () {
    return score;
  }

  /*
  * Returns the current highscore value.
  * pre: none
  * post: Highscore value is returned.
  */
  public int highScoreValue () {
    return highscore;
  }

  /*
  * Checks the board for a winning position. If found, saves any
  * unsaved highscores returns true.
  * pre: none
  * post: Any wins are found, and corresponding boolean is returned.
  */
  public boolean winCheck () {
    boolean exit = false;
    // Loops through the board 2d array
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        // If any of the tiles have a value of 2048
        if(board[i][j] == 2048) {
          saveHighScore();
          exit = true;
        }
      }
    }
    return exit;
  }

  /*
  * Checks for a losing position. If found, saves any
  * unsaved highscores and returns true.
  * pre: none
  * postL Any losses are found, and corresponding boolean is returned.
  */
  public boolean loseCheck () {
    // If no legal moves are available, saves any needed scores and returns true
    if (!legalMoveLeft() && !legalMoveDown() && !legalMoveRight() && !legalMoveUp()) {
      saveHighScore();
      return true;
    } else {
      return false;
    }
  }
}