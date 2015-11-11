import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    ArrayList<Integer>[][][] perms;

    public static void computeSum(int numOfFours, int numOfFives, int numOfSixes) {
        



        System.out.println(new Double(Math.pow(10, 4)).intValue());
    }


    public static void main(String[] args) {
        String input;
        int numOfFours = 0;
        int numOfFives = 0;
        int numOfSixes = 0;

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

        Solution.computeSum(numOfFours, numOfFives, numOfSixes);
        
    }
}
