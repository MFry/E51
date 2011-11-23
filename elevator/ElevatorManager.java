import java.util.PriorityQueue;

public class ElevatorManager {

   private Elevator[] elevators; // TODO store the elevators in priority queues
                                 // one for up state and one for down states
   private Building building;
   private boolean[] requests;
   PriorityQueue<Integer> curFloors;
   /*
    * Mode available: d - Dumb mode (An elevator cannot switch states until it
    * hits the top floor
    */
   private boolean dumbMode;

   // TODO: Be able to output the proper statistics

   public ElevatorManager(Elevator[] e, Building b, String mode) {
      /***
       * Given the elevator and building the elevator will manage the elevators
       * in constraint to the modes set
       */
      setMode(mode);
      this.elevators = e;
      this.building = b;
      FloorComparatorDown down = new FloorComparatorDown();
      curFloors = new PriorityQueue<Integer>(elevators.length, down);
      requests = new boolean[b.floors.length];
   }

   private void setMode(String mode) {
      /***
       * Extracts the string for valid modes
       */
      for (int i = 0; i < mode.length(); ++i) {
         if ('d' == mode.charAt(i)) {
            dumbMode = true;
         }
      }
   }

   public void manage() {

      // this cannot and should not happen
      assert elevators == null;
      assert building == null;
      if (dumbMode == true) {
         dumbManage();
      }
   }

   private void updateElevatorFloor() {
      /***
       * Updates the floors on which elevators currently reside
       */
      for (int i = 0; i < elevators.length; ++i) {
         curFloors.add(elevators[i].getCurrentFloor());
      }
   }

   private Elevator getElevator (int elevatorFloor, int state) {
      
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getCurrentFloor() == elevatorFloor && elevators[i].getState() == state) {
            return elevators[i];
         }
      }
      System.err.println("Error has occurred: No elevator found"); 
      return null;
   }
   
   private Elevator getElevator (int elevatorFloor) {
      
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getCurrentFloor() == elevatorFloor) {
            return elevators[i];
         }
      }
      System.err.println("Error has occurred: No elevator found"); 
      return null;
   }
   
   private void dumbManage() {
      // updates the current floors of all the elevators
      updateElevatorFloor();
      // Generate goals for elevators going up
      for (int i = 0; i < building.floors.length; ++i) {
         int elevatorOnFloor = curFloors.remove();
         if (elevatorOnFloor <= i) {
            Elevator curElevator = getElevator (elevatorOnFloor);
            // There are people on this floor waiting for the elevator
            if (building.getPeople(i, Elevator.UP) > 0) {
               while (curElevator.)
            }
            //because it is a dumb elevator both people going up and down can be picked up
            if (building.getPeople(i, Elevator.DOWN) > 0) {
               
            }
         }
      }
      // generate goals for elevators going down

   }

   // It will need to poll the building to see which floor has the most people

}
