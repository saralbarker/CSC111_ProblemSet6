/* @file RobotRandomBehaviorSelfAvoidingPaths.java
@brief Coding a robot that randomly travels throughout a grid using methods and StdDraw (avoids traveled paths).
@author Sara Barker
@date 10/17/2018 */

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class RobotRandomBehaviorSelfAvoidingPaths {

    public static void main(String[] args) {

        //imports
        Random random = new Random();
        Scanner frodo = new Scanner(System.in);

        //variable declarations
        int N = 0;
        System.out.println("Enter grid size (N, N): "); //asking user for grid size
        N = frodo.nextInt();

        int[][] map = new int[N + 1][N + 1];
        int xOld;
        int yOld;
        int direction;
        int steps = 0;

        //calling method
        drawBoard(N);

        //start in middle
        int x = N / 2;
        int y = N / 2;

        //pen colors
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);

        //iterating loops
        while (x <= N && x > 0 && y <= N && y > 0) {

            map[x][y] = 1; //needs to be inside loop to update location --> 0 is empty, 1 is occupied

            //stopping loop if it gets stuck
            if (map[x][y + 1] == 1 && map[x][y - 1] == 1 && map[x - 1][y] == 1 && map[x + 1][y] == 1) {
                break;
            }

            //save x and y to xold/yold
            xOld = x;
            yOld = y;

            //choose a random direction
            direction = random.nextInt(4);


            //update robot location and steps
            // up
            if (direction == 0) {
                if (map[x][y + 1] == 0) {
                    y = y + 1;
                    steps++;
                }
            }
            // down
            else if (direction == 1) {
                if (map[x][y - 1] == 0) {
                    y = y - 1;
                    steps++;
                }
            }
            // right
            else if (direction == 2) {
                if (map[x + 1][y] == 0) {
                    x = x + 1;
                    steps++;
                }
            }
            // left
            else if (direction == 3) {
                if (map[x - 1][y] == 0) {
                    x = x - 1;
                    steps++;
                }
            }

            //drawing line segment
            drawLineSegment(xOld, yOld, x, y);
        }

            //drawing circle
            StdDraw.circle(x, y, 0.15);

            System.out.println("The robot took " + steps + " steps!");

        } //end of main

            //drawing the board
        public static void drawBoard ( int N){

            StdDraw.setPenColor(Color.RED);
            StdDraw.setScale(-0.1, N + 0.1);
            StdDraw.setScale(-0.2, N + 0.2);
            StdDraw.setPenColor(StdDraw.BLACK);

            StdDraw.square(N / 2.0, N / 2.0, N / 2.0);

            //initializations and loops
            int x = 0;
            int y = 0;
            int i;

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

        // method to draw the line segment
        public static void drawLineSegment ( int xOld, int yOld, int x, int y){

            StdDraw.line(xOld, yOld, x, y);
            StdDraw.pause(5);

        }
    }

