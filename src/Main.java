import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author John Berkley
 * CPP Class: CS 311
 * Date Created: Nov 07, 2017
 *
 * Program was written in Intellij IDEA using JDK 9
 * Modify keywordScanner to point to wherever the text file containing the keywords text is located
 * Modify programScanner to point to wherever the text file containing the program text is located
 * If the program text is too long for the current arrays, modify MAX_TRANSITION in DynamicFSA.java
 *  to handle a larger number of characters (default is set to 10,000)
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keywordScanner = new Scanner(new File("/Users/johnberkley/IdeaProjects/CS311_Project2/src/Keywords"));
        DynamicFSA dfsa = new DynamicFSA();

        //process each line of the keyword file
        while (keywordScanner.hasNextLine()) {
            dfsa.processLine(keywordScanner.nextLine(), true);
        }

        //process each line of the program file (includes some small formatting)
        Scanner programScanner = new Scanner(new File("/Users/johnberkley/IdeaProjects/CS311_Project2/src/JavaProgram"));
        while (programScanner.hasNextLine()) {
            String nextLine = programScanner.nextLine().trim();
            if (nextLine.equals("")) {
                System.out.println();
            }
            if (!nextLine.equals("") && nextLine.charAt(0) != '/' && !nextLine.trim().equals("{")) {
                dfsa.processLine(nextLine, false);
                System.out.println();
            }
        }

        //display switch and next tables
        dfsa.displayTables();
    }
}
