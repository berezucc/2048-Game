/*
Vladislav A
Instructions Class
Displays the instructions to the game of 2048.
*/

import javax.swing.*; 
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Instructions extends Colors {
  private JTextArea text;
  
  /*
  * Constructor
  * pre: none
  * post: Frame and text area created and formatted.
  */
  public Instructions(Color bg) {
    Settings settings = new Settings();
    
    // Creates a new frame for text display
    JFrame frame = new JFrame("Instructions");
    frame.getContentPane().setBackground(bg);
    ImageIcon logo = new ImageIcon("Images/og_image (1).jpg");
    frame.setIconImage(logo.getImage());
    
    // Creates and formats a text area for instructions
    text = new JTextArea();
    text.setBounds(40,40,320,320);
    readFile();
    text.setEditable(false);
    text.setBackground(CUSTOM_4);
    
    // Formats the frame and adds the text area
    frame.setSize(400,500);  
    frame.setLayout(null); 
    frame.add(text); 
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /*
  * Reads the preset instructions file and displays its
  * contents on the text area.
  * pre: File must contain text.
  * post: Instructions are read from file and displayed
  */
  private void readFile() {
    BufferedReader br;
    String line;

    // Catches any file related exceptions
    try {
      br = new BufferedReader(new FileReader(".//Files//instructions.txt"));
      // Loops though all readable lines
      while ((line = br.readLine()) != null) {
        text.append(line + "\n"); // Appends given lines to text area object
      }
    } catch (IOException e) {
      System.out.println("Problem reading the file");
      System.err.println("IOException: " + e.getMessage());
    }
  }
}