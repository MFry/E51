import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Contains the slider and label that describes an elevator
 * @author Kim
 *
 */
@SuppressWarnings("serial")
public class ElevatorSlider extends JPanel {
   /** Number of floors after which the sliders need to be resized */
   private static final int FLOOR_LIMIT = 60;

   /** Spacing for the minor ticks on the sliders */
   private static final int MINOR_TICK_SPACING = 1;

   /** Spacing for the major ticks on the sliders */
   private static final int MAJOR_TICK_SPACING = 10;

   /** Height of the slider for each floor */
   private static final int HEIGHT_PER_FLOOR = 10;

   /** Width that each slider takes up */
   private static final int SLIDER_WIDTH = 100;

   /** Slider to represent the elevator's position */
   JSlider slider;
   /** String that contains the info about the elevator */
   String labelStr = "";
   /** Label containing the string with the info about the elevator */
   JLabel label = new JLabel (labelStr);
   /** Elevator that this object describes */
   Elevator elevator;
   int currFloor;
   int maxCap;
   int numFloors;
   int currCap;
   int state;

   /**
    * Creates a new elevator slider with info
    * @param e Elevator to create the slider for
    */
   public ElevatorSlider (Elevator e) {
      elevator = e;
      // Create the slider and adjust its properties
      slider = new JSlider (JSlider.VERTICAL, 0, 100, 0);
      slider.setMajorTickSpacing (MAJOR_TICK_SPACING);
      slider.setMinorTickSpacing (MINOR_TICK_SPACING);
      slider.setPaintTicks (true);
      slider.setPaintLabels (true);
      update (e);
      // Calculate the dimensions for the sliders
      int width = SLIDER_WIDTH;
      int height = HEIGHT_PER_FLOOR * numFloors;
      int heightResize = 1 + (numFloors / FLOOR_LIMIT);
      height /= heightResize;
      slider.setPreferredSize (new Dimension (width, height));
      // Add the slider
      this.add (slider);
      // Add the label to the panel
      this.add (label);
      update (elevator);
      // Set the layout
      this.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
   }

   /**
    * Sets the value of the slider and updates the 
    * label with the proper floors
    * @param floor Floor it is now on
    * @param c Current carrying capacity
    */
   public void update (Elevator e) {
      elevator = e;
      // Update the numerical information
      getInfo ();
      // Create a label for its current condition
      buildString ("1", currCap, maxCap, currFloor, state);
      label = new JLabel (labelStr);
      label.repaint ();
      slider.setValue (currFloor);
   }

   public void getInfo () {
      // Get the numerical information from the elevator
      int[] info = elevator.getInfo ();
      currFloor = info[0];
      maxCap = info[2];
      numFloors = info[3];
      currCap = info[5];
      state = info[6];
   }

   /**
    * Builds the string for the label under the slider
    * @param name Elevator name/ID
    * @param currCap Current carrying capacity
    * @param maxCap Maximum carrying capacity
    * @param currFloor Current floor
    * @param dir Current direction
    */
   public void buildString (String name, int currCap, int maxCap,
   int currFloor, int dir) {
      labelStr = "#" + name;
      // Identify the elevator's current state
      char state = 'E'; // E is for error
      if (dir == -1) {
         state = '-';
      } else if (dir == 0) {
         state = '=';
      } else if (dir == 1) {
         state = '+';
      }
      // Add the state to the ID
      labelStr += state + "\n";
      labelStr += "" + currCap + "/" + maxCap + "\n";
      labelStr += "Floor " + currFloor + "\n";
      label = new JLabel (labelStr);
   }
}
