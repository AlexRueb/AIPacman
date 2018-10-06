/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import *;

/**
 *
 * @author Alex and Dill
 *
 * AIPacman imports a maze from a text file into a 2D char array, then passes it
 * to the various informed and uninformed agents. The start point of each maze
 * is represented by a P, while the end point is represented by a *. Each agent
 * solves the maze in a different way then returns a new 2d char array which
 * represents the solved maze.
 *
 */
public class AIPacman {

    /**
     * @param args {String maze, String agent} maze: the file name of the maze
     * to be solved agent: the name of the agent to implement
     */
    //InterruptedException is for debugging purposes only
    //REMOVE BEFORE FINAL SUBMISSION
    public static void main(String[] args) throws InterruptedException {
        try {
            //Initialize Board
            char[][] board = import_maze("src/aipacman/medium maze.txt");
            
            //Depth First
            Depth depth = new Depth(board);
            Node[][] depthSolved = depth.solve();
            
            //Breadth First
            Breadth breadth = new Breadth(board);
            Node[][] breadthSolved = breadth.solve();

            //A* 
            //Astar astar = new Astar(board);
            //Node[][] astarSolved = astar.solve();
            
            //Greedy Best First
            //Greedy greedy = new Greedy(board);
            //Node[][] greedySolved = greedy.solve();

            //Printing
            System.out.println("Depth First Solution, starting from right going counter-clockwise:");
            System.out.println("Starting at: X = " + depth.startX + " and Y = " + depth.startY);
            System.out.println("Steps taken: " + depth.stepsTaken);
            System.out.println("Nodes expanded: " + depth.nodesExpanded);
            printBoard(depthSolved);
            System.out.println("Breadth First Solution:");
            System.out.println("Starting at: X = " + breadth.startX + " and Y = " + breadth.startY);
            System.out.println("Steps taken: " + breadth.stepsTaken);
            System.out.println("Nodes expanded: " + breadth.nodesExpanded);
            printBoard(breadthSolved);
            //System.out.println("Greedy First Solution:");
            //printBoard(greedySolved);
            //System.out.println("A* Solution:");
            //printBoard(astarSolved);
        } catch (IOException ex) {
            Logger.getLogger(AIPacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printBoard(Node[][] board) {
        for(Node[] row : board){
            for(Node n : row){
                System.out.print(n.id);
            }
            System.out.println();
        }
    }

    public static char[][] import_maze(String f) throws IOException {
        int boardX = 100;
        int boardY = 100;
        //Im too lazy to make a dynamically sized 2D array
        switch (f) {
            case "src/aipacman/open maze.txt":
                boardX = 20;
                boardY = 20;
                break;
            case "src/aipacman/medium maze.txt":
                boardX = 61;
                boardY = 23;
                break;
            case "src/aipacman/large maze.txt":
                boardX = 81;
                boardY = 31;
                break;
            default:
                System.out.println("Error: we do not support this maze!");
        }
        char[][] board = new char[boardY][boardX];

        try (BufferedReader b = new BufferedReader(new FileReader(f))) {
            int i = 0;
            while (i < boardY) {
                board[i] = b.readLine().toCharArray();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return board;

    }
}
