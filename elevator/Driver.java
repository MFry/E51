import java.util.ArrayList;

/**
 * 
 * 
 *
 */
public class Driver {
    
    // Create a method that reads input from Joe's output
    // For each person, if they are inserted, create as a new person.
    // Then insert into the building accordingly
    public static int idGenerator = 1;
    public static void main(String[] args){
        Building building = new Building(10);
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person(idGenerator++, 0, 10, 1));
        people.add(new Person(idGenerator++, 0, 10, 1));
        people.add(new Person(idGenerator++, 0, 10, 1));
        people.add (new Person(idGenerator++, 0, 10, 1));
        people.add (new Person(idGenerator++, 0, 10, 1));
        people.add(new Person(idGenerator++, 0, 10, 1));
        people.add (new Person(idGenerator++, 0, 10, 1));
        people.add(new Person(idGenerator++, 0, 10, 1));
        people.add (new Person(idGenerator++, 1, 10, 1));
        people.add(new Person(idGenerator++, 1, 10, 1));
        people.add(new Person(idGenerator++, 2, 10, 1));
        people.add (new Person(idGenerator++, 2, 10, 1));
        people.add (new Person(idGenerator++, 3, 10, 1));
        people.add(new Person(idGenerator++, 4, 10, 1));
        people.add (new Person(idGenerator++, 7, 10, 1));
        people.add(new Person(idGenerator++, 8, 10, 1));
        people.add (new Person(idGenerator++, 7, 10, 1));
        people.add (new Person(idGenerator++, 9, 10, 1));
        for(Person x: people){
            building.enterBuilding (x);
        }
    }
    
}
