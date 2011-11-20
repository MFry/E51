import java.util.HashMap;
import java.util.PriorityQueue;

public class Elevator {
   
//TODO need to add a function that allows the manager to add goals for the elevator
   
   private int floor; // The current floor of the elevator
   private int startingFloor; // The floor the elevator starts on
   private final int maxCap;
   private int distanceTrav;
   private int curCap; // Current Capacity of the elevator
   private int state; // -1 going down, 0 not moving, 1 going up
   private PriorityQueue<Integer> goals; // The goals for the elevator
   private HashMap<Integer, Person> contains; // The people the elevator
                                              // contains
   private FloorComparatorDown down = new FloorComparatorDown();
   private FloorComparatorUp up = new FloorComparatorUp();
   /* Variables to be used by the manager as well as the elevator */
   public static final int DOWN = -1;
   public static final int STATIC = 0;
   public static final int UP = 1;

   public Elevator(int maxCap, int start) {
      // TODO need to keep in mind a maximum floor an elevator can reach
      /***
       * The constructor for an elevator, with an initial starting floor and
       * maximum capacity
       */
      this.maxCap = maxCap;
      this.distanceTrav = 0;
      this.startingFloor = start;
      this.state = STATIC; // The elevator hasn't moved yet
      goals = new PriorityQueue<Integer>();
      contains = new HashMap<Integer, Person>(maxCap);
   }

   public int getCurCap() {
      /*** returns the current capacity of the elevator */
      return curCap;
   }

   public void setCurCap(int people) {
      /*** Changes the current capacity of the elevator */
      this.curCap = people;
   }

   public boolean changeState(int newState) {
      /*** Changes whether the elevator goes up or down */
      if (newState != state) {
         if (goals.isEmpty()) {
            state = newState;
            this.setState(newState);
            return true;
         }
      }
      return false;
   }

   private void setState(int newState) {
      /***
       * deletes the old goals Queue and changes it to the appropriate one so
       * that it priorities based on the state
       */
      if (newState > 0) {
         goals = new PriorityQueue<Integer>(2 * maxCap, up);
      } else {
         goals = new PriorityQueue<Integer>(2 * maxCap, down);
      }
   }

   public int getDistance() {
      /*** Returns the total distance the elevator has traveled */
      return distanceTrav;
   }

   public int getState() {
      /*** Returns the direction the elevator will move */
      return state;
   }

   public boolean isActive() {
      /*** returns whether the elevator has moved in the last unit time */
      if (state != 0) {
         return true;
      }
      return false;
   }

   private boolean checkValid(int f) {
      /***
       * Takes the input f (floor) and check if its a valid floor.
       * Example: if f is somewhere below the current floor of 
       * the elevator but the elevator is going up it will be rejected
       */

      if (f < floor && state == UP) {
         return false;
      } else if (f > floor && state == DOWN) {
         return false;
      }
      return true;
   }

   public boolean enter(Person p) {
      /***
       * Adds a person when appropriate inside the elevator keeping track of
       * necessary information and without losing the instance of the person
       */

      if (curCap == maxCap) {
         return false;
      }
      int floorWanted = p.getDestinationFloor();
      if (checkValid(floorWanted))
         goals.add(floorWanted);
      contains.put(floorWanted, p);
      curCap++;
      return true;
   }

   public Person update() {
      /*** moves the elevator forward one unit of time */
      if (!goals.isEmpty()) {
         floor += state;
         distanceTrav += state;
         System.out.println("Person gets off!");
         int key = goals.remove();
         return contains.get(key);
      }
      state = STATIC; // we have nothing to do
      return null;
   }
}
