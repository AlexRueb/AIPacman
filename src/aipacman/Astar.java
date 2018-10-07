//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
import java.lang.Math;

public class Astar extends InformedAgent{

    public Astar(char[][] chararr) {
        this.chararr = chararr;
        frontier = new QueueOrStack(false);
        maze = new Node[chararr.length][chararr[0].length];
        answer = new Stack();
        solved = false;
        stepsTaken = 0;
        nodesExpanded = 0;
        root = new Node();
    }
    
    //returns node from frontier with lowest manhattan distance to goal
    private Node evaluation_function(Node target){
        if(target == null) {
            return null;
        }
        
        Node result = frontier.pop();
        for(Node n : frontier){
            //if the current node has a lower manhattan+start distance than result
            if((heuristic_function(n) + stepsTaken) < (heuristic_function(result) + stepsTaken)){
                result = n;
            }
            else if((heuristic_function(n) + stepsTaken) == (heuristic_function(result) + stepsTaken)){
                result = n;
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
        root = find_start_point();
        
        //determine which node to expand using heuristic function
        frontier.push(root);
        root.visited = true;
        while (!solved) {
            nodesExpanded++;
            //expand node that has lowest evaluation score
            Node current = frontier.pop();
            Node node_to_expand = evaluation_function(current);
            current.visited = true;
            if(!node_to_expand.visited){
                if(node_to_expand.id == '*'){
                   solved = true;
                   node_to_expand.parent = current;
                   answer.push(node_to_expand);
                   break;
                }
                
            }
            node_to_expand.parent = current;
            node_to_expand.visited = true;
            frontier.push(node_to_expand);
            
            print_board();
        }
        Node ans = answer.pop();
        findParent(ans);

        return maze;
    }
}
