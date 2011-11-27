
public class Person {

    private int ID;
    private int waitingTime;
    private int removeTime;
    private int destinationFloor;
    private int direction;
    public static int UP = 1;
    public static int STATIC = 0;
    public static int DOWN = -1;
    /**
     * Person constructor
     * @param id
     * @param waitTime
     * @param destFl
     * @param direct
     */
    public Person(int id, int waitTime, int removeTime, Integer destFl, Integer direct){
        setID (id);
        setWaitingTime (waitTime);
        setRemoveTime (removeTime);
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

    public int getRemoveTime () {
        return removeTime;
    }

    public void setRemoveTime (int removeTime) {
        this.removeTime = removeTime;
    }

    
}
