public class ControlSystem {
   /** Int array containing the positions of each elevator */
   int[] positions;

   /** Generates a new control system for the elevators */
   public ControlSystem (int numElevators) {
      positions = new int[numElevators];
      for (int j = 0; j < numElevators; j++) {
         positions[j] = 0;
      }
   }

   /**
    * Moves each elevator one step in the int array
    * @return New positions array
    */
   public int[] step () {
      // Get the state of each individual elevator and then update
      for (int j = 0; j < positions.length; j++) {
         positions[j] = positions[j]++;
      }
      return positions;
   }
}
