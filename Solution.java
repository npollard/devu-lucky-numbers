import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    List<List<List<List<Integer>>>> perms;
    int numOfFours, numOfFives, numOfSixes;

    public void computeSum(int fours, int fives, int sixes) {
        numOfFours = fours;
        numOfFives = fives;
        numOfSixes = sixes;

        if (perms == null || perms.size() < numOfFours || perms.get(0).size() < numOfFives 
                || perms.get(0).get(0).size() < numOfSixes) {
            initPerms();
        }

        generatePermutations();
        printSum();
        
    }

    private void printSum() {
        int i, j, k, l;
        int sum = 0;

        for (i = 0; i <= numOfFours; i++) {
            for (j = 0; j <= numOfFives; j++) {
                for (k = 0; k <= numOfSixes; k++) {
                    for (Integer perm : perms.get(i).get(j).get(k)) {
                        sum += perm.intValue();
                    }
                }
            }
        }
        System.out.println("\n*************");
        System.out.println(sum % ((new Double(Math.pow(10, 9)).intValue() + 7))); 

    }


    private void generatePermutations() {
        int multiplier, perm;
        int i, j, k, l, m, n;
        List<Integer> ai, aj, ak;

        System.out.println();
        for (i = 0; i <= numOfFours; i++) {
            for (j = 0; j <= numOfFives; j++) {
                for (k = 0; k <= numOfSixes; k++) {
                    System.out.print("\n[" + i + "][" + j + "][" + k + "]:\t");
                    multiplier = (new Double(Math.pow(10, i + j + k - 1))).intValue();
                    if (0 < i) {
                        System.out.print("\ti: ");
                        ai = perms.get(i - 1).get(j).get(k);
                        System.out.print(ai.toString());
                        for (l = 0; l < ai.size(); l++) {
                            perm = 4 * multiplier + ai.get(l);
                            perms.get(i).get(j).get(k).add(perm);
                        }
                    }
                    if (0 < j) {
                        System.out.print("\tj: ");
                        aj = perms.get(i).get(j - 1).get(k);
                        System.out.print(aj.toString());
                        for (m = 0; m < aj.size(); m++) {
                            perm = 5 * multiplier + aj.get(m);
                            perms.get(i).get(j).get(k).add(perm);
                        }
                    }
                    if (0 < k) {
                        System.out.print("\tk: ");
                        ak = perms.get(i).get(j).get(k - 1);
                        System.out.print(ak.toString());
                        for (n = 0; n < ak.size(); n++) {
                            perm = 6 * multiplier + ak.get(n);
                            perms.get(i).get(j).get(k).add(perm);
                        }
                    }
                }
            }
        }
        System.out.println("\n" + perms.get(numOfFours).get(numOfFives).get(numOfSixes).toString());

    }


    private void initPerms() {
        perms = new ArrayList<List<List<List<Integer>>>>();
        for (int i = 0; i <= numOfFours; i++) {
            List<List<List<Integer>>> fives = new ArrayList<List<List<Integer>>>();
            for (int j = 0; j <= numOfFives; j++) { 
                List<List<Integer>> sixes = new ArrayList<List<Integer>>();
                for (int k = 0; k <= numOfSixes; k++) {
                    List<Integer> permutations = new ArrayList<Integer>();
                    sixes.add(permutations);
                }
                fives.add(sixes);
            }
            perms.add(fives);
        }

        populatePerms();

    }


    private void populatePerms() {
        perms.get(1).get(0).get(0).add(4);
        perms.get(0).get(1).get(0).add(5);
        perms.get(0).get(0).get(1).add(6);

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
