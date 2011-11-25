public class ControlSystem implements ControlSystemInterface {
   Elevator[] elevators;
   int numElevators;

   /** Generates a new control system for the elevators */
   public ControlSystem (int numElevators, Elevator[] e) {
      elevators = e;
      this.numElevators = numElevators;
   }

   /**
    * Moves each elevator one step in the int array
    * @return New positions array
    */
   public Elevator[] step () {
      // Get the state of each individual elevator and then update
      for (int j = 0; j < numElevators; j++) {
         
      }
      return positions;
   }
}
