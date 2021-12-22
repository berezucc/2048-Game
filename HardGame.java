/* 
Nikita B
HardGame.java
Runs the functional aspects of the extended game.
*/

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class HardGame extends Game {
  private int highscoreHard = 0;
  public HardGame() {
    super();
    scan();
  }

  @Override
  public void scan() {
    try {
      scan = new Scanner(new File(".//Files//highscore(hard).txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Problem finding file");
      System.err.println("FileNotFoundException: " + e.getMessage());
    }
    
    highscoreHard = scan.nextInt();
  }

  @Override
  public void setHighScore() {
    if (score > highscoreHard) {
      highscoreHard = score;
    }
  }

  @Override
  public void saveHighScore() {
    try {
      bw = new BufferedWriter(new FileWriter(".//Files//highscore(hard).txt", false));
      bw.write(String.valueOf(highscoreHard));
      bw.close();
    } catch (IOException e) {
      System.out.println("Problem writing to file");
      System.err.println("IOException: " + e.getMessage());
    }
  }

  @Override
  public boolean winCheck() {
    boolean exit = false;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (board[i][j] == 131072) {
          saveHighScore();
          exit = true;
        }
      }
    }
    return exit;
  }
  
  @Override
  public boolean loseCheck () {
    if (!legalMoveLeft() && !legalMoveDown() && !legalMoveRight() && !legalMoveUp()) {
      saveHighScore();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int highScoreValue () {
    return highscoreHard;
  } 
}