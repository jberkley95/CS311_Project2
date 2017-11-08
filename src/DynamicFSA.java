import java.util.Arrays;

/**
 * @author John Berkley
 * CPP Class: CS 311
 * Date Created: Nov 07, 2017
 */
public class DynamicFSA {
    private int[] initialSymbolTable;
    private char[] nextSymbolTable;
    private int[] nextSymbolShiftTable;
    private int currSymTableSize;
    private int ptr;

    public DynamicFSA() {
        initialSymbolTable = new int[54];
        nextSymbolTable = new char[100000];
        nextSymbolShiftTable = new int[100000];
        currSymTableSize = 0;
        ptr = -1;
        Arrays.fill(initialSymbolTable, -1);
        Arrays.fill(nextSymbolShiftTable, -1);
    }



    public void processLine(String newInput, boolean isKeyword) {
        newInput = newInput.replaceAll("[^a-zA-Z0-9_$][0-9]*", " ");
        String[] line = newInput.split("\\s+");

        for (int i = 0; i < line.length; i++) {
            if (!isKeyword) {
                System.out.print(line[i]);
            }

            int currChar = 0;
            char currSymbol = line[i].charAt(currChar);
            int currSymbolIndex = getIndex(currSymbol);
            ptr = initialSymbolTable[currSymbolIndex];
            if (ptr == -1) {
                initialSymbolTable[currSymbolIndex] = currSymTableSize;
                createNew(line[i].substring(currChar + 1, line[i].length()), isKeyword);
            } else {
                currSymbol = line[i].charAt(currChar);
                boolean exit = false;
                while (!exit) {
                    if (nextSymbolTable[ptr] == currSymbol) {
                        if (currSymbol != '*' && currSymbol != '?' && currSymbol != '@') {
                            ptr++;
                            currSymbol = line[i].charAt(++currChar);
                        } else {
                            if (!isKeyword) {
                                System.out.print(currSymbol + " ");
                            }
                            exit = true;
                        }
                    } else {
                        if (nextSymbolShiftTable[ptr] != -1) {
                            ptr = nextSymbolShiftTable[ptr];
                        } else {
                            nextSymbolShiftTable[ptr] = currSymTableSize;
                            createNew(line[i].substring(currChar + 1, line[i].length()), isKeyword);
                        }
                    }

                }
            }
        }
    }

    public void displayTables() {

    }

    private void createNew(String newInput, boolean isKeyword) {
        for (int i = currSymTableSize; i < newInput.length(); i++) {
            nextSymbolTable[i] = newInput.charAt(i);
        }
        if (isKeyword) {
            nextSymbolTable[newInput.length()] = '*';
        } else {
            nextSymbolTable[newInput.length()] = '?';
        }

        currSymTableSize += newInput.length() + 1;
    }

    private int getIndex(char currSymbol) {
        if (currSymbol >= 'A' && currSymbol <= 'Z') {
            return 65 - currSymbol;
        } else if (currSymbol >= 'a' && currSymbol <= 'z') {
            return 123 - currSymbol;
        } else if (currSymbol == '_') {
            return 52;
        } else {
            return 53;
        }
    }
}
