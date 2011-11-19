import java.util.Comparator;
import java.util.PriorityQueue;

public class CustomQueue{

    /**
     * @param args
     */
    public static void main (String[] args) {
        FloorComparatorDown down = new FloorComparatorDown();
        //FloorComparatorUp up = new FloorComparatorUp();
        
        PriorityQueue<Integer> floors = new PriorityQueue<Integer> (100,down);
        floors.add (10);
        floors.add(2);
        floors.add(4);
        floors.add(5);
        floors.add(8);
        floors.add (1);
        while(floors.size ()>0){
            System.out.println(floors.remove ());
        }
        };

    }

class FloorComparatorDown implements Comparator<Integer>{

    @Override
    public int compare (Integer o1, Integer o2) {
        if(o1.compareTo (o2) == 1){
            return 1;
        }
        if(o1.compareTo (o2) == -1){
            return -1;
        }
        return 0;
    }
    
}

class FloorComparatorUp implements Comparator<Integer>{

    @Override
    public int compare (Integer o1, Integer o2) {
        if(o1.compareTo (o2) == 1){
            return -1;
        }
        if(o1.compareTo (o2) == -1){
            return 1;
        }
        return 0;
    }
    
}

