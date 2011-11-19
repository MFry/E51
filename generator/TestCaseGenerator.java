import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TestCaseGenerator {

    /**
     * Program that generates several test cases
     * for the elevator algorithm
     * 
     * @author Joseph Del Prete
     */
    public static void main (String[] args) {
        // do not necessarily need main
        // serves mainly as a caller for other methods
        normalTestCase("output.txt");
    }
    
    public static void normalTestCase(String outputFile)
    {
        File file = new File(outputFile);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println ("File not found");
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    }

}
