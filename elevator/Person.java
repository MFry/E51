
public class Person {

    private int ID;
    private int initialTime;
    private int destinationFloor;
    private int direction;
    
    public Person(int id, int initT, int destFl, int direct){
        setID (id);
        setInitialTime (initT);
        setDestinationFloor (destFl);
        setDirection (direct);
    }

    public int getDirection () {
        return direction;
    }

    public void setDirection (int direction) {
        this.direction = direction;
    }

    public int getID () {
        return ID;
    }

    public void setID (int iD) {
        ID = iD;
    }

    public int getInitialTime () {
        return initialTime;
    }

    public void setInitialTime (int initialTime) {
        this.initialTime = initialTime;
    }

    public int getDestinationFloor () {
        return destinationFloor;
    }

    public void setDestinationFloor (int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    
}
