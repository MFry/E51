import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorSwing extends MouseAdapter implements ActionListener {
   JFrame frame; // The entire frame
   JLabel doorLeft = new JLabel (); // Label containing the left door
   JLabel doorRight = new JLabel (); // Label containing the right door
   final JLabel upArrow = new JLabel (); // Label containing the up arrow
   final JLabel downArrow = new JLabel (); // Label containing the down arrow
   JButton exitButton; // The exit button
   Color bgColor = Color.gray; // Background color for the frame

   /**
    * Creates the GUI for this application
    */
   private void init () {
      System.out.println ("Creating GUI");
      // Create a JFrame with "Elevator Proposal" as the title
      frame = new JFrame ("Elevator Proposal");
      // Set the frame so that the program stops when the frame is closed
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      System.out.println ("Setting frame size");
      frame.setSize (500, 500);

      // Create a container for the whole frame
      Container content = frame.getContentPane ();

      System.out.println ("Creating buttons");
      initPanel ();
      System.out.println ("Creating doors");
      initDoors ();
      System.out.println ("Creating exit button");
      initExit ();

      // Create a Panel to hold both doors (for layout purposes)
      JPanel doors = new JPanel ();
      doors.setLayout (new BoxLayout (doors, BoxLayout.X_AXIS));
      doors.add (doorLeft);
      doors.add (doorRight);

      // Create a Panel to hold both arrows (for layout purposes)
      JPanel arrows = new JPanel ();
      arrows.setLayout (new BoxLayout (arrows, BoxLayout.Y_AXIS));
      arrows.add (upArrow);
      arrows.add (downArrow);
      arrows.setBackground (bgColor);

      // Add these panels to the frame
      content.add (arrows);
      content.add (doors);
      content.add (exitButton);

      // Adjust the background color and layout of the frame
      content.setBackground (bgColor);
      content.setLayout (new FlowLayout ());

      System.out.println ("Displaying frame");

      // Shrink the window to fit the contents
      frame.pack ();

      frame.setVisible (true);
   }

   /**
    * Initializes the arrow panels
    */
   private void initPanel () {
      // Create Icons for up and down arrows
      upArrow.setIcon (new ImageIcon ("./media/inactive_up.png"));
      downArrow.setIcon (new ImageIcon ("./media/inactive_down.png"));
      // Define the mouse listener for the up arrow
      upArrow.addMouseListener (new MouseAdapter () {
         public void mouseEntered (MouseEvent me) {
            // Activate light when hovering
            upArrow.setIcon (new ImageIcon ("./media/activated_up.png"));
         }

         public void mouseExited (MouseEvent me) {
            // Deactivate light when not hovering
            upArrow.setIcon (new ImageIcon ("./media/inactive_up.png"));
         }

         public void mouseClicked (MouseEvent me) {
            // Display text
            System.out.println ("Going up!");
         }

      });
      // Make the mouse cursor change to a hand when hovering over the up arrow
      upArrow.setCursor (new Cursor (Cursor.HAND_CURSOR));
      // Define the mouse listener for the down arrow
      downArrow.addMouseListener (new MouseAdapter () {
         public void mouseEntered (MouseEvent me) {
            // Activate light when hovering
            downArrow.setIcon (new ImageIcon ("./media/activated_down.png"));
         }

         public void mouseExited (MouseEvent me) {
            // Deactivate light when not hovering
            downArrow.setIcon (new ImageIcon ("./media/inactive_down.png"));
         }

         public void mouseClicked (MouseEvent me) {
            // Display text
            System.out.println ("Going down!");

         }
      });
      // Make the mouse cursor change to a hand when hovering over the down arrow
      downArrow.setCursor (new Cursor (Cursor.HAND_CURSOR));
   }

   /**
    * Initializes the doors
    */
   private void initDoors () {
      doorLeft.setIcon (new ImageIcon ("./media/door_left.png"));
      doorRight.setIcon (new ImageIcon ("./media/door_right.png"));
   }

   /**
    * Initializes the exit button
    */
   private void initExit () {
      exitButton = new JButton ("Exit");
      exitButton.setMnemonic (KeyEvent.VK_Q);
      exitButton.setToolTipText ("Click this button to quit");
      exitButton.setActionCommand ("quit");
      exitButton.addActionListener (this);
   }

   public void actionPerformed (ActionEvent e) {
      String command = e.getActionCommand ();
      if (command.equals ("quit")) {
         System.out.println ("Exiting... have a nice day");
         frame.setVisible (false);
         frame.dispose ();
      }
   }

   /**
    * Starts the program
    * @param args Command line args (not used)
    */
   public static void main (String[] args) {
      new ElevatorSwing ().init ();
   }
}