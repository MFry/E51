import java.util.ArrayList;


public class Building {
    //LinkedList<Person>[] floors;
    
    ArrayList<Person> floors [][]; 
   
    // Array of two arrays that have a 
    int numbFloors;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int STATIC = 2;
    
    @SuppressWarnings("unchecked")
    public Building(int numFloors) {
        numbFloors = numFloors;
        floors = (ArrayList<Person>[][]) new ArrayList[numFloors][3];
        for (int i = 0; i < floors.length; i++){
            floors[i][0] = new ArrayList<Person>();
            floors[i][1] = new ArrayList<Person>();
            floors[i][2] = new ArrayList<Person>();
        }

        // 0 is array list of people going down
        // 1 is array list of people going up
        // 2 is array list of people staying in the floor
    }

    
    public int getPeople(int floor, int state){
        /**
         * Returns the number of people in a floor wishing to go either up or down
         */
        return floors[floor][state].size ();
    }
    
    public void enterBuilding(Person some){
        // Inserts the person on the specific floor and queue of either going up
        // or down
        if(some.getDirection() == 1){ // Going up
            floors[0][UP].add (some);
        }
        else if (some.getDirection() == -1){ // Going down
            floors[0][DOWN].add(some);
        } else {
            floors[0][STATIC].add (some);
        }
    }
    
    // Not sure
    public void insertInFloor(int floor, Person some){
        // remove first
        floors[floor][STATIC].add (some);
    }
    // int currentFloor, int state, Integer destination
    public Person removeFromFloor(int currentFloor, int state, Integer destination){
        // find person in that floor.
        //Person removed = new Person()
        return floors[currentFloor][state].remove (0);
    }
    
    public Person remove(Person some){
        Person test;
        // People going up
        if(some.getDirection() == 1){
            test = floors[some.getDestinationFloor()][UP].remove (floors[some.getDestinationFloor()][0].indexOf (some));
            return test;
        }
        else {
            test = floors[some.getDestinationFloor()][1].remove (floors[some.getDestinationFloor()][1].indexOf (some));
            return test;
        }
    }
    
    
    
}
