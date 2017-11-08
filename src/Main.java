import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author John Berkley
 * CPP Class: CS 311
 * Date Created: Nov 07, 2017
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /*Scanner keywordScanner = new Scanner(new File("/Users/johnberkley/IdeaProjects/CS311_Project2/src/Keywords"));
        DynamicFSA dfsa = new DynamicFSA();

        while (keywordScanner.hasNextLine()) {
            dfsa.processLine(keywordScanner.nextLine(), true);
        }*/

        String newInput = "print(Arr[0][2]));  a12";
        newInput = newInput.replaceAll("[^a-zA-Z0-9_$][0-9]*", " ");
        String[] line = newInput.split("\\s+");
        System.out.println(Arrays.toString(line));
    }
}
