import java.util.PriorityQueue;
public class Elevator {

   // @NOTE: there is not elevator state for up and down
   // because this is the job of the elevator manager
   
   private int floor; //The current floor of the elevator
   private int startingFloor; //The floor the elevator starts on
   private final int maxCap;
   private int distanceTrav;
   private int curCap; //Current Capacity of the elevator
   private int status; // -1 going down, 0 not moving, 1 going up
   private PriorityQueue<Integer> goals;
   private static int DOWN = -1;
   private static int STATIC = 0;
   private static int UP = 1;
   
   
   public Elevator(int maxCap, int start) {
      this.maxCap = maxCap;
      this.distanceTrav = 0;
      this.startingFloor = start;
      goals = new PriorityQueue<Integer>();
   }

   public int getCurCap() {
      //returns the current capacity of the elevator
      return curCap;
   }

   public void setCurCap(int people) {
      this.curCap = people;
   }

   public int getDistance() {
      return distanceTrav;
   }

   public int getStatus() {
      return status;
   }

   private boolean checkValid(int f) {
      // Takes the input f (floor) and check if its a valid floor
      // Example: if f is somewhere below the current floor of the elevator but
      // the elevator is going up it will be rejected
      if (f < floor && status == UP) {
         return false;
      } else if (f > floor && status == DOWN) { 
         return false;
      }
      return true;
   }

   public boolean enter (int f) {
      if (curCap == maxCap) {
         return false;
      }
      goals.add(f);
      curCap++;
      return true;
   }
   
   public void update() {
   }
}
