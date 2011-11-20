import java.util.HashMap;
import java.util.PriorityQueue;

public class Elevator {

   // @NOTE: there is not elevator state for up and down
   // because this is the job of the elevator manager

   private int floor; // The current floor of the elevator
   private int startingFloor; // The floor the elevator starts on
   private final int maxCap;
   private int distanceTrav;
   private int curCap; // Current Capacity of the elevator
   private int state; // -1 going down, 0 not moving, 1 going up
   private PriorityQueue<Integer> goals;
   private HashMap<Integer, Person> contains;
   public static int DOWN = -1;
   public static int STATIC = 0;
   public static int UP = 1;
   private FloorComparatorDown down = new FloorComparatorDown();
   private FloorComparatorUp up = new FloorComparatorUp();

   public Elevator(int maxCap, int start) {
      this.maxCap = maxCap;
      this.distanceTrav = 0;
      this.startingFloor = start;
      goals = new PriorityQueue<Integer>();
      contains = new HashMap<Integer, Person>(maxCap);
   }

   public int getCurCap() {
      // returns the current capacity of the elevator
      return curCap;
   }

   public void setCurCap(int people) {
      this.curCap = people;
   }

   public boolean changeState(int newState) {
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
      if (newState > 0) {
         goals = new PriorityQueue<Integer>(2 * maxCap, up);
      } else {
         goals = new PriorityQueue<Integer>(2 * maxCap, down);
      }
   }

   public int getDistance() {
      return distanceTrav;
   }

   public int getState() {
      return state;
   }

   public boolean isActive() {
      if (state != 0) {
         return true;
      }
      return false;
   }

   private boolean checkValid(int f) {
      // Takes the input f (floor) and check if its a valid floor
      // Example: if f is somewhere below the current floor of the elevator but
      // the elevator is going up it will be rejected

      if (f < floor && state == UP) {
         return false;
      } else if (f > floor && state == DOWN) {
         return false;
      }
      return true;
   }

   public boolean enter(Person p) {
      // Adds a person when appropriate inside the elevator
      // keeping track of necessary information and without losing the instance
      // of the person

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
