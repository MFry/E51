import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorSwing extends MouseAdapter implements ActionListener {
   JFrame frame;
   private void createAndShowGUI () {
      System.out.println ("Creating GUI");
      frame = new JFrame ("Elevator Proposal");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      System.out.println ("Set frame size");
      frame.setSize (500, 500);

      Container content = frame.getContentPane ();

      System.out.println ("Creating buttons");
      
      JLabel doorLeft = new JLabel ();
      JLabel doorRight = new JLabel ();
      final JLabel upArrow = new JLabel ();
      final JLabel downArrow = new JLabel ();
      
      
      doorLeft.setIcon (new ImageIcon ("./src/media/door_left.png"));
      doorRight.setIcon (new ImageIcon ("./src/media/door_right.png"));
      upArrow.setIcon (new ImageIcon ("./src/media/inactive_up.png"));
      downArrow.setIcon (new ImageIcon ("./src/media/inactive_down.png"));
      upArrow.addMouseListener(new MouseAdapter() {
         boolean active = false;
         public void mouseEntered (MouseEvent me) {
            upArrow.setIcon (new ImageIcon ("./src/media/activated_up.png"));
         }
         public void mouseExited (MouseEvent me) {
            upArrow.setIcon (new ImageIcon ("./src/media/inactive_up.png"));
         }
         public void mouseClicked(MouseEvent me) {
           
           System.out.println("Going up!");
         }

       });
      upArrow.setCursor (new Cursor (Cursor.HAND_CURSOR));
      downArrow.addMouseListener(new MouseAdapter() {
         public void mouseEntered (MouseEvent me) {
            downArrow.setIcon (new ImageIcon ("./src/media/activated_down.png"));
         }
         public void mouseExited (MouseEvent me) {
            downArrow.setIcon (new ImageIcon ("./src/media/inactive_down.png"));
         }
         public void mouseClicked(MouseEvent me) {
           System.out.println("Going down!");
         }
       });
      downArrow.setCursor (new Cursor (Cursor.HAND_CURSOR));
      
      frame.add (downArrow);
      frame.add (upArrow);
      frame.add (doorLeft);
      frame.add (doorRight);
      
      
      JButton exitButton = new JButton ("Exit");
      exitButton.setMnemonic (KeyEvent.VK_Q);
      exitButton.setToolTipText ("Click this button to quit");
      exitButton.setActionCommand ("quit");
      exitButton.addActionListener (this);
      
      content.setBackground (Color.WHITE);
      content.setLayout (new FlowLayout ());
      content.add (exitButton);
      
      System.out.println ("Displaying frame");
      frame.pack ();
      frame.setVisible (true);
   }

   public void actionPerformed (ActionEvent e) {
      String command = e.getActionCommand ();
      if (command.equals("quit")) {
         System.out.println ("Exiting... have a nice day");
         frame.setVisible (false);
         frame.dispose ();
      }
   }
   
   public static void main (String[] args) {
      new ElevatorSwing ().createAndShowGUI ();
   }
}

@SuppressWarnings("serial")
class MyPanel extends JPanel {

   public MyPanel () {
      setBorder (BorderFactory.createLineBorder (Color.black));
   }

   public Dimension getPreferredSize () {
      return new Dimension (50, 50);
   }

   public void paintComponent (Graphics g) {
      super.paintComponent (g);

      // Draw Text
      //g.drawString ("This is my custom Panel!", 10, 20);
   }
}
