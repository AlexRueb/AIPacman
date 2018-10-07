//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
import java.lang.Math;

public class Greedy extends InformedAgent{

    public Greedy(char[][] chararr) {
        this.chararr = chararr;
        frontier = new QueueOrStack(false);
        maze = new Node[chararr.length][chararr[0].length];
        answer = new Stack();
        solved = false;
        stepsTaken = 0;
        nodesExpanded = 0;
    }
    
    //returns node from frontier with lowest manhattan distance to goal
    private Node evaluation_function(){
        Node goal = formulate_goal();
        Node result = new Node();
        if(frontier.peek() == null){
            return null;
        } else {
            result = frontier.pop();
            for(Node n : frontier){
                if(manhattan_distance(n, goal) < manhattan_distance(result, goal)){
                    result = n;
                }
            }
        }
        return result;
    }
    
    //the method that solves the maze
    @Override
    public Node[][] solve() throws InterruptedException {
        
        //initialize node array
        create_node_arr(chararr);
        
        //find start point
        Node root = find_start_point();
        
        //determine which node to expand using heuristic function
        frontier.push(root);
        root.visited = true;
        boolean stuck = false;
        while (!solved) {
            
            //print_board();
        }
        return maze;
    }
}
