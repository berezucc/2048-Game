/*
Nikita B
Credits Class
Displays the credits/creators of the game.
*/

import javax.swing.*; 
import java.awt.Color;
import java.awt.Font;

public class Credits extends Colors{
  public Credits(Color bg) {
    JFrame frame = new JFrame("Credits");  
    frame.getContentPane().setBackground(bg);

    JLabel title,creator1,creator2,creator3;  
    title=new JLabel("Created By:");
    title.setBounds(50,50, 400,30);
    creator1=new JLabel("Vladislav A.");
    creator1.setBounds(50,100, 400,30);  
    creator2=new JLabel("Nikita B.");  
    creator2.setBounds(50,150, 400,30);  
    creator3=new JLabel("Shawn W.");
    creator3.setBounds(50,200, 400,30);  
    frame.add(title); frame.add(creator1); frame.add(creator2); frame.add(creator3);
    
    title.setFont(new Font("Arial", Font.BOLD, 32));  
    creator1.setFont(new Font("Arial", Font.BOLD, 24));  
    creator2.setFont(new Font("Arial", Font.BOLD, 24));  
    creator3.setFont(new Font("Arial", Font.BOLD, 24));  
    
    frame.setSize(400,500);  
    frame.setLayout(null);
    ImageIcon logo = new ImageIcon("Images/og_image (1).jpg");
    frame.setIconImage(logo.getImage());  
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
  }
}