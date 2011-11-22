import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestCaseGenerator {

    private static final int NIGHT_SHIFT_END = 500;
    private static final int NIGHT_SHIFT_BEGIN = 2300;
    /**
     * Program that generates several test cases for the elevator algorithm
     * 
     * @author Joseph Del Prete
     */

    static int buildingFloors = 5;
    int workers;
    static int maxCapacity;
    static int openingTime = 900; // 9:00 am
    static int closingTime = 1700; // 5:00 pm
    
    static ArrayList<ArrayList<JPerson>> building = new ArrayList<ArrayList<JPerson>>();

    public static void initBuilding()
    {
        for (int i = 0; i < buildingFloors; i++)
        {
            ArrayList<JPerson> tempList = new ArrayList<JPerson>();
            building.add (tempList);
        }
    }
    
    public int setBuildingFloors (int buildingFloors_1) {
        buildingFloors = buildingFloors_1;
        return buildingFloors;
    }

    public int getBuildingFloors () {
        return buildingFloors;
    }

    public int setWorkers (int workers_1) {
        workers = workers_1;
        return workers;
    }

    public int getWorkers () {
        return workers;
    }

    @SuppressWarnings("unchecked")
    public static void main (String[] args) {
        // do not necessarily need main
        // serves mainly as a caller for other methods
        initBuilding();
        ArrayList<JPerson> testPeople = generateNormalPeople ();
        Collections.sort(testPeople);
        try {
            writeOutput (testPeople, "output.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
    }

    public static ArrayList<JPerson> generateNormalPeople () {
        ArrayList<JPerson> personList = new ArrayList<JPerson> ();
        // max capacity is determined by seeded random number % buildingFloors *
        // 100
        Random randInt = new Random (1000); // seed = 1000
        maxCapacity = ( randInt.nextInt () % buildingFloors) * 100;
        if (maxCapacity < 0) {
            maxCapacity = maxCapacity * -1;
        }
        // 60% of max capacity enters in morning
        int amountOfWorkers = (int) ( maxCapacity * 0.6);
        // people can never exceed max capacity
        openingTime = 900;
        
        // generate workers for the morning, they enter at 9:00am
        int count = 0;
        for (; count < amountOfWorkers; count++)
        {
            JPerson person = new JPerson (
                    openingTime + randInt.nextInt () % 10, // time
                    1, // direction
                    0,
                    workerBeginDayChooseFloor () // destination floor
            );
            building.get(person.getDestFloor()).add(person);
            personList.add (person);
        }
        
        // generate traffic during operating hours
        int currentTime = openingTime;
        while (currentTime < closingTime - 100) {
            for (int i = 0; i < 60; i++)
            {
                int currentFloor = chooseCurrentFloor();
                int destinationFloor = chooseRandomFloor(currentFloor);
                int direction = 0;
                if (destinationFloor < currentFloor)
                {
                    direction = -1;
                } else if (destinationFloor > currentFloor)
                {
                    direction = 1;
                } else {
                    // shouldn't happen
                    direction = 0;
                }
                JPerson person = new JPerson(currentTime + i, direction, currentFloor, destinationFloor);
                building.get(person.getDestFloor ()).add (person);
                personList.add (person);
            }
            currentTime += 100; // go to the next hour
        }
        
        // random people have to leave (go to the 0th floor)
        // at one hour before closing time
        
        int count2 = 0;
        int amountOfRandomPeople = (((closingTime - 100) - openingTime)/100)*60;
        for (; count2 < amountOfRandomPeople; count2++)
        {
            JPerson temp = personList.get(count + count2);
            JPerson person = new JPerson(closingTime -100 + (randInt.nextInt () % 30), -1, temp.getDestFloor (), 0);
            personList.add(person);
            building.get(0).add (person);
        }
        
        // workers then leave at 5:00pm
        closingTime = 1700;
        count = 0;
        for (; count < amountOfWorkers; count++) {
            JPerson temp = personList.get (count);
            JPerson person = new JPerson(closingTime + randInt.nextInt () % 10, -1, temp.getDestFloor (), 0);
            building.get(person.getDestFloor ()).add (person);
            personList.add (person);
        } // personList grows by += amountOfWorkers

        // at this point, everyone is on floor zero
        // so clear the people from the other floors
        for (int i = 0; i < buildingFloors; i++) {
            if (i != 0) {
                while (!building.get(i).isEmpty ()) {
                    for (int j = 0; j < building.get (i).size(); j++) {
                        JPerson tempPerson = building.get(i).get(j);
                        building.get(0).add(tempPerson);
                        building.get(i).remove (tempPerson);
                    }
                    //for (JPerson tempPerson : building.get(i))
                    //{
                    //    building.get(0).add(tempPerson);
                    //    building.get(i).remove(tempPerson);
                    //}
                }
            }
        }
        
        // random access until next day at 8:00am, which is 0800
        // generate traffic during operating hours
        currentTime = closingTime;
        while (currentTime < 2400) { // midnight
            for (int i = 0; i < 60; i++)
            {
                int currentFloor = chooseCurrentFloor();
                int destinationFloor = chooseRandomFloor(currentFloor);
                int direction = 0;
                if (destinationFloor < currentFloor)
                {
                    direction = -1;
                } else if (destinationFloor > currentFloor)
                {
                    direction = 1;
                } else {
                    // shouldn't happen
                    direction = 0;
                }
                JPerson person = new JPerson(currentTime + i, direction, currentFloor, destinationFloor);
                building.get(person.getDestFloor ()).add (person);
                personList.add (person);
            }
            currentTime += 100; // go to the next hour
        }
        
        currentTime = 0000;
        while (currentTime < 800) { // 800 am
            for (int i = 0; i < 60; i++)
            {
                int currentFloor = chooseCurrentFloor();
                int destinationFloor = chooseRandomFloor(currentFloor);
                int direction = 0;
                if (destinationFloor < currentFloor)
                {
                    direction = -1;
                } else if (destinationFloor > currentFloor)
                {
                    direction = 1;
                } else {
                    // shouldn't happen
                    direction = 0;
                }
                JPerson person = new JPerson(currentTime + i, direction, currentFloor, destinationFloor);
                building.get(person.getDestFloor ()).add (person);
                personList.add (person);
            }
            currentTime += 100; // go to the next hour
        }
        
        // generate custodians for late night shift
        // custodians come in at 11:00pm for night shift
        int amountOfCustodians = (int)(maxCapacity * 0.10);
        count = 0;
        for (; count < amountOfCustodians; count++)
        {
            JPerson person = new JPerson (
                    NIGHT_SHIFT_BEGIN + randInt.nextInt () % 10, // time
                    1, // direction
                    0,
                    workerBeginDayChooseFloor () // destination floor
            );
            building.get(person.getDestFloor()).add(person);
            personList.add (person);
        }
        
        // custodians then leave at 5:00pm
        int currentPosition = personList.size() - amountOfCustodians -1;
        count = 0;
        for (; count < amountOfCustodians; count++) {
            JPerson temp = personList.get (currentPosition + count);
            JPerson person = new JPerson(NIGHT_SHIFT_END + randInt.nextInt () % 10, -1, temp.getDestFloor (), 0);
            building.get(person.getDestFloor ()).add (person);
            personList.add (person);
        } // personList grows by += amountOfCustodians
        
        return personList;
    }

    public static int workerBeginDayChooseFloor () {
        int destinationFloor;
        Random randInt = new Random ();
        int randomNumber = randInt.nextInt ();
        if (randomNumber < 0)
        {
            randomNumber = randomNumber * -1;
        }
        destinationFloor = randInt.nextInt () % buildingFloors;
        if (destinationFloor < 0) {
            destinationFloor *= -1;
        }
        if (destinationFloor == 0) {
            destinationFloor += 1;
        }
        return destinationFloor;
    }
    
    public static int chooseCurrentFloor()
    {
        int currentFloor;
        Random randInt = new Random();
        currentFloor = randInt.nextInt() % buildingFloors;
        if (currentFloor < 0)
        {
            currentFloor *= -1; // make it positive
        }
        return currentFloor;
    }
    
    public static int chooseRandomFloor(int currentFloor)
    {
        int randomFloor;
        Random randInt = new Random();
        int randomNumber = randInt.nextInt ();
        if (randomNumber < 0)
        {
            randomNumber = randomNumber * -1;
        }
        randomFloor = randInt.nextInt () % buildingFloors;
        if (randomFloor < 0) {
            randomFloor *= -1; // make it positive
        }
        
        if (randomFloor == currentFloor)
        {
            // change the floor by 2
            randomFloor += 2;
            if (randomFloor > (buildingFloors - 1) )
            {
                randomFloor -= 4;
            } else if (randomFloor < 0)
            {
                randomFloor += 4;
            }
        }
        
        return randomFloor;
    }

    public static void writeOutput (ArrayList<JPerson> personList,
            String outputFile) throws IOException {
        File file = new File (outputFile);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter (file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println ("File not found");
            e.printStackTrace ();
        }
        BufferedWriter bw = new BufferedWriter (fileWriter);
        for (int count = 0; count < personList.size (); count++) {
            JPerson temp = personList.get(count);
            bw.write ("Time: " + temp.getTime() + " " + "Direction: " + temp.getDirection () + " " + "Init Floor: " + temp.getInitialFloor () + " " + "Dest Floor: " + temp.getDestFloor ());
            bw.write ("\n");
        }
        bw.flush ();
        //bw.close ();
        //fileWriter.close();
        
        File statFile = new File("buildingStats.txt");
        fileWriter = null;
        try {
            fileWriter = new FileWriter(statFile);
        } catch (IOException e) {
            System.out.println ("File not found");
            e.printStackTrace ();
        }
        bw = new BufferedWriter (fileWriter);
        
        for (int i = 0; i < buildingFloors; i++)
        {
            ArrayList<JPerson> temp = building.get (i);
            for (JPerson personTemp : building.get(i))
            {
                //System.out.print (personTemp);
                bw.write (personTemp.toString () + " ");
            }
            //System.out.println ();
            bw.write("\n");
        }
        
        bw.flush();
        
        File statFile2 = new File("floorStats.txt");
        fileWriter = null;
        try {
            fileWriter = new FileWriter(statFile2);
        } catch (IOException e) {
            System.out.println ("File not found");
            e.printStackTrace ();
        }
        bw = new BufferedWriter (fileWriter);
        
        for (int i = 0; i < buildingFloors; i++)
        {
            int count = 0;
            ArrayList<JPerson> temp = building.get (i);
            for (JPerson personTemp : building.get(i))
            {
                //System.out.print (personTemp);
                count++;
                bw.write (count + " ");
            }
            //System.out.println ();
            bw.write("\n");
        }
        
        bw.flush ();
        
        bw.close ();
        fileWriter.close();
    }
}
