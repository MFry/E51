import java.util.LinkedList;
import java.util.PriorityQueue;

public class ElevatorManager {

   private Elevator[] elevators;
   private Building building;
   private boolean[] upRequestsServed;
   private boolean[] downRequestsServed;
   PriorityQueue<Elevator> upElevators;
   PriorityQueue<Elevator> downElevators;
   // INTELI ELEVATOR
   private int[][] oldBuildingState;
   private int priorityFieldDistance = 2;
   /*
    * Mode available: d - Dumb mode (An elevator cannot switch states until it
    * hits the top floor
    */
   private boolean dumbMode;
   private boolean smartMode;
   // Different scalling Modes for the smart elevator
   private boolean knownPeoplePerFloor; // TODO Implement this
   private boolean knownDestinations; // TODO Implement this
   private static final int DOWN = 0;
   private static final int UP = 1;
   private int[][] curBuildingState;

   // TODO: Be able to output the proper statistics
   /***
    * Given the elevator and building the elevator will manage the elevators in
    * constraint to the modes set
    * 
    * @param e
    *           An array of all the elevators
    * @param b
    *           The building
    * @param mode
    *           A string of modes that are set
    */
   public ElevatorManager(Elevator[] e, Building b, String mode) {
      setMode(mode);
      this.elevators = e;
      this.building = b;
      ElevatorComparatorAscending up = new ElevatorComparatorAscending();
      ElevatorComparatorDescending down = new ElevatorComparatorDescending();
      upElevators = new PriorityQueue<Elevator>(e.length, up);
      downElevators = new PriorityQueue<Elevator>(e.length, down);
      upRequestsServed = new boolean[b.floors.length];
      downRequestsServed = new boolean[b.floors.length];
      oldBuildingState = new int[b.numbFloors][2]; // Its 2 instead of 3
                                                   // because we do not care
                                                   // about people that do not
                                                   // want to move
      init(); // TODO recode this
   }

   private void init() {
      // TODO Documentation
      // Sets all elevators on the bottom floor to an up state
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getCurrentFloor() == 0) {
            elevators[i].changeState(Elevator.UP);
         }
      }
   }

   private void setMode(String mode) {
      /***
       * Extracts the string for valid modes
       */
      // TODO Complete setMode for other mode that we will add
      mode = mode.toLowerCase();
      for (int i = 0; i < mode.length(); ++i) {
         if ('d' == mode.charAt(i)) {
            dumbMode = true;
         } else if ('s' == mode.charAt(i)) {
            smartMode = true;
         }
         if (smartMode) {
            // TODO Implement expandable options
         }
      }
   }

   public void manage() {
      // this should not happen
      assert elevators == null;
      assert building == null;
      // TODO Create other modes
      if (dumbMode == true) {
         dumbManage();
      } else if (smartMode == true) {
         smartElevator();
      }
   }

   private void generateUpQueue() {
      // TODO Write documentation
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getState() == Elevator.UP
               || elevators[i].getState() == Elevator.STATIC) { // TODO
                                                                // THIS
                                                                // BEHAVIOUR
                                                                // NEEDS
                                                                // TO BE
                                                                // NOTED
            if (!elevators[i].isFull()) { // Ignores full elevators
               upElevators.add(elevators[i]);
            }
         }
      }
   }

   private void generateDownQueue() {
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getState() == Elevator.DOWN
               || elevators[i].getState() == Elevator.STATIC) { // TODO
                                                                // THIS
                                                                // BEHAVIOUR
                                                                // NEEDS
                                                                // TO BE
                                                                // NOTED
            if (!elevators[i].isFull()) { // Ignores full elevators
               downElevators.add(elevators[i]);
            }
         }
      }
   }

   // TODO JOE TEST THIS
   /***
    * Finds the floor of every elevator going in the desired location
    * 
    * @param state
    *           Please use either Elevator.UP or Elevator.DOWN for polling of
    *           the desired elevators
    * 
    * @returns The location of all the elevators going in the appropriate
    *          location
    */
   private int[] getElevatorFloors(int state) {
      int totalElevators = 0;
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getState() == state
               || elevators[i].getState() == Elevator.STATIC) {
            ++totalElevators;
         }
      }
      int elevatorFloors[] = new int[totalElevators];
      for (int i = 0; i < elevators.length; ++i) {
         if (elevators[i].getState() == state
               || elevators[i].getState() == Elevator.STATIC) {
            elevatorFloors[i] = elevators[i].getCurrentFloor();
         }
      }
      System.err.println("Error has occurred: No elevator found");
      return null;
   }

   // TODO JOE TEST THIS
   /***
    * @returns the number of floors that have had new people call the elevators
    */
   private int getNumberOfActiveFloors(int buildingState) {
      int total = 0;
      for (int i = 0; i < curBuildingState.length; ++i) {
         if (curBuildingState[i][buildingState] > 0) {
            ++total;
         }
      }
      return total;
   }

   /***
    * Runs all elevators a single unit time
    */
   private void runAllElevators() {
      for (int i = 0; i < elevators.length; ++i) {
         LinkedList<Person> people = elevators[i].update();
         if (people != null) {
            building.insertInFloor(elevators[i].getCurrentFloor(), people);
         }
      }
   }

   /***
    * The dumb elevator follows a very strict and poorly optimized: 1. The
    * elevator must go completely down or up before it picks up people 2. The
    * elevator will not pick up people going down until it switches to down
    * state NOTES Dumb Elevator does not have a static mode
    */
   private void dumbManage() {
      // updates the current floors of all the up elevators
      generateUpQueue();
      // Generate goals for elevators going up
      Elevator curElevator = null;
      while (!upElevators.isEmpty()) {
         curElevator = upElevators.remove();
         for (int i = 0; i < building.floors.length; ++i) {
            if (curElevator.getCurrentFloor() <= i) {
               int people = building.getPeople(i, Building.UP);
               if (people > 0) {
                  // We only do work for floors that have people on them
                  if (curElevator.getCurrentFloor() == i) {
                     // Take as many people from this floor
                     // as the elevator allows
                     upRequestsServed[i] = false; // An elevator has
                                                  // serviced
                                                  // the
                                                  // floor
                     while (!curElevator.isFull() && people > 0) {
                        curElevator.enter(building.remove(i, Building.UP));
                        --people;
                     }
                  } else {// The elevator has yet to reach this floor
                     // generate goals for elevator
                     if (people > 0 && !upRequestsServed[i]) {
                        upRequestsServed[i] = true;
                        curElevator.setGoal(i); // TODO For the more
                                                // intelligent
                                                // elevator we will
                                                // need a
                                                // scheduler
                     }
                  }
               }
            }
         }
      }

      // updates the current floors of all the up elevators
      generateDownQueue();
      // generate goals for elevators going down
      while (!downElevators.isEmpty()) {
         curElevator = downElevators.remove();
         for (int i = building.floors.length - 1; i >= 0; --i) {
            if (curElevator.getCurrentFloor() >= i) {
               int people = building.getPeople(i, Building.DOWN);
               if (people > 0) {
                  // We only do work for floors that have people on them
                  if (curElevator.getCurrentFloor() == i) {
                     // Take as many people from this floor
                     // as the elevator allows
                     downRequestsServed[i] = false; // An elevator has
                                                    // serviced
                                                    // the
                                                    // floor
                     while (!curElevator.isFull() && people > 0) {
                        curElevator.enter(building.remove(i, Building.DOWN));
                        --people;
                     }
                  } else {// The elevator has yet to reach this floor
                     // generate goals for elevator
                     if (people > 0 && !downRequestsServed[i]) {
                        downRequestsServed[i] = true;
                        curElevator.setGoal(i); // TODO For the more
                                                // intelligent
                                                // elevator we will
                                                // need a
                                                // scheduler
                     }
                  }
               }
            }
         }
      }
      runAllElevators();
   }

   // TODO Exponential recharge time for elevator chaining
   // It will need to poll the building to see which floor has the most people
   private void smartElevator() {
      if (checkBuildingState()) {
         // Manager only does work for tasks that have not been computed and
         // assigned yet
         // calculate floor priority
         int[] buildingOrder = intelliScheduler(Building.UP);
      }
      // TODO Update oldBuildingState
   }

   // TODO JOE TEST THIS
   private boolean checkBuildingState() {
      boolean changeOccured = false; // Assume no change
      curBuildingState = new int[oldBuildingState.length][2];
      for (int i = 0; i < building.numbFloors; ++i) {
         // Check the current building state by comparing new state to old
         // state
         int upState = building.getPeople(i, Building.UP)
               - oldBuildingState[i][ElevatorManager.UP];
         int downState = building.getPeople(i, Building.DOWN)
               - oldBuildingState[i][ElevatorManager.DOWN];
         if (upState > 0 || downState > 0) {
            changeOccured = true; // change occurred
         }
         curBuildingState[i][ElevatorManager.UP] = upState;
         curBuildingState[i][ElevatorManager.DOWN] = downState;
      }
      return changeOccured;
   }

   private int[] intelliScheduler(int buildingState) {
      int[] floorSchedule = null;
      // What information can we know? and how can we use it
      if (knownDestinations) {

      } else if (knownPeoplePerFloor) {

      } else {
         floorSchedule = primitiveScheduler(buildingState);
      }
      assert floorSchedule != null;
      return floorSchedule;
   }

   private int[] primitiveScheduler(int buildingState) {
      int floorSchedule[] = new int[getNumberOfActiveFloors(buildingState)];
      int wantedElevators; // Gets the appropriate elevator state
      if (buildingState == Building.UP) {
         wantedElevators = Elevator.UP;
      } else {
         wantedElevators = Elevator.DOWN;
      }
      // Gets the position of all the appropriate elevators
      int[] elevatorFloors = getElevatorFloors(wantedElevators);
      for (int i = 0; i < curBuildingState.length; ++i) {
         if (curBuildingState[i][buildingState] > 0) {
            int sum = 0;
            for (int j = 0; j < elevatorFloors.length; ++j) {

            }
         }
      }
      return floorSchedule;
   }

   private LinkedList<Elevator> generatePriorityFields(int direction, int floor) {

      // create LinkList that will hold all the elevators in the building
      // and it will be gradually decreased until it contains all the
      // desirable elevators
      LinkedList<Elevator> elevatorList = new LinkedList<Elevator>();

      // add all the elevators initially
      for (int i = 0; i < elevators.length; i++) {
         elevatorList.add(elevators[i]);
      }

      // remove all elevators that we don't need
      // which at this point, are all the ones that are full
      for (Elevator elevator : elevatorList) {
         if (elevator.isFull()) {
            elevatorList.remove(elevator);
         }
      }

      // remove elevators moving in the opposite direction that are NOT empty
      for (int i = 0; i < elevatorList.size(); i++) {
         if (!elevatorList.get(i).isEmpty()
               && elevatorList.get(i).getState() != direction) {
            elevatorList.remove(elevatorList.get(i));
         }
      }
      /*
       * for (Elevator elevator : elevatorList) { if (!elevator.isEmpty () &&
       * elevator.getState () != direction) { elevatorList.remove (elevator); }
       * }
       */

      /*
       * // remove ones that are not within the desired proximity for (Elevator
       * elevator : elevatorList) { if ( ( elevator.getCurrentFloor () > floor +
       * priorityFieldDistance) || ( elevator.getCurrentFloor () < floor -
       * priorityFieldDistance)) { elevatorList.remove (elevator); } }
       */

      elevatorList = atomicSort(elevatorList, floor);

      return elevatorList;
   }

   private static LinkedList<Elevator> atomicSort(
         LinkedList<Elevator> localElevatorList, int proximityFloor) {
      // sort based on proximity
      // if proximities are equal, sort by the elevator with the most people

      // Loop once for each element in the array.
      for (int counter = 0; counter < localElevatorList.size() - 1; counter++) {

         // Once for each element, minus the counter.
         for (int index = 0; index < localElevatorList.size() - 1 - counter; index++) {

            // Test if need a swap or not.
            if (atomicCompare(localElevatorList.get(index),
                  localElevatorList.get(index + 1), proximityFloor) == -1) {
               // These three lines just swap the two elements:
               Elevator temp = localElevatorList.get(index);
               localElevatorList.set(index, localElevatorList.get(index + 1));
               localElevatorList.set(index + 1, temp);
            }
         }
      }

      return localElevatorList;
   }

   private static int atomicCompare(Elevator elevator1, Elevator elevator2,
         int floor) {

      // if the elevator 1 is further away from the target floor
      // if elevator 2 is closer
      int elevator1Distance = Math.abs(elevator1.getCurrentFloor() - floor);
      int elevator2Distance = Math.abs(elevator2.getCurrentFloor() - floor);
      if (elevator1Distance > elevator2Distance) {
         return -1; // elevator 2 is closer
      } else if (elevator1Distance < elevator2Distance) {
         return 1; // elevator 1 is closer
      } else {
         // check to see if any of the elevators are empty
         if (elevator1.isEmpty()) {
            return 1;
         }
         if (elevator2.isEmpty()) {
            return -1;
         }
         // check to see which has the most people
         if (elevator1.getCurCap() < elevator2.getCurCap()) {
            return 1; // elevator 1 is more preferred
         } else if (elevator2.getCurCap() > elevator2.getCurCap()) {
            return -1; // elevator 2 is more preferred
         } else {
            return 0; // equal
         }
      }
   }

}
