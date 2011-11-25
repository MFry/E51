import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

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

    public static void main (String[] args) {
        Building building = new Building (10, 1);
        Elevator[] elevators = new Elevator[1];
        for(int i = 0; i < elevators.length; i++){
            elevators[i] = new Elevator(10, 0, 10, "d");
        }
        ElevatorManager manager = new ElevatorManager(elevators, building, "d");
        /**
         * TODO For every time unit the manager should do something
         * Check according to the state, if person has to be created or moved.
         */
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream ("test.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream (fstream);
            BufferedReader br = new BufferedReader (new InputStreamReader (in));
            String strLine;
            Scanner scan;
            String movement;
            int time=0;
            int currentTime = 0;
            int destFloor;
            int direction;
            // Read File Line By Line
            while ( ( strLine = br.readLine ()) != null) {
                // Manage for every single time
                scan = new Scanner(strLine);
                movement = scan.next ();
                time = scan.nextInt ();
                direction = scan.nextInt ();
                destFloor = scan.nextInt ();
                if((time != currentTime)){ // check this line for bugs
                    // Do the movements since there is a change on management
                    manager.manage ();
                } else {
                    if (movement.equals ("create")){
                        building.enterBuilding (new Person(idGenerator++, time, destFloor, direction));
                    }
                    else if (movement.equals("move")) {
                        /**
                         * TODO How do I see what floor I am currently in if the buildings does not allow me to do that?
                         */
                        System.out.println("move");
                    }
                }

                currentTime = time;
            }
            // Close the input stream
            in.close ();
        } catch (Exception e) {// Catch exception if any
            System.err.println ("Error: " + e.getMessage ());
        }
    }

}
