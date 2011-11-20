public class JPerson {
    int time; // 0000 to 2400
    int direction; // 1 for up, 0 for down
    int initialFloor; // 0 is ground floor
    int destinationFloor; // 0 is ground floor

    JPerson(int time_1, int direction_1, int initFloor_1, int destFloor_1) {
        time = time_1;
        direction = direction_1;
        initialFloor = initFloor_1;
        destinationFloor = destFloor_1;
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
}
