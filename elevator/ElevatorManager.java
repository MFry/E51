import java.util.PriorityQueue;

public class ElevatorManager {

   private Elevator[] elevators; 
   private Building building;
   private boolean[] upRequestsServed;
   private boolean[] downRequestsServed; 
   PriorityQueue<Elevator> upElevators;
   PriorityQueue<Elevator> downElevators; // TODO Implement this
   PriorityQueue<Integer> curFloorsUp;
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
      curFloorsUp = new PriorityQueue<Integer>(elevators.length, down);
      upRequestsServed = new boolean[b.floors.length];
      downRequestsServed = new boolean[b.floors.length];
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

   private void generateUpQueue() {
      //TODO Write documentation
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getState() == Elevator.UP
               || elevators[i].getState() == Elevator.STATIC) {
            upElevators.add(elevators[i]);
         }
      }
   }

   private Elevator getElevator(int elevatorFloor, int state) {

      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getCurrentFloor() == elevatorFloor
               && elevators[i].getState() == state) {
            return elevators[i];
         }
      }
      System.err.println("Error has occurred: No elevator found");
      return null;
   }

   private void dumbManage() {
      // updates the current floors of all the elevators
      generateUpQueue();
      // Generate goals for elevators going up

      for (int i = 0; i < building.floors.length; ++i) {
         Elevator curElevator = upElevators.remove();
         // Checks if the elevator is full, if it is it will remove the next one
         if (curElevator.isFull()) {
            --i;
            continue;
         }
         if (curElevator.getCurrentFloor() <= i) {
           int people = building.getPeople(i, Elevator.UP); 
           if (curElevator.getCurrentFloor() == i) {
               // Check if there are people on this floor waiting for the
               // elevator
               
               if (people > 0) {
                  // take as many people as possible
                  while (!curElevator.isFull() && people > 0) {
                     curElevator.enter(building.remove(i, Building.UP));
                     --people;
                  }
               }
            } else { //The elevator has yet to reach this floor
              //generate goals for elevator 
              if (people > 0 && !upRequestsServed[i]) {
                 curElevator.setGoal(i); //TODO For the more intelligent elevator we will need a scheduler
              }
            }
         }
      }
      // generate goals for elevators going down

   }
   // It will need to poll the building to see which floor has the most people

}
