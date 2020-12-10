/* @file ConnectFour.java
@brief Creating a game of Connect Four using methods and StdDraw.
@author Sara Barker
@date 10/17/2018 */

import java.awt.*;
import java.util.Arrays;

public class ConnectFour {
    public static void main(String[] args) {

        //variable declarations
        int x;
        int y;
        int plays = 0;
        final int BLUE = 1;
        final int RED = 2;
        int turn = BLUE;
        int[] columnCounter = new int[7]; // Keep track of number of discs in each column in the board
        // Also, columnCounter[x] is the ROW where the next disc should be placed
        int[][] board = new int[6][7];

        //calling method
        drawBoard();

        //notifying user
        printMessage("Blue's turn", StdDraw.BLUE);

        //BIG while loop starts here
        while (plays < 42) {
            // Was the mouse pressed?
            if (StdDraw.isMousePressed()) {
                x = (int)StdDraw.mouseX();
                y = (int)StdDraw.mouseY();

                // Was one of slots on top clicked?
                if (y >= 6 && y <= 6.5) {
                    // The value of x tells us which of the slots was clicked
                    // So, use x as index for each column in the board

                    // If there is space in column x then draw a blue or red circle
                    if (columnCounter[x] < 6) {
                        // Is it blue's turn?
                        if (turn == BLUE) {
                            board[columnCounter[x]][x] = BLUE;  // Update the board
                            StdDraw.setPenColor(StdDraw.BLUE);  // Select the blue pen
                            // Draw the disc and wait 300 milliseconds
                            StdDraw.filledCircle(x + 0.5, columnCounter[x] + 0.5, 0.5);
                            StdDraw.pause(300);
                            printMessage("Red's turn", StdDraw.RED);
                        } else {  // Turn must be red's
                            board[columnCounter[x]][x] = RED;
                            StdDraw.setPenColor(StdDraw.RED);   // Select the red pen
                            // Draw the disc and wait 300 milliseconds
                            StdDraw.filledCircle(x + 0.5, columnCounter[x] + 0.5, 0.5);
                            StdDraw.pause(300);
                            printMessage("Blue's turn", StdDraw.BLUE);
                        }

                        columnCounter[x]++;     // Update the column counter

                        // Print the board to the screen for debugging purposes
                        print2Darray(board);

                            // If turn is the winner then break the game loop
                            //declaring winner
                            if (isWinner(board, turn)) {
                                if (turn == 1) {
                                    printMessage("Blue wins!", StdDraw.BLACK);
                                }
                                else if (turn == 2) {
                                    printMessage("Red wins!", StdDraw.BLACK);
                                }
                                break;
                            }

                        // No winner yet, so change turns and increment plays
                        if (turn == RED) {
                            turn = BLUE;
                        } else {
                            turn = RED;
                        }

                        plays++; //adding iterations of plays
                    }
                }

            }
        } // end while

        // If plays == 42 then game ended in a tie
        if (plays == 42) {
            printMessage("It's a tie.", StdDraw.BLACK);
        }

        // If turn == BLUE then blue was the winner, else it was RED

    }

    // method: printMessage()
    // input: a String and a color
    // Returned value: none
    // Purpose: Prints the given string message at the top of the canvas in the given color
    public static void printMessage(String message, Color color) {
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle( 0.5, 6.5, 7, 0.25);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(3.5, 6.5, message);
    }

    // method: print2Darray()
    // input: a 2D array
    // Returned value: none
    // Purpose: prints the given 2D array in the console for debugging purposes
    public static void print2Darray(int[][] table) {

    }

    // method: isWinner()
    // Input: a 6 x 7 array of ints, an integer value
    // Returned value: true or false
    // Purpose: Returns true if there is four in a row of the given integer value.
    //   Otherwise it returns false
    public static boolean isWinner(int[][] table, int turn) {

        int i, j;

//creating loop and conditions to accomodate for horizontals, vertical, diagonal lines
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                if (j <= 3) {
                    if (table [i][j] == turn && table [i][j +1] == turn && table [i][j +2] == turn && table [i][j +3] == turn) {
                        return true;
                    }
                }
                if (i <= 2) {
                    if (table [i][j] == turn && table [i + 1][j] == turn && table [i + 2][j] == turn && table [i + 3][j] == turn) {
                        return true;
                    }
                }
                if (i <= 2 && j <= 3) {
                    if (table [i][j] == turn && table [i + 1][j + 1] == turn && table [i + 2][j + 2] == turn && table [i + 3][j + 3] == turn) {
                        return true;
                    }
                }
                if (i >= 3 && j <= 3) {
                    if (table [i][j] == turn && table [i - 1][j + 1] == turn && table [i - 2][j + 2] == turn && table [i - 3][j + 3] == turn) {
                        return true;
                    }
                }
            }
        }

        return false;   // when no winner found
    }

    // method: drawBoard()
    // Input: none
    // Returned value: none
    // Purpose: Creates a canvas scaled to 0, 7 and draws a grid of 6 x 7 circles

    //drawing board
    public static void drawBoard() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setScale(0, 7);

        int i, j;


     //drawing slots
        StdDraw.setPenColor(Color.gray);
        for (i = 0; i < 7; i++) {
            StdDraw.filledRectangle(i + 0.5, 6.25, 0.50, 0.25);
        }

        //Draw board circles, i = row and j = individual circle in each row
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 7; j++) {
                    StdDraw.circle(j + 0.5, i + 0.5, 0.5);
                }
            }
    }
}