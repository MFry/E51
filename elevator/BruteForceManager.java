
public class BruteForceManager {
    
    public void directPeople(){
        
    }
    
    public static void main (String[] args) {
        // Person (id, initial time, destination floor, direction)
        Building testBuild = new Building(20);
        Elevator elev1 = new Elevator(10, 0, 19);
        elev1.changeState (Elevator.UP);
        Person p1 = new Person(1, 0, 15, 1);
        Person p2 = new Person(2, 0, 1, 1);
        Person p3 = new Person(3, 0, 7, 1);
        Person p4 = new Person(4, 1, 1, 1);
        Person p5 = new Person(5, 1, 2, 1);
        Person p6 = new Person(6, 1, 17, 1);
        Person p7 = new Person(7, 2, 19, 1);
        Person p8 = new Person(8, 2, 18, 1);
        
        testBuild.insert (p1);
        testBuild.insert (p2);
        testBuild.insert (p3);
        testBuild.insert (p4);
        testBuild.insert (p5);
        testBuild.insert (p6);
        testBuild.insert (p7);
        testBuild.insert (p8);
        
        
        int floor = 0;
        // testing going up
        // Driver
        
        while(floor < testBuild.floors.length){
            System.out.println(floor);
            if(!testBuild.floors[floor][0].isEmpty ()){
                for(Person x: testBuild.floors[floor][0]){
                    elev1.enter (x);
                }
            }
//            if(elev1.getCurCap () > 0){
//                /*
//                for(Person x: testBuild.floors[0][0]){
//                    //System.out.println(x.getDestinationFloor ());
//                    
//                    if(x.getDestinationFloor () == floor){
//                        elev1.update ();
//                    }
//                    
//                }
//            */
//            }
            floor++;
            elev1.update ();
            
        }
        
        

    }

}
