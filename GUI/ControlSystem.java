
public class ControlSystem {
   public ControlSystem () {
      
   }
   public void step () {
      // Get the state of each individual elevator and then update
      for (int j = 0; j < elevators; j++) {
         sliders[j].setValue ((int) (Math.random () * floors));
      }
      while (true){
         for (int j = 0; j < elevators; j++) {
            sliders[j].setValue (sliders[j].getValue () + 1);
            if (sliders[j].getValue () == 0 || sliders[j].getValue () == floors) {
               sliders[j].setValue ((int) (Math.random () * floors));
            }
         }
         Thread.sleep (rate);
      }
   }
}
