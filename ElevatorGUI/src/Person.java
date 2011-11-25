
public class Person {

    private int ID;
    private int waitingTime;
    private int destinationFloor;
    private int direction;
    
    public Person(int id, int waitTime, Integer destFl, Integer direct){
        setID (id);
        setWaitingTime (waitTime);
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

    public int getWaitingTime () {
        return waitingTime;
    }

    public void setWaitingTime (int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getDestinationFloor () {
        return destinationFloor;
    }

    public void setDestinationFloor (int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    
}
