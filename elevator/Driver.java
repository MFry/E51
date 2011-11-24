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
        Building building = new Building (10, 2);
        /**
         * TODO For every time unit the manager should do something
         * Check according to the state, if person has to be created or moved.
         */
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream ("textfile.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream (fstream);
            BufferedReader br = new BufferedReader (new InputStreamReader (in));
            String strLine;
            Scanner scan;
            String movement;
            int time;
            int destFloor;
            int direction;
            // Read File Line By Line
            while ( ( strLine = br.readLine ()) != null) {
                // Manage for every single time
                scan = new Scanner(strLine);
                time = scan.nextInt ();
                movement = scan.next ();
                destFloor = scan.nextInt ();
                direction = scan.nextInt ();
                
                if (movement.equals ("create")){
                    building.enterBuilding (new Person(idGenerator++, time, destFloor, direction));
                }
                else {
                    // do movements within floors throughout the elevator manager
                }
            }
            // Close the input stream
            in.close ();
        } catch (Exception e) {// Catch exception if any
            System.err.println ("Error: " + e.getMessage ());
        }
    }

}
