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
            
            //Initialize agents
            Depth depth = new Depth();
            //Breadth breadth = new Breadth();
            //Greedy greedy = new Greedy();
            //Astar astar = new Astar();
            
            //Solutions
            printBoard(board);
            char[][] depthSolved = depth.solve(board);
            //char[][] breadthSolved = breadth.solve(board);
            //char[][] greedySolved = greedy.solve(board);
            //char[][] astarSolved = astar.solve(board);
            
            //Printing
            System.out.println("Depth First Solution:");
            printBoard(depthSolved);
            //System.out.println("Breadth First Solution:");
            //printBoard(breadthSolved);
            //System.out.println("Greedy First Solution:");
            //printBoard(greedySolved);
            //System.out.println("A* Solution:");
            //printBoard(astarSolved);
        } catch (IOException ex) {
            Logger.getLogger(AIPacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
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
        
        try(BufferedReader b = new BufferedReader(new FileReader(f))) {
            int i = 0;
            while (i<boardY) {
                board[i] = b.readLine().toCharArray();
                i++;
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return board;
    }
}
