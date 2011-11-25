import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MovingDoor extends JComponent {
   /** List of frames */
   private Image[] frameList;

   /** Milliseconds per frame*/
   private long msPerFrame = 100;

   /** Current frame */
   private volatile int currFrame;

   private Thread internalThread;

   private boolean isLeft;

   private volatile boolean noStopRequested;

   /**
    * 
    */
   public MovingDoor (boolean setToLeft) {
      isLeft = setToLeft;
      setPreferredSize (new Dimension (138, 286));
      // Create frame list
      frameList = buildImages (isLeft);
      currFrame = 0;

      // Create a runnable object for thread
      noStopRequested = true;
      Runnable r = new Runnable () {
         public void run () {
            try {
               runWork ();
            } catch (Exception x) {
               // in case ANY exception slips through
               x.printStackTrace ();
            }
         }
      };

      internalThread = new Thread (r);
      internalThread.start ();
   }

   /**
    * Creates the images for the frames
    * @return
    */
   private Image[] buildImages (boolean isLeft) {
      BufferedImage[] im = new BufferedImage[12];
      for (int i = 1; i <= 12; i++) {
         try {
            String type = (isLeft) ? "left" : "right";
            String fileName = "./media/door_" + type + "_" + i + ".png";
            File file = new File (fileName);
            BufferedImage img = ImageIO.read (file);
            im[i - 1] = img;
         } catch (IOException e) {
            // Do nothing
         }
      }

      return im;
   }

   private void runWork () {
      while (noStopRequested) {
         currFrame = (currFrame + 1) % frameList.length;
         repaint ();
         try {
            Thread.sleep (msPerFrame);
         } catch (InterruptedException x) {
            Thread.currentThread ().interrupt ();
         }
      }
   }

   public void stopRequest () {
      noStopRequested = false;
      internalThread.interrupt ();
   }

   public boolean isAlive () {
      return internalThread.isAlive ();
   }

   public void paint (Graphics g) {
      int xPos = 0;
      if (isLeft) {
         xPos = 0;
      }
      g.drawImage (frameList[currFrame], xPos, 0, this);
   }

   public static void main (String[] args) {
      MovingDoor left = new MovingDoor (true);
      MovingDoor right = new MovingDoor (false);
      JFrame f = new JFrame ();
      f.setLayout (new FlowLayout ());
      f.add (left);
      f.add (right);

      f.setSize (450, 250);
      f.setVisible (true);
      f.addWindowListener (new WindowAdapter () {
         public void windowClosing (WindowEvent e) {
            System.exit (0);
         }
      });
   }
}