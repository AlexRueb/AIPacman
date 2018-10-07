//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;

public class Depth extends UninformedAgent{

    public Depth(char[][] chararr) {
        this.chararr = chararr;
        frontier = new QueueOrStack(false);
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
        root = find_start_point();
        
        //making the stack
        frontier.push(root);
        root.visited = true;
        while (!solved) {
            find_path(frontier.pop());
            //print_board();
        }
        Node ans = answer.pop();
        findParent(ans);

        return maze;
    }
}
