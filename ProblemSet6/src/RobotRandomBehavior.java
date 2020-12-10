/* @file RobotRandomBehavior.java
@brief Coding a robot that randomly travels throughout a grid using methods and StdDraw.
@author Sara Barker
@date 10/17/2018 */

import java.awt.*;
import java.util.Random;
import java.util.Scanner;


public class RobotRandomBehavior {
    public static void main(String[] args) {

        //imports
        Random random = new Random();
        Scanner frodo = new Scanner(System.in);

        //declaration for N, now ready for calculations and assignments to x and y
        int N;

        //ask user
        System.out.println("Enter grid size (N, N): ");
        N = frodo.nextInt();

        //calling method
        drawBoard(N);

        int steps = 0;

        int x = N / 2;
        int y = N / 2;

        int xOld = 0;
        int yOld = 0;

        int direction;

        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.01);


        while (x <= N && x > 0 && y <= N && y > 0) {

            direction = random.nextInt(4);

            // save x and y to xold/yold
            xOld = x;
            yOld = y;

            // choose a random direction

            //update robot location and steps
            // up
            if (direction == 0) {
                y = y + 1;
                steps++;
            }
            // down
            else if (direction == 1) {
                y = y - 1;
                steps++;
            }
            // right
            else if (direction == 2) {
                x = x + 1;
                steps++;
            }
            // left
            else if (direction == 3) {
                x = x - 1;
                steps++;
            }

            drawLineSegment(xOld, yOld, x, y);

        }

        StdDraw.circle(x, y, 0.15);

        System.out.println("The robot took " + steps + " steps!");
    }

    //creating board
    public static void drawBoard(int N) {
        StdDraw.setPenColor(Color.red);
        StdDraw.setScale(-0.1, N + 0.1);
        StdDraw.setScale(-0.2, N + 0.2);
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.square(N/2.0, N/2.0, N/2.0);

        int x = 0;
        int y = 0;
        int i;

        //iterating loops
        for (i = 0; i < N; i++) {

            StdDraw.line(x + 1, y, x + 1, N);
            x++;

        }

        x = 0;

        for (i = 0; i < N; i++) {

            StdDraw.line(x, y + 1, N, y + 1);
            y++;

        }

    }

    public static void drawLineSegment(int xOld, int yOld, int x, int y) {
        // drawing the line segment
        StdDraw.line(xOld, yOld, x, y);
        StdDraw.pause(5);
    }

// for second program
    // need to place a marker in the previously crossed intersections so the robot knows whether to continue it or not
    // store a number on each intersection
    // as soon as robot is placed, change marker to say it has been there
    // still need x,y,xold,yold
    // need an array of size N+1 N+1 -- int[][] map = int[N+1][N+1] (gives you a map of all 0s)
    // set map at [x][y] = 1 if the robot has been to a certain x/y location
    // is the robot stuck? use an if statement -- if (robot is stuck) then program ends -- check map[x][y]
    // if at least one path is availible the normal program runs

}