/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
/**
 *
 * @author Alex and Dill
 */
public class AIPacman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char[][] board = import_maze("large maze.txt");
        for(char[] line : board){
          System.out.println(line);
        }
    }

    public static char[][] import_maze(String f){
      int boardX = 100;
      int boardY = 100;
      //Im too lazy to make a dynamically sized 2D array
      switch(f){
        case "open maze.txt":
          boardX = 20;
          boardY = 20;
          break;
        case "medium maze.txt":
          boardX = 61;
          boardY = 23;
          break;
        case "large maze.txt":
          boardX = 81;
          boardY = 31;
          break;
        default:
          System.out.println("Error: we do not support this maze!");
      }
      char[][] board = new char[boardY][boardX];
      File file = new File(f);
      try{
        Scanner s = new Scanner(file);
        int i = 0;
        while(s.hasNextLine()){
          board[i] = s.nextLine().toCharArray();
          i++;
        }
      } catch(FileNotFoundException e) {
        System.out.println(e);
      }
      return board;
    }
}
