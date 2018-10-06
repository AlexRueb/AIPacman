/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

import java.util.Stack;

/**
 *
 * @author root
 */
abstract public class Agent {
    char[][] chararr;
    public Node[][] maze;
    Stack<Node> answer;
    QueueOrStack<Node> frontier;
    public int startX;
    public int startY;
    public boolean solved;
    public int stepsTaken;
    public int nodesExpanded;
    
    abstract public Node[][] solve() throws InterruptedException;
    
    
    public void findParent(Node target) throws InterruptedException {
        if (target.parent != null) {
            answer.push(target.parent);
            if (target.id != '*') {
                maze[target.yCord][target.xCord].id = '.';
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
    
    public Node find_start_point(){
        Node root = new Node();
        for(Node[] row : maze){
            for(Node n : row){
                if(n.id == 'P'){
                    root = n;
                    startX = n.xCord;
                    startY = n.yCord;
                }
            }
        }
        return root;
    }
    
    public void create_node_arr(char[][] board){
        //initializing variables
        int x, y;
        y = chararr.length;
        char[] t = chararr[1];
        x = t.length;

        //finding start point of maze
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (chararr[i][j] == 'P') {
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
                maze[i][j] = new Node();
                maze[i][j].id = chararr[i][j];
                maze[i][j].xCord = j;
                maze[i][j].yCord = i;
                if (maze[i][j].id == 'P') {
                    root = maze[i][j];
                }
            }
        }

        //find all neighbors of all nodes
        for (int i = 1; i < y - 1; i++) {
            for (int j = 1; j < x - 1; j++) {
                if ((maze[i - 1][j].id != '%')) {
                    maze[i][j].addNeighbor(maze[i - 1][j]);
                }
                if ((maze[i][j - 1].id != '%')) {
                    maze[i][j].addNeighbor(maze[i][j - 1]);
                }
                if ((maze[i + 1][j].id != '%')) {
                    maze[i][j].addNeighbor(maze[i + 1][j]);
                }
                if ((maze[i][j + 1].id != '%')) {
                    maze[i][j].addNeighbor(maze[i][j + 1]);
                }
            }
        }
    }
}
