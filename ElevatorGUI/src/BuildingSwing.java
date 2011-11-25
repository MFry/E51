import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Creates a GUI that simulates the elevators in an entire building
 * with a certain number of floors and elevators
 * @author Kim
 *
 */
public class BuildingSwing {

   /** Floors in the building */
   int numFloors;
   /** Elevators in the building */
   int numElevators;
   /** Array of sliders to represent each elevator */
   JSlider[] sliders;
   JPanel[] elevator;
   /** GUI frame */
   JFrame frame;
   /** Control system for the elevator */
   ControlSystem system;

   /**
    * Creates a new GUI for the Building View
    * @param numFloors Number of floors in the building
    * @param numElevators Number of elevators in the building
    * @param system Control system for the elevator
    */
   public BuildingSwing (int floors, int elevators, ControlSystem system) {
      numFloors = floors;
      numElevators = elevators;
      sliders = new JSlider[numElevators];
      elevator = new JPanel[numElevators];
   }

   /**
    * Generates the building view using sliders to represent the elevators
    */
   public void init () {
      System.out.println ("Creating GUI");
      // Create a JFrame with "Elevator Proposal" as the title
      frame = new JFrame ("Building View");
      // Set the frame so that the program stops when the frame is closed
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      System.out.println ("Setting frame size");
      frame.setSize (500, 500);
      
      // Generate the elevators
      for (int i = 0; i < numElevators; i++) {
         JSlider slider = new JSlider (JSlider.VERTICAL, 0, numFloors, 0);
         slider.setMajorTickSpacing (MAJOR_TICK_SPACING);
         slider.setMinorTickSpacing (MINOR_TICK_SPACING);
         slider.setPaintTicks (true);
         slider.setPaintLabels (true);
         slider.setPreferredSize (new Dimension (width, height));
         sliders[i] = slider;
         elevator[i] = new JPanel ();
         elevator[i].add (slider);
         elevator[i].setLayout (new BoxLayout (elevator[i], BoxLayout.Y_AXIS));
         elevator[i].add (new JLabel ("" + sliders[i].getValue ()));

         frame.add (elevator[i]);

      }
      frame.setLayout (new FlowLayout ());
      frame.setVisible (true);
      frame.setBackground (Color.white);
      frame.pack ();
   }

   /**
    * Changes the elevator positions
    * @param rate The number of milliseconds between "frames"
    * @throws InterruptedException 
    */
   public void update (int rate) throws InterruptedException {
      // Get the state of each individual elevator and then update
      while (true) {
         int[] values = system.step ();
         for (int i = 0; i < numElevators; i++) {
            sliders[i].setValue (values[i]);
         }
         Thread.sleep (rate);
      }
   }

   public static void main (String[] args) throws InterruptedException {
      ControlSystem system = new ControlSystem (50);
      BuildingSwing building = new BuildingSwing (50, 10, system);
      building.init ();
      building.update (100);
   }
}
