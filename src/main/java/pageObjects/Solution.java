package pageObjects;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String myString = "";
        Scanner scanner = new Scanner(System.in);
        int myInt = scanner.nextInt();
        double myD = scanner.nextDouble();

        scanner.nextLine();
        myString = scanner.nextLine();

        scanner.close();

        System.out.println("String: " + myString);
        System.out.println("Double: " + myD);
        System.out.println("Int: " + myInt);
    }
}
