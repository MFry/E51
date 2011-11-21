
public class ElevatorManager {
   
   private Elevator[] elevators; //TODO store the elevators in priority queues one for up state and one for down states
   private Building building;
   /*
    * Mode available:
    *   d - Dumb mode (An elevator cannot switch states until it hits the top floor
    */
   private boolean dumbMode;
   
   //TODO: Be able to output the proper statistics 
   
   public ElevatorManager (Elevator[] e, Building b, String mode) {
      /***
       * Given the elevator and building the elevator will manage the elevators in constraint to the modes set
       */
      setMode (mode);
      this.elevators = e;
      this.building = b;
   }
   
   private void setMode (String mode) {
      /***
       * Extracts the string for valid modes
       */
      for (int i = 0; i < mode.length(); ++i) {
         if ('d' == mode.charAt(i)) {
            dumbMode = true;
         }
      }
   }
   
   public void manage () {
      
      //this cannot and should not happen
      assert elevators == null;
      assert building == null;
      
      
   }
   
   private void dumbManage () {
      //Dumb Manager
      //check elevator state and the floor it is on
      
      //poll the floors
      for (int i = 0; i < building.floors.length; ++i) {
         //Give the elevator a goal
         if (building.getPeople (i, Elevator.UP)) {
            
         }
      }
      
      
      
      
   }
   
   //It will need to poll the building to see which floor has the most people 
   
}
