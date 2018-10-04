//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;

public class Depth {

    public Depth() {
    }

    Stack<Node> frontier = new Stack();
    public char[][] maze;
    public boolean solved = false;
    
    //only increments if a space gets changed from ' ' to '.'
    public int stepsTaken = 0;
    
    //only increments when a node is pushed onto the stack.
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

        //making the stack
        Node root = new Node();
        root.id = 'P';
        root.xCord = startX;
        root.yCord = startY;
        frontier.push(root);
        nodesExpanded++;
        findNeighbors(root);
        //System.out.println(root.neighbors[0].xCord + " " + root.neighbors[0].yCord);

        while (!solved) {
            //Thread.sleep only used for debugging, to watch the maze be solved.
            //REMOVE BEFORE SUBMISSION
            //Thread.sleep(100);
            findNeighbors(frontier.pop());
        }
        System.out.println("Steps taken: " + stepsTaken);
        System.out.println("Nodes expanded: " + nodesExpanded);

        return maze;
    }

    public void findNeighbors(Node target) {
        //REMOVE PRINTBOARD CALL BELOW
        //AIPacman.printBoard(maze);
        int x = target.xCord;
        int y = target.yCord;

        if (maze[y][x] != 'P') {
            maze[y][x] = '.';
            stepsTaken++;
        }
        //down
        if (maze[y + 1][x] != '%') {
            if (maze[y + 1][x] == ' ') {
                Node current = target.addNeighbor(' ', x, y + 1);
                frontier.push(current);
                nodesExpanded++;
            } else if (maze[y + 1][x] == '*') {
                solved = true;
            }
        }

        //left
        if (maze[y][x - 1] != '%') {
            if (maze[y][x - 1] == ' ') {
                Node current = target.addNeighbor(' ', x - 1, y);
                frontier.push(current);
                nodesExpanded++;
            } else if (maze[y][x - 1] == '*') {
                solved = true;
            }
        }

        //up
        if (maze[y - 1][x] != '%') {
            if (maze[y - 1][x] == ' ') {
                Node current = target.addNeighbor(' ', x, y - 1);
                frontier.push(current);
                nodesExpanded++;
            } else if (maze[y - 1][x] == '*') {
                solved = true;
            }
        }

        //right
        if (maze[y][x + 1] != '%') {
            if (maze[y][x + 1] == ' ') {
                Node current = target.addNeighbor(' ', x + 1, y);
                frontier.push(current);
                nodesExpanded++;
            } else if (maze[y][x + 1] == '*') {
                solved = true;
            }
        }
    }

}
