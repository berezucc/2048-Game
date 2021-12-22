/*
Vladislav A, Nikita B
Board Class
Generates the game interface and board
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Board extends Colors implements KeyListener, ActionListener {
  private JFrame frame;
  private JLabel scoreNum, highScoreNum;
  private JButton button;
  private JLabel[][] grid;
  private ImageIcon i2, i4, i8, i16, i32, i64, i128, i256, i512, i1024, i2048, i4096, i8192, i16384, i32768, i65536, i131072;
  private Game game;
  private HardGame hardGame;
  
  /*
  * Constructor
  * pre: none
  * post: Board object created, game frame is displayed in
  * default state with all frame objects initialized.
  */
  public Board(int gameMode, Color bg) {
    frame = new JFrame("2048");
    button = new JButton("Restart");
    grid = new JLabel[4][4];
    game = null;

    // Defines the "game" variable as either the standard or hard version based on user choice
    if (gameMode == 0) {
      game = new Game();

      // 2048 Title
      JLabel title = new JLabel("2048");
      title.setBounds(50, 10, 200, 100);
      title.setFont(new Font("Arial", Font.BOLD, 40));
      title.setBackground(CUSTOM_4);
      frame.add(title);

      // High Score display panel
      highScoreNum = new JLabel(String.valueOf(game.highScoreValue()), SwingConstants.CENTER);
      highScoreNum.setBounds(260, 50, 90, 40);
      setLabel(highScoreNum, CUSTOM_2, CUSTOM_4);
      frame.add(highScoreNum);

    } else if (gameMode == 1) {
      game = new HardGame();

      // 2048 Title
      JLabel title = new JLabel("Hard ");
      JLabel title2 = new JLabel ("2048 ");
      title.setBounds(50, 0, 200, 100);
      title2.setBounds(50, 30, 200, 100);
      title.setFont(new Font("Arial", Font.BOLD, 35));
      title2.setFont(new Font("Arial", Font.BOLD, 35));
      title.setBackground(CUSTOM_4);
      frame.add(title); frame.add(title2);


      // High Score display panel for extended mode
      highScoreNum = new JLabel(String.valueOf(game.highScoreValue()), SwingConstants.CENTER);
      highScoreNum.setBounds(260, 50, 90, 40);
      setLabel(highScoreNum, CUSTOM_2, CUSTOM_4);
      frame.add(highScoreNum);
    }

    // 2048 Icon on the top left of the frame 
    ImageIcon logo = new ImageIcon("Images/og_image (1).jpg");
    frame.setIconImage(logo.getImage());
    
    // Number icons for normal mode
    i2 = new ImageIcon(Main.class.getResource("Images/2 (1).jpg"));
    i4 = new ImageIcon(Main.class.getResource("Images/4 (1).jpg"));
    i8 = new ImageIcon(Main.class.getResource("Images/8 (1).jpg"));
    i16 = new ImageIcon(Main.class.getResource("Images/16 (1).jpg"));
    i32 = new ImageIcon(Main.class.getResource("Images/32 (1).jpg"));
    i64 = new ImageIcon(Main.class.getResource("Images/64 (1).jpg"));
    i128 = new ImageIcon(Main.class.getResource("Images/128 (1).jpg"));
    i256 = new ImageIcon(Main.class.getResource("Images/256 (1).jpg"));
    i512 = new ImageIcon(Main.class.getResource("Images/512 (1).jpg"));
    i1024 = new ImageIcon(Main.class.getResource("Images/1024 (1).jpg"));
    i2048 = new ImageIcon(Main.class.getResource("Images/2048 (1).jpg"));

    // Number icons for extended mode
    i4096 = new ImageIcon(Main.class.getResource("Images/4096 (1).jpg"));
    i8192 = new ImageIcon(Main.class.getResource("Images/8192 (1).jpg"));
    i16384 = new ImageIcon(Main.class.getResource("Images/16384 (1).jpg"));
    i32768 = new ImageIcon(Main.class.getResource("Images/32768 (1).jpg"));
    i65536 = new ImageIcon(Main.class.getResource("Images/65536 (1).jpg"));
    i131072 = new ImageIcon(Main.class.getResource("Images/131072 (1).jpg"));

    // Application frame
    frame.addKeyListener(this);
    frame.setFocusable(true);
    frame.requestFocus();
    frame.setSize(400, 500);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.getContentPane().setBackground(bg);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Restart button
    button.setBounds(50, 100, 100, 40);
    button.addActionListener(this);
    button.setBackground(CUSTOM_3);
    button.setOpaque(true);
    button.setForeground(CUSTOM_4);
    frame.add(button);
    button.setVisible(true);

    // Board and board outline
    initBoard();
    JLabel outline = new JLabel("");
    outline.setBounds(49, 149, 302, 302);
    outline.setBorder(BorderFactory.createLineBorder(CUSTOM_3, 1));
    outline.setOpaque(true);
    outline.setBackground(CUSTOM_2);
    frame.add(outline);

    // Score Label
    JLabel score = new JLabel("Score ", SwingConstants.CENTER);
    score.setBounds(170, 100, 90, 40);
    setLabel(score, CUSTOM_3, CUSTOM_4);
    frame.add(score);

    // Score display panel
    scoreNum = new JLabel("0", SwingConstants.CENTER);
    scoreNum.setBounds(260, 100, 90, 40);
    setLabel(scoreNum, CUSTOM_2, CUSTOM_4);
    frame.add(scoreNum);

    // High Score Label
    JLabel highScore = new JLabel("Best ", SwingConstants.CENTER);
    highScore.setBounds(170, 50, 90, 40);
    setLabel(highScore, CUSTOM_3, CUSTOM_4);
    frame.add(highScore);

    // High Score display panel
    highScoreNum = new JLabel(String.valueOf(game.highScoreValue()), SwingConstants.CENTER);
    highScoreNum.setBounds(260, 50, 90, 40);
    setLabel(highScoreNum, CUSTOM_2, CUSTOM_4);
    frame.add(highScoreNum);

    start();
  }

  /*
  * Displays the game board in its default state.
  * pre: none
  * post: Formatted game board is displayed in the frame.
  */
  private void initBoard() {
    Border board = BorderFactory.createLineBorder(CUSTOM_3, 1);
    int y = 150;
    for (int row = 0; row < 4; row++) {
      int x = 50;
      for (int col = 0; col < 4; col++) {
        grid[row][col] = new JLabel("");
        grid[row][col].setBounds(x, y, 75, 75);
        frame.add(grid[row][col]);
        grid[row][col].setBorder(board);
        x += 75;
      }
      y += 75;
    }
  }

  /*
  * Formats given label with given color objects.
  * pre: JLabel and Color objects already exist.
  * post: Given label is formatted accordingly.
  */
  private void setLabel(JLabel label, Color back, Color fore) {
    label.setBackground(back);
    label.setOpaque(true);
    label.setForeground(fore);
  }

  /*
  * Controls user inputted key movements.
  * pre: none
  * post: Dictates the appropriate movement to the Game object,
  * and corresponds that with a board drawing call.
  */
  @Override
  public void keyPressed(KeyEvent ke) {
    if (ke.getKeyCode() == KeyEvent.VK_UP) {
      game.moveUp();
      start();
    } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
      game.moveDown();
      start();
    } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
      game.moveRight();
      start();
    } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
      game.moveLeft();
      start();
    }
  }

  @Override
  public void keyTyped(KeyEvent ke) {}

  @Override
  public void keyReleased(KeyEvent ke) {}

  /*
  * Controls restart button actions.
  * pre: none
  * post: Resets the board both in the Game object and
  * visually for the user.
  */
  @Override
  public void actionPerformed(ActionEvent e) {
    game.wipe();
    start();
    frame.requestFocus();
  }

  /*
  * Draws board and determines displays score and highscore variables to
  * the user (used after each move that alters the board)
  * pre: Game board is initialized, and the highscore file in use contains
  * a numerical value that can be displayed.
  * post: Board is drawn and score variables are properly displayed.
  */
  private void start() {
    
    // Iterates through board elements and displays their corresponding values
    // to the user. Each case represents a unique type of tile to be displayed.
    for (int row = 0; row < 4; row ++) {
      for (int col = 0; col < 4; col++) {
        switch (game.getBoard()[row][col]) {
          case 0:
            grid[row][col].setIcon(null); break;
          case 2:
            grid[row][col].setIcon(i2); break;
          case 4:
            grid[row][col].setIcon(i4); break;
          case 8:
            grid[row][col].setIcon(i8); break;
          case 16:
            grid[row][col].setIcon(i16); break;
          case 32:
            grid[row][col].setIcon(i32); break;
          case 64:
            grid[row][col].setIcon(i64); break;
          case 128:
            grid[row][col].setIcon(i128); break;
          case 256:
            grid[row][col].setIcon(i256); break;
          case 512:
            grid[row][col].setIcon(i512); break;
          case 1024:
            grid[row][col].setIcon(i1024); break;
          case 2048:
            grid[row][col].setIcon(i2048); break;
          case 4096:
            grid[row][col].setIcon(i4096); break;
          case 8192:
            grid[row][col].setIcon(i8192); break;
          case 16384:
            grid[row][col].setIcon(i16384); break;
          case 32768:
            grid[row][col].setIcon(i32768); break;
          case 65536:
            grid[row][col].setIcon(i65536); break;
          case 131072:
            grid[row][col].setIcon(i131072); break;
        }
      }
    }
    game.setHighScore();
    scoreNum.setText(game.scoreValue() + ""); // Displays the current score
    highScoreNum.setText(String.valueOf(game.highScoreValue())); //Displays the current highscore
    winChecker();
  }

  public void winChecker() {
    if (game.winCheck()) {
      JLabel lose = new JLabel ("You Won!");
      lose.setFont(new Font("Arial", Font.BOLD, 40));
      lose.setBounds(75, 175, 200, 100);
      lose.setBackground(CUSTOM_3);
      lose.setVisible(true);

      JButton exitButton = new JButton("Back To Menu"); 
      exitButton.setBounds(49, 149, 302, 302);
      exitButton.setBackground(CUSTOM_5);
      frame.add(exitButton); frame.add(lose);
      exitButton.setVisible(true);

      exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          new Menu();
          frame.requestFocus();
        }
      });
    } else if (game.loseCheck()) {
      JLabel lose = new JLabel ("You Lost!");
      lose.setFont(new Font("Arial", Font.BOLD, 40));
      lose.setBounds(75, 175, 200, 100);
      lose.setBackground(CUSTOM_3);
      lose.setVisible(true);

      JButton exitButton = new JButton("Back To Menu"); 
      exitButton.setBounds(49, 149, 302, 302);
      exitButton.setBackground(CUSTOM_5);
      frame.add(exitButton); frame.add(lose);
      exitButton.setVisible(true);

      exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          new Menu();
          frame.requestFocus();
        }
      });
    }
  }
}
