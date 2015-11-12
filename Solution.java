import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private final int MODULO = (new Double(Math.pow(10, 9))).intValue() + 7;
    
    List<List<List<Entry>>> entries;
    int numOfFours, numOfFives, numOfSixes;

    public void computeSum(int fours, int fives, int sixes) {
        numOfFours = fours;
        numOfFives = fives;
        numOfSixes = sixes;

        initEntries();
        generateEntries();
        findSum();

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
       
        if (0 < numOfFours) {
            entries.get(1).get(0).get(0).setSum(4);
            entries.get(1).get(0).get(0).setNumberOfPerms(1);
        }

        if (0 < numOfFives) {
            entries.get(0).get(1).get(0).setSum(5);
            entries.get(0).get(1).get(0).setNumberOfPerms(1);
        }

        if (0 < numOfSixes) {
            entries.get(0).get(0).get(1).setSum(6);
            entries.get(0).get(0).get(1).setNumberOfPerms(1);
        }

    }


    private void generateEntries() {
        for (int i = 0; i <= numOfFours; i++) {
            for (int j = 0; j <= numOfFives; j++) {
                for (int k = 0; k <= numOfSixes; k++) {
                    if (i + j + k <= 1) {
                        continue;
                    }

                    long sum = 0;
                    long numberOfPerms = 0;
                    int multiplier = (new Double(Math.pow(10, i + j + k - 1))).intValue() % MODULO;

                    if (0 < i) {
                        Entry entryFour = entries.get(i - 1).get(j).get(k);
                        sum += (4 * multiplier * entryFour.getNumberOfPerms()) % MODULO
                            + entryFour.getSum() % MODULO;
                        numberOfPerms += entryFour.getNumberOfPerms() % MODULO;
                    }

                    if (0 < j) {
                        Entry entryFive = entries.get(i).get(j - 1).get(k);
                        sum += ((5 * multiplier % MODULO) * entryFive.getNumberOfPerms()) % MODULO
                            + entryFive.getSum() % MODULO;
                        numberOfPerms += entryFive.getNumberOfPerms() % MODULO;
                    }

                    if (0 < k) {
                        Entry entrySix = entries.get(i).get(j).get(k - 1);
                        sum += ((6 * multiplier % MODULO) * entrySix.getNumberOfPerms()) % MODULO
                            + entrySix.getSum() % MODULO;
                        numberOfPerms += entrySix.getNumberOfPerms() % MODULO;
                    }

                    entries.get(i).get(j).get(k).setSum(sum % MODULO);
                    entries.get(i).get(j).get(k).setNumberOfPerms(numberOfPerms % MODULO);

                }
            }
        }
    }

    
    private void findSum() {
        long sum = 0;
        for (int i = 0; i <= numOfFours; i++) {
            for (int j = 0; j <= numOfFives; j++) {
                for (int k = 0; k <= numOfSixes; k++) {
                    sum += entries.get(i).get(j).get(k).getSum() % MODULO;
                }
            }
        }


        System.out.println(sum % MODULO);

    }


    private class Entry {
        private long sum;
        private long numberOfPerms;

        public long getSum() {
            return sum;
        }

        public void setSum(long sum) {
            this.sum = sum;
        }

        public long getNumberOfPerms() {
            return numberOfPerms;
        }

        public void setNumberOfPerms (long numberOfPerms) {
            this.numberOfPerms = numberOfPerms;
        }

        public String toString() {
            return "<" + sum + ", " + numberOfPerms + ">";
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
