import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class BuildingSwing {
   /** Number of floors after which the sliders need to be resized */
   private static final int FLOOR_LIMIT = 60;
   /** Spacing for the minor ticks on the sliders */
   private static final int MINOR_TICK_SPACING = 1;
   /** Spacing for the major ticks on the sliders */
   private static final int MAJOR_TICK_SPACING = 10;
   /** Height of the slider for each floor */
   private static final int HEIGHT_PER_FLOOR = 10;
   /** Width that each slider takes up */
   private static final int SLIDER_WIDTH = 50;
   /** Floors in the building */
   int floors;
   /** Elevators in the building */
   int elevators;
   /** Array of sliders to represent each elevator */
   JSlider[] sliders;
   /** GUI frame */
   JFrame frame;

   /**
    * Creates a new GUI for the Building View
    * @param numFloors Number of floors in the building
    * @param numElevators Number of elevators in the building
    */
   public BuildingSwing (int numFloors, int numElevators) {
      floors = numFloors;
      elevators = numElevators;
      sliders = new JSlider[elevators];
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
      // Calculate the dimensions for the sliders
      int width = SLIDER_WIDTH;
      int height = HEIGHT_PER_FLOOR * floors;
      int heightResize = 1 + (floors / FLOOR_LIMIT);
      height /= heightResize;
      // Generate the elevators
      for (int i = 0; i < elevators; i++) {
         JSlider slider = new JSlider (JSlider.VERTICAL, 0, floors, 0);
         slider.setMajorTickSpacing (MAJOR_TICK_SPACING);
         slider.setMinorTickSpacing (MINOR_TICK_SPACING);
         slider.setPaintTicks (true);
         slider.setPaintLabels (true);
         slider.setPreferredSize (new Dimension (width, height));
         sliders[i] = slider;
         frame.add (slider);
      }
      frame.setLayout (new FlowLayout ());
      frame.setVisible (true);
      frame.pack ();
   }

   /**
    * Changes the elevator positions
    * @param rate The number of milliseconds between "frames"
    * @throws InterruptedException 
    */
   public void update (int rate) throws InterruptedException {
      // Get the state of each individual elevator and then update
      for (int j = 0; j < elevators; j++) {
         sliders[j].setValue ((int) (Math.random () * floors));
      }
      while (true){
         for (int j = 0; j < elevators; j++) {
            sliders[j].setValue (sliders[j].getValue () - 1);
            if (sliders[j].getValue () == 0 || sliders[j].getValue () == floors) {
               sliders[j].setValue ((int) (Math.random () * floors));
            }
         }
         Thread.sleep (rate);
      }
   }

   public static void main (String[] args) throws InterruptedException {
      BuildingSwing building = new BuildingSwing (25, 20);
      building.init ();
      building.update (100);
   }
}
