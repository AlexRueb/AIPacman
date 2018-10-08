package aipacman;

import java.io.*;

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
     * 
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws IOException {
        String[] mazes = {"open maze.txt", "medium maze.txt", "large maze.txt"};
        for(String maze : mazes) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true));
            //Initialize Board
            char[][] board = import_maze("src/aipacman/"+maze);

            //Initialize Agent
            Agent agent;

            //Depth First
            agent = new Depth(board);
            Node[][] depthSolved = agent.solve();
            System.out.println("Depth First Solution, starting from right going counter-clockwise:");
            System.out.println("Starting at: X = " + agent.startX + " and Y = " + agent.startY);
            System.out.println("Steps taken: " + agent.stepsTaken);
            System.out.println("Nodes expanded: " + agent.nodesExpanded);
            System.out.println("---------------------------------------");
            printBoard(depthSolved);
            System.out.println();
            
            bw.append("Depth First Solution, starting from right going counter-clockwise:\n");
            bw.append("Starting at: X = " + agent.startX + " and Y = " + agent.startY + "\n");
            bw.append("Steps taken: " + agent.stepsTaken + "\n");
            bw.append("Nodes expanded: " + agent.nodesExpanded + "\n");
            bw.append("---------------------------------------\n");
            output_board(depthSolved, bw);
            bw.newLine();

            //Breadth First
            agent = new Breadth(board);
            Node[][] breadthSolved = agent.solve();
            System.out.println("Breadth First Solution:");
            System.out.println("Starting at: X = " + agent.startX + " and Y = " + agent.startY);
            System.out.println("Steps taken: " + agent.stepsTaken);
            System.out.println("Nodes expanded: " + agent.nodesExpanded);
            System.out.println("---------------------------------------");
            printBoard(breadthSolved);
            System.out.println();
            
            bw.append("Breadth First Solution:\n");
            bw.append("Starting at: X = " + agent.startX + " and Y = " + agent.startY + "\n");
            bw.append("Steps taken: " + agent.stepsTaken + "\n");
            bw.append("Nodes expanded: " + agent.nodesExpanded + "\n");
            bw.append("---------------------------------------\n");
            output_board(breadthSolved, bw);
            bw.newLine();

            //A* 
            agent = new Astar(board);
            Node[][] astarSolved = agent.solve();
            System.out.println("A* Solution:");
            System.out.println("Starting at: X = " + agent.startX + " and Y = " + agent.startY);
            System.out.println("Steps taken: " + agent.stepsTaken);
            System.out.println("Nodes expanded: " + agent.nodesExpanded);
            System.out.println("---------------------------------------");
            printBoard(astarSolved);
            System.out.println();
            
            bw.append("A* Solution:\n");
            bw.append("Starting at: X = " + agent.startX + " and Y = " + agent.startY + "\n");
            bw.append("Steps taken: " + agent.stepsTaken + "\n");
            bw.append("Nodes expanded: " + agent.nodesExpanded + "\n");
            bw.append("---------------------------------------\n");
            output_board(astarSolved, bw);
            bw.newLine();

            //Greedy Best First
            agent = new Greedy(board);
            Node[][] greedySolved = agent.solve();
            System.out.println("Greedy BFS Solution:");
            System.out.println("Starting at: X = " + agent.startX + " and Y = " + agent.startY);
            System.out.println("Steps taken: " + agent.stepsTaken);
            System.out.println("Nodes expanded: " + agent.nodesExpanded);
            System.out.println("---------------------------------------");
            printBoard(greedySolved);
            System.out.println();
            
            bw.append("Greedy BFS Solution:\n");
            bw.append("Starting at: X = " + agent.startX + " and Y = " + agent.startY + "\n");
            bw.append("Steps taken: " + agent.stepsTaken + "\n");
            bw.append("Nodes expanded: " + agent.nodesExpanded + "\n");
            bw.append("---------------------------------------\n");
            output_board(greedySolved, bw);
            bw.newLine();
            bw.close();
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
    
    public static void output_board(Node[][] board, BufferedWriter out) throws IOException {
        for(Node[] row : board){
            for(Node n : row){
                out.append(n.id);
            }
            out.newLine();
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
