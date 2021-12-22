/*
Nikita B
Settings Class
User is able to decide whether they want to play the game in a dark or light theme
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings extends Colors {
    public Color background; 
    
    public void Display(){
        JFrame frame = new JFrame("Settings");
        frame.setFocusable(true);
        frame.getContentPane().setBackground(CUSTOM_1);
        frame.requestFocus();

        ImageIcon logo = new ImageIcon("Images/og_image (1).jpg");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(background);
        JLabel title = new JLabel("Settings");
        title.setBounds(89, 25, 400, 100);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        frame.add(title);

        JButton bLight = new JButton("Light Mode"); 
        bLight.setBounds(105,150,200,40); 
        setButton(bLight);
        frame.add(bLight);  

        JButton bDark = new JButton("Dark Mode"); 
        bDark.setBounds(105,200,200,40);
        setButton(bDark);
        frame.add(bDark); 

        JButton bExit = new JButton("Back To Menu"); 
        bExit.setBounds(105,250,200,40);
        setButton(bExit);
        frame.add(bExit); 

        frame.setSize(400,500);
        frame.setLayout(null); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bLight.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              background = CUSTOM_1;
              frame.getContentPane().setBackground(background);
          }
        });

        bDark.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              background = CUSTOM_6;
              frame.getContentPane().setBackground(background);
          }
        });

        bExit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new Menu();
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