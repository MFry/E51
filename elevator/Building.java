import java.util.ArrayList;


public class Building {
    //LinkedList<Person>[] floors;
    
    ArrayList<Person> floors [][]; 
   
    // Array of two arrays that have a 
    int numbFloors;
    
    @SuppressWarnings("unchecked")
    public Building(int numFloors) {
        numbFloors = numFloors;
        floors = (ArrayList<Person>[][]) new ArrayList[numFloors][2];
        // 0 is arraylist of people going down
        // 1 is arralist of people going up
    }

    
    public int getPeople(int floor, int state){
        /**
         * Returns the number of people in a floor wishing to go either up or down
         */
        return floors[floor][state].size ();
    }
    
    public void insert(Person some){
        // Inserts the person on the specific floor and queue of either going up
        // or down
        if(some.getDirection() == 1){ // Going up
            floors[some.getDestinationFloor()][0].add (some);
        }
        else if (some.getDirection() == -1){ // Going down
            floors[some.getDestinationFloor()][1].add(some);
        }
    }
    
    public Person remove(Person some){
        Person test;
        if(some.getDirection() == 1){
            test = floors[some.getDestinationFloor()][0].remove (floors[some.getDestinationFloor()][0].indexOf (some));
            return test;
        }
        else {
            test = floors[some.getDestinationFloor()][1].remove (floors[some.getDestinationFloor()][1].indexOf (some));
            return test;
        }
    }
    
    
    
}
