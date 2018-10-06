//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.PriorityQueue;
import java.util.Stack;

public class Astar extends Agent{

    public Astar(char[][] chararr) {
        this.chararr = chararr;
        //frontier = new PriorityQueue();
        maze = new Node[chararr.length][chararr[0].length];
        answer = new Stack();
        solved = false;
        stepsTaken = 0;
        nodesExpanded = 0;
    }
    
    //the method that solves the maze
    @Override
    public Node[][] solve() throws InterruptedException {
        
        //initialize node array
        create_node_arr(chararr);
        
        //find start point
        Node root = find_start_point();
        
        //making the stack
        frontier.push(root);
        root.visited = true;
        while (!solved) {
            findPath(frontier.pop());
        }
        Node ans = answer.pop();
        findParent(ans);

        return maze;
    }
}
