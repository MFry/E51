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
        // 0 is arraylist of people going up
        // 1 is arralist of people going up
    }
    //TODO add a method with this signature: public int getPeople (int floor, int state)
    // returns the number of people wishing to go up or down
    
    public void insert(Person some){
        if(some.getDirection() == 1){
            floors[some.getDestinationFloor()][0].add (some);
        }
        else if (some.getDirection() == -1){
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
