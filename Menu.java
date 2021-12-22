/*
Nikita B, Vladislav A (Partially)
Menu Class
Generates the menu with the button to play the game, instructions, and credits.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends Colors {
  private JFrame frame;

  public Menu() {
    Settings settings = new Settings();
    frame = new JFrame("2048");
    frame.getContentPane().setBackground(CUSTOM_1);
    frame.setFocusable(true);
    frame.requestFocus();

    ImageIcon logo = new ImageIcon("Images/og_image (1).jpg");
    frame.setIconImage(logo.getImage());

    JLabel title = new JLabel("2048");
    title.setBounds(138, 10, 200, 100);
    title.setFont(new Font("Arial", Font.BOLD, 48));
    title.setBackground(Color.WHITE);
    frame.add(title);

    JLabel image = new JLabel(logo);
    image.setBounds(10,10, 75, 75);
    frame.add(image);
          
    JButton bNormal = new JButton("Classic Game"); 
    bNormal.setBounds(105,100,200,40); 
    setButton(bNormal);
    frame.add(bNormal);  

    JButton bHard = new JButton("Hard Game"); 
    bHard.setBounds(105,150,200,40);
    setButton(bHard);
    frame.add(bHard); 

    JButton bRules = new JButton("Instructions"); 
    bRules.setBounds(105,200,200,40);
    setButton(bRules);
    frame.add(bRules); 

    JButton bCreds = new JButton("Credits");
    bCreds.setBounds(105,250,200,40);  
    setButton(bCreds);
    frame.add(bCreds); 

    ImageIcon iCogg = new ImageIcon("Images/cogg (1).png");
    JButton bSettings = new JButton(iCogg);
    bSettings.setBounds(305,10,75,75);  
    bSettings.setOpaque(false);
    bSettings.setContentAreaFilled(false);
    bSettings.setBorderPainted(false);
    frame.getContentPane().add(bSettings);
            
    frame.setSize(400,500);
    frame.setLayout(null); 
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

    bNormal.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Board(0, settings.background);
        frame.requestFocus();
      }
    });

    bHard.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Board(1, settings.background);
        frame.requestFocus();
      }
    });

    bRules.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Instructions(settings.background);
        frame.requestFocus();
      }
    });

    bCreds.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Credits(settings.background);
        frame.requestFocus();
      }
    });
    bSettings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        settings.Display();
        frame.requestFocus();
      }
    });
  }

  /*
  * Formats buttons.
  * pre: button must be inilitalized
  * post: Given button is formatted with color.
  */
  private void setButton (JButton b) {
    b.setBackground(CUSTOM_3);
    b.setOpaque(true);
    b.setForeground(CUSTOM_4);
  }

}