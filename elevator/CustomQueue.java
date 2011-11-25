import java.util.Comparator;
import java.util.PriorityQueue;

public class CustomQueue {

   /**
    * @param args
    */
   // TODO reword up and down into ascending and descending
   // TODO comment the code
   // TODO general Refactoring for readability and stability
   public static void main(String[] args) {

      FloorComparatorAscending up = new FloorComparatorAscending();
      FloorComparatorDescending down = new FloorComparatorDescending();

      PriorityQueue<Integer> floors = new PriorityQueue<Integer>(100, up);
      floors.add(10);
      floors.add(2);
      floors.add(4);
      floors.add(5);
      floors.add(8);
      floors.add(1);
      while (floors.size() > 0) {
         System.out.println(floors.remove());
      }
   };

}

class ElevatorComparatorAscending implements Comparator<Elevator> {

   @Override
   public int compare(Elevator o1, Elevator o2) {
      if (o1.compareTo(o2) == 1) {
         return 1;
      }
      if (o1.compareTo(o2) == -1) {
         return -1;
      }
      return 0;
   }
}

class ElevatorComparatorDescending implements Comparator<Elevator> {

   @Override
   public int compare(Elevator o1, Elevator o2) {
      if (o1.compareTo(o2) == 1) {
         return -1;
      }
      if (o1.compareTo(o2) == -1) {
         return 1;
      }
      return 0;
   }
}


class FloorComparatorAscending implements Comparator<Integer> {

   @Override
   public int compare(Integer o1, Integer o2) {
      if (o1.compareTo(o2) == 1) {
         return 1;
      }
      if (o1.compareTo(o2) == -1) {
         return -1;
      }
      return 0;
   }

}

class FloorComparatorDescending implements Comparator<Integer> {

   @Override
   public int compare(Integer o1, Integer o2) {
      if (o1.compareTo(o2) == 1) {
         return -1;
      }
      if (o1.compareTo(o2) == -1) {
         return 1;
      }
      return 0;
   }

}
