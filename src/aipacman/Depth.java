//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;

public class Depth {

    public Depth() {
    }

    Stack<Node> frontier = new Stack();
    Stack<Node> answer = new Stack();
    public char[][] maze;
    public boolean solved = false;

    //only increments if a space gets changed from ' ' to '.'
    public int stepsTaken = 0;

    //only increments findNeighbors is ran, which is the expansion method
    public int nodesExpanded = 0;

    public Node[][] nodeArr;

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
        nodeArr = new Node[y][x];

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
        Node root = new Node();

        //creates array of nodes
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                nodeArr[i][j] = new Node();
                nodeArr[i][j].id = maze[i][j];
                nodeArr[i][j].xCord = j;
                nodeArr[i][j].yCord = i;
                if (nodeArr[i][j].id == 'P') {
                    root = nodeArr[i][j];
                }
            }
        }

        //find all neighbors of all nodes
        for (int i = 1; i < y - 1; i++) {
            for (int j = 1; j < x - 1; j++) {
                if ((nodeArr[i - 1][j].id != '%')) {
                    nodeArr[i][j].addNeighbor(nodeArr[i - 1][j]);
                }
                if ((nodeArr[i][j - 1].id != '%')) {
                    nodeArr[i][j].addNeighbor(nodeArr[i][j - 1]);
                }
                if ((nodeArr[i + 1][j].id != '%')) {
                    nodeArr[i][j].addNeighbor(nodeArr[i + 1][j]);
                }
                if ((nodeArr[i][j + 1].id != '%')) {
                    nodeArr[i][j].addNeighbor(nodeArr[i][j + 1]);
                }
            }
        }

        System.out.println("Starting at: X = " + startX + " and Y = " + startY);

        //making the stack
        frontier.push(root);
        root.visited = true;
        while (!solved) {

            findPath(frontier.pop());
        }
        Node ans = answer.pop();
        findParent(ans);

        System.out.println("Steps taken: " + stepsTaken);
        System.out.println("Nodes expanded: " + nodesExpanded);

        return maze;
    }

    public void findParent(Node target) throws InterruptedException {
        if (target.parent != null) {
            answer.push(target.parent);
            if (target.id != '*') {
                maze[target.yCord][target.xCord] = '.';
                stepsTaken++;
            }
            findParent(target.parent);
        }
    }

    public void findPath(Node target) {
        int x = target.xCord;
        int y = target.yCord;
        //maze[y][x] = '.';
        nodesExpanded++;
        for (int i = 0; i < target.spot; i++) {
            target.visited = true;
            if (!target.neighbors[i].visited) {
                if (target.neighbors[i].id == '*') {
                    solved = true;
                    target.neighbors[i].parent = target;
                    answer.push(target.neighbors[i]);
                    return;
                }
                target.neighbors[i].parent = target;
                target.neighbors[i].visited = true;
                frontier.push(target.neighbors[i]);

            } else {

            }
        }
    }
}
