package com.company;

import java.util.Scanner;

public class Main {

    // public fields
    static Scanner scnr = new Scanner(System.in);

    /****************************************************************
     * getValidInteger function to validate that it is an int value *
     ****************************************************************/
    public static int getValidInteger() {

        // This keeps looping until integer input is received.
        while (!scnr.hasNextInt()) {
            scnr.nextLine();         // clear the buffer
            System.out.print("Please enter an integer! ");
        }

        return scnr.nextInt();
    }

    /*************************************************************************
     * getValidInteger to and check for a range, user with getValidInteger() *
     *************************************************************************/
    public static int getValidInteger(int min, int max) {

        int userInput = getValidInteger();

        while (userInput < min || userInput > max) {
            System.out.print("Please enter a number within range! ");
            userInput = getValidInteger();
        }

        return userInput;
    }

    /*************************************************************************
     * This method asks the user if they want to continue the program or not *
     *************************************************************************/
    public static boolean askUserToContinue() {
        // Variable declarations
        String userInput = "";                  // User input string
        Scanner scan = new Scanner(System.in);  // Scanner object for user input

        // Prompt user to enter yes or no
        System.out.print("Do you want to continue? (y/n): ");
        userInput = scan.nextLine();
        System.out.println("");

        // Validate whether user input is ok, and continue asking until right
        while (!userInput.equalsIgnoreCase("y") &&
                !userInput.equalsIgnoreCase("yes") &&
                !userInput.equalsIgnoreCase("n") &&
                !userInput.equalsIgnoreCase("no")) {
            System.out.println("That is not a valid input.  Please try again. ");
            System.out.print("Do you want to continue? (y/n): ");
            userInput = scan.nextLine();
            System.out.println("");
        }

        // Return true if user says yes, and false if user says no
        if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }


    /************************************************************************************************
     * This method prompts the user to input the grades, and returns an array of the counts
     * @return
     ************************************************************************************************/
    public static int[] getUserGradesInput() {
        // Variable declarations
        int[] gradesRangeCount = new int[10];     // This is the array that will store the count of each range of grades
        int userInput;                            // holds the user's input
        int countInput = 0;                       // holds the count of how many grades have been entered

        // Prompt user
        System.out.println("Please enter grades in the range 0-100.  (-1 to stop entering grades)");

        // Keep asking for input until -1 is entered
        while (true) {
            //Prompt user for a grade, only accept valid range of -1 through 100
            System.out.print("Enter grade #" + ++countInput + ": ");
            userInput = getValidInteger(-1,100);

            // If -1, break
            if (userInput == -1){
                break;
            }

            // increase count on the range for the user input
            // 100 is an exception since 100/10 would be 10
            // and index 10 should not be accessed
            if (userInput == 100) {
                gradesRangeCount[9]++;
            }
            else {
                gradesRangeCount[userInput/10]++;
            }
        }

        // Return the array for grades
        return gradesRangeCount;
    }


    /********************************************************************************************
     * This method will print the vertical histogram when passed the array parameter
     * @param gradesRangeCount
     ********************************************************************************************/
    public static void printVerticalHistogram(int[] gradesRangeCount) {

        // Heading
        System.out.println("===== Vertical Histogram =====");

        // Outer for loop takes care of each row
        for (int i = 0; i < gradesRangeCount.length; i++) {

            // Print the vertical column label, different if i is last i.e.
            if (i == (gradesRangeCount.length - 1)) {
                System.out.printf("%2d -%3d: ", i * 10, i * 10 + 10);
            }
            else {
                System.out.printf("%2d -%3d: ", i * 10, i * 10 + 9);
            }

            // Print the asterisk for the number of grades entered
            for (int j = 0; j < gradesRangeCount[i]; j++) {
                System.out.print("*");
            }

            // new row
            System.out.println();

        }

        return;
    }


    /****************************************************************************
     * This method will return the integer max value of an integer array
     * @param array
     * @return
     ****************************************************************************/
    public static int getMaxValueArray(int[] array) {
        // Variable declarations
        int maxValue = 0;      // This will hold the maxValue of the array

        // Get the max value of the array
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // return the max value
        return maxValue;
    }



    public static void printHorizontalHistogram(int[] gradesRangeCount) {
        // Variable declarations
        int numRowsAsterisk = getMaxValueArray(gradesRangeCount);  // This will return the largest value in the array

        // Heading
        System.out.println("===== Horizontal Histogram =====");

        // numRowsAsterisk keeps track of the current row where asterisk should be printed
        // Loop while numRowAsterisk is greater than 0
        while (numRowsAsterisk > 0) {
            // This loop will iterate through the array, per row
            for (int i = 0; i < gradesRangeCount.length; i++) {

                // If, the value in the array matches numRowsAsterisk,
                //      print asterisk
                // else
                //      print blanks
                if (gradesRangeCount[i] == numRowsAsterisk) {
                    System.out.print("  *   ");
                    // decrease the number for that element, so it matches the numRowAsterisk the next time through
                    gradesRangeCount[i]--;
                }
                else {
                    System.out.print("      ");
                }
            }

            // To next row
            System.out.println();

            // decrease numRowAsterisk for the next row
            numRowsAsterisk--;
        }

        // Print the horizontal axis label
        for (int i = 0; i < gradesRangeCount.length ; i++) {

            // condition different when
            // (1) printing for range 90-100
            // (2) printing for 0-9
            if (i == (gradesRangeCount.length - 1)) {
                System.out.printf("%-6s", (i * 10) + "-" + (i * 10 + 10));
            }
            else if (i == 0) {
                System.out.printf("%-6s", " " + (i * 10) + "-" + (i * 10 + 9));
            }
            else {
                System.out.printf("%-6s", (i * 10) + "-" + (i * 10 + 9));
            }
        }
        System.out.println();

        return;
    }


    public static void main(String[] args) {
	    // Variable declarations
        int[] gradesRangeCount = new int[10];     // This is the array that will store the count of each range of grades

        // Welcome message
        System.out.println("Welcome to the GradesHistogram program!\n");

        // Continue the program until user decides to quit
        while (true) {
            // Get the user inputs
            gradesRangeCount = getUserGradesInput();
            System.out.println();

            // Print the vertical histogram
            printVerticalHistogram(gradesRangeCount);
            System.out.println();

            // Print the horizontal histogram
            printHorizontalHistogram(gradesRangeCount);
            System.out.println();

            // Ask user if they want to continue or not
            if (!askUserToContinue()) {
                break;
            }
        }

        // Exit message
        System.out.println("Goodbye!");

    }
}
