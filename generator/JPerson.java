import java.util.Comparator;

public class JPerson implements Comparator, Comparable {
    int time; // 0000 to 2400
    int direction; // 1 for up, 0 for down
    int initialFloor; // 0 is ground floor
    int destinationFloor; // 0 is ground floor
    String createOrMove;

    JPerson(int time_1, int direction_1, int initFloor_1, int destFloor_1, String createOrMove_1) {
        time = time_1;
        direction = direction_1;
        initialFloor = initFloor_1;
        destinationFloor = destFloor_1;
        createOrMove = createOrMove_1;
    }

    public String getCreateOrMove () {
        return createOrMove;
    }

    public void setCreateOrMove (String createOrMove) {
        this.createOrMove = createOrMove;
    }

    public int getTime () {
        return time;
    }

    public void setTime (int time) {
        this.time = time;
    }

    public int getDirection () {
        return direction;
    }

    public void setDirection (int direction) {
        this.direction = direction;
    }

    public int getInitialFloor () {
        return initialFloor;
    }
    
    public int getDestFloor () {
        return destinationFloor;
    }

    public void setInitialFloor (int floor) {
        this.initialFloor = floor;
    }
    
    public void setDestFloor (int floor) {
        this.destinationFloor = floor;
    }
    
    public String toString()
    {
        String s = "";
        s += "Time: " + this.time + " Direction: " + this.direction + " InitFloor: " + this.initialFloor + " DestFloor: " + this.destinationFloor; 
        return s;
    }

    @Override
    public int compare (Object arg0, Object arg1) {
        JPerson person1 = (JPerson) arg0;
        JPerson person2 = (JPerson) arg1;
        if (person1.time > person2.time)
        {
            return 1;
        } else if (person1.time < person2.time)
        {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo (Object o) {
        JPerson person2 = (JPerson) o;
        if (this.time > person2.time)
        {
            return 1;
        } else if (this.time < person2.time)
        {
            return -1;
        } else {
            return 0;
        }
    }
}
