import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    List<List<List<Entry>>> entries;
    int numOfFours, numOfFives, numOfSixes;

    public void computeSum(int fours, int fives, int sixes) {
        numOfFours = fours;
        numOfFives = fives;
        numOfSixes = sixes;

        initEntries();
        System.out.println(entries.toString());
        
    }


    private class Entry {
        public long sum;
        public int numberOfPerms;


        public String toString() {
            return "<" + sum + ", " + numberOfPerms + ">";
        }
    }


    private void initEntries() {
        entries = new ArrayList<List<List<Entry>>>();
        for (int i = 0; i <= numOfFours; i++) {
            List<List<Entry>> fives = new ArrayList<List<Entry>>();
            for (int j = 0; j <= numOfFives; j++) { 
                List<Entry> sixes = new ArrayList<Entry>();
                for (int k = 0; k <= numOfSixes; k++) {
                    Entry entry = new Entry();
                    entry.sum = 0;
                    entry.numberOfPerms = 0;
                    sixes.add(entry);
                }
                fives.add(sixes);
            }
            entries.add(fives);
        }

    }


    public static void main(String[] args) {
        String input;
        int numOfFours = 0;
        int numOfFives = 0;
        int numOfSixes = 0;
        Solution s = new Solution();

        Scanner scanner = new Scanner(System.in);
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Error reading input.");
            return;
        }

        String[] splitInput = input.split(" ");
        
        if (splitInput.length != 3) {
            System.err.println("Invalid input: wrong number of arguments.");
            return;
        }

        try {
            numOfFours = Integer.parseInt(splitInput[0]);
            numOfFives = Integer.parseInt(splitInput[1]);
            numOfSixes = Integer.parseInt(splitInput[2]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: wrong input type.");
            return;
        }

        s.computeSum(numOfFours, numOfFives, numOfSixes);

    }
}
