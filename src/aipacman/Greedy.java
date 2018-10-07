//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
import java.util.PriorityQueue;

public class Greedy extends InformedAgent{

    public Greedy(char[][] chararr) {
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
        PriorityQueue<Node> frontier = new PriorityQueue<>(4,new FrontierOrder());
        
        //initialize node array
        create_node_arr(chararr);
        
        //goal formulation
        Node goal = formulate_goal();
        
        //find start point
        root = find_start_point();
        
        //determine which node to expand using heuristic function
        frontier.add(root);
        root.visited = true;
        int step = 0;
        while (!frontier.isEmpty()) {
            print_board();
            nodesExpanded++;
            //always expand node that has lowest manhattan score to goal
            Node current = frontier.poll();
            if(current.id == '*'){
                answer.push(current);
                findParent(answer.pop(), '*');
                System.out.println("Found the path!");
                return maze;
            } else {
                for(Node n : current.neighbors){
                    //print_board();
                    if(n == null) continue;
                    
                    //If neighbor of current node has been visited
                    current.visited = true;
                    if(n.visited){continue;} 
                    else {
                       n.visited = true;
                       frontier.add(n);
                    }
                    n.parent = current;
                    n.distance_to_goal = manhattan_distance(n, goal);
                }
            }  
        }
        
        return maze;
    }
    
    public void print_frontier(PriorityQueue<Node> p){
        System.out.println(p.toArray().toString());
    }
}
