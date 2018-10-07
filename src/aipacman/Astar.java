//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
import java.lang.Math;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Astar extends InformedAgent{

    public Astar(char[][] chararr) {
        this.chararr = chararr;
        maze = new Node[chararr.length][chararr[0].length];
        answer = new Stack();
        solved = false;
        stepsTaken = 0;
        nodesExpanded = 0;
        root = new Node();
    }
    
    //returns node from frontier with lowest manhattan distance to goal
    private int evaluation_function(Node target){
        return manhattan_distance(target, root);
    }
    
    //the method that solves the maze
    @Override
    public Node[][] solve() throws InterruptedException {
        
        //initialize frontier, with (heuristic+step_cost(n,root)) minimized
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        
        //initialize node array
        create_node_arr(chararr);
        
        //find start point
        root = find_start_point();
        
        //determine which node to expand using heuristic function
        frontier.add(root);
        root.visited = true;
        int step = 0;
        while (!frontier.isEmpty()) {
            print_frontier(frontier);
            nodesExpanded++;
            System.out.print(nodesExpanded);
            //expand node that has lowest evaluation score
            Node current = frontier.poll();
            if(current.id == '*'){
                findParent(current);
                return maze;
            }
            
            
            current.visited = true;
            int tentative_score = 0;
            for(Node n : current.neighbors){
                if(n == null) continue;
                print_board();
                //If neighbor of current node has been visited
                if(n.visited){
                } else {
                    tentative_score = 0/*TODO path cost from start to node here*/;
                    
                    if(!frontier.contains(n)){
                        frontier.add(n);
                    }
                    else if(tentative_score >= n.distance_to_start){
                        continue;
                    }
                }
                n.parent = current;
                n.distance_to_start = tentative_score;
                n.estimated_cost = n.distance_to_start + heuristic_function(n);
            }  
        }
        return maze;
    }
    
    public void print_frontier(PriorityQueue<Node> p){
        for(Node n : p){
            System.out.println(n.id);
            System.out.println(n.distance_to_start);
            System.out.println(n.estimated_cost);
        }
    }
}
