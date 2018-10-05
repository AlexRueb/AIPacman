//This is the initial file for the breadth-first search agent
package aipacman;

import java.util.LinkedList;
import java.util.Queue;

public class Breadth {

    public Breadth() {
    }

    public Queue<Node> frontier = new LinkedList();
    public char[][] maze;
    public boolean solved = false;

    //only increments if a space gets changed from ' ' to '.'
    public int stepsTaken = 0;

    //only increments findNeighbors is ran, which is the expansion method
    public int nodesExpanded = 0;

    //the method that solves the maze
    public char[][] solve(char[][] maze) throws InterruptedException {
        //initializing variables
        this.maze = maze;
        int startX = 0;
        int startY = 0;
        int x, y;
        y = maze.length;
        char[] t = maze[1];
        x = t.length;

        //finding start point of maze
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (maze[i][j] == 'P') {
                    startX = j;
                    startY = i;
                    break;
                }
            }
        }
        System.out.println("Starting at: X = " + startX + " and Y = " + startY);

        Node root = new Node();
        root.id = 'P';
        root.xCord = startX;
        root.yCord = startY;
        frontier.add(root);

        while (!solved) {
            findNeighbors(frontier.poll());
            nodesExpanded++;
            //Thread.sleep(100);

        }
        
        System.out.println("Steps taken: " + stepsTaken);
        System.out.println("Nodes expanded: " + nodesExpanded);

        return maze;
    }

    public void findNeighbors(Node target) {
        //REMOVE PRINTBOARD CALL BELOW
        AIPacman.printBoard(maze);
        int x = target.xCord;
        int y = target.yCord;

        if (maze[y][x] != 'P') {
            maze[y][x] = '.';
            stepsTaken++;
        }
        //down
        if (maze[y + 1][x] != '%') {
            if (maze[y + 1][x] == ' ') {
                if (frontier.contains(maze[y + 1][x])) {

                } else {
                    Node current = target.addNeighbor(' ', x, y + 1);
                    frontier.add(current);
                }
            } else if (maze[y + 1][x] == '*') {
                solved = true;
            }
        }

        //left
        if (maze[y][x - 1] != '%') {
            if (maze[y][x - 1] == ' ') {
                Node current = target.addNeighbor(' ', x - 1, y);
                frontier.add(current);
            } else if (maze[y][x - 1] == '*') {
                solved = true;
            }
        }

        //up
        if (maze[y - 1][x] != '%') {
            if (maze[y - 1][x] == ' ') {
                Node current = target.addNeighbor(' ', x, y - 1);
                frontier.add(current);
            } else if (maze[y - 1][x] == '*') {
                solved = true;
            }
        }

        //right
        if (maze[y][x + 1] != '%') {
            if (maze[y][x + 1] == ' ') {
                Node current = target.addNeighbor(' ', x + 1, y);
                frontier.add(current);
            } else if (maze[y][x + 1] == '*') {
                solved = true;
            }
        }
    }
}
