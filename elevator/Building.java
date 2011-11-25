import java.util.ArrayList;
import java.util.LinkedList;

public class Building {
   // LinkedList<Person>[] floors;

   // A two dimensional array structured as:
   // [Building Floors][People Going Up/Down/Staying] containing linked lists of
   // people
   ArrayList<Person> floors[][];

   int numbFloors;
   int numbElevators;
   public static final int UP = 0;
   public static final int DOWN = 1;
   public static final int STATIC = 2;

   @SuppressWarnings("unchecked")
   public Building(int numFloors, int numElevators) {
      numbFloors = numFloors;
      numbElevators = numElevators;
      floors = (ArrayList<Person>[][]) new ArrayList[numFloors][3];
      for (int i = 0; i < floors.length; i++) {
         floors[i][0] = new ArrayList<Person>();
         floors[i][1] = new ArrayList<Person>();
         floors[i][2] = new ArrayList<Person>();
      }

      // 0 is array list of people going down
      // 1 is array list of people going up
      // 2 is array list of people staying in the floor
   }

   public int getPeople(int floor, int state) {
      /**
       * Returns the number of people in a floor wishing to go either up or down
       */
      return floors[floor][state].size();
   }

   public void enterBuilding(Person some) {
      // Inserts the person on the specific floor and queue of either going up
      // or down
      if (some.getDirection() == 1) { // Going up
         floors[0][UP].add(some);
      } else if (some.getDirection() == -1) { // Going down
         floors[0][DOWN].add(some);
      } else {
         floors[0][STATIC].add(some);
      }
   }

   // Seems like not useful.
   public void insertInFloor(int floor, Person some) {
      // remove first
       int direction = some.getDirection ();
       if ( direction == -1){
           direction = DOWN;
       } else if (direction == 1){
           direction = UP;
       } else {
           direction = STATIC;
       }
      floors[floor][direction].add(some);
   }
   
   /**
    * 
    * @param floor
    * @param people
    * This method insert a group of people into the given floor
    */
   public void insertInFloor(int floor, LinkedList<Person> people){
       for (int i = 0; i < people.size ();i++){
           people.get(i).setDirection (Person.STATIC);
          floors[floor][STATIC].add(people.get (i));
       }
   }

   // int currentFloor, int state, Integer destination
   public Person removeFromFloor(int currentFloor, int state,
         Integer destination) {
      // find person in that floor.
      // Person removed = new Person()
      return floors[currentFloor][state].remove(0);
   }

   // Removes an specific person from its proper queue
   public Person remove(Person some) {
      Person test = null;
      if (some.getDirection() == 1) {
         test = floors[some.getDestinationFloor()][UP].remove(floors[some
               .getDestinationFloor()][UP].indexOf(some));
      } else if (some.getDirection() == -1) {
         test = floors[some.getDestinationFloor()][DOWN].remove(floors[some
               .getDestinationFloor()][DOWN].indexOf(some));
      }
      return test;
   }

   // Removes the first person on the queue according to its direction
   public Person remove(int floor, int direction) {
      Person test;
      if ( direction == -1){
          direction = DOWN;
      } else if (direction == 1){
          direction = UP;
      } else {
          direction = STATIC;
      }
      test = floors[floor][direction].remove(0);
      return test;
   }

   public int getNumElevators() {
      return numbElevators;
   }

}
