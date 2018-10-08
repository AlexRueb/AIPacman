//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
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
    @Override
    public void findParent(Node target){
        if(target.parent != null){
            answer.push(target.parent);
            pathCost++;
            target.id = '.';
            findParent(target.parent);
        }
    }
    
    public int path_cost(Node n){
        pathCost = 0;
        findParent(n);
        return pathCost;
    }
    
    //the method that solves the maze
    @Override
    public Node[][] solve() throws InterruptedException {
        
        //initialize frontier, with (heuristic+step_cost(n,root)) minimized
        PriorityQueue<Node> frontier = new PriorityQueue<>(4,new FrontierAStarOrder());
        
        //initialize node array
        create_node_arr(chararr);
        
        //goal formulation
        Node goal = formulate_goal();
        
        //find start point
        root = find_start_point();
        
        //determine which node to expand using heuristic function
        frontier.add(root);
        root.visited = true;
        while (!frontier.isEmpty()) {
            nodesExpanded++;
            //always expand node that has lowest manhattan score to goal
            Node current = frontier.poll();
            if(current.id == '*'){
                answer.push(current);
                super.findParent(answer.pop());
                stepsTaken = pathCost;
                System.out.println("Found the path!");
                return maze;
            }
            for(Node n : current.neighbors){
                //print_board();
                if(n == null) continue;
                
                int tentative_score = path_cost(current);

                //If neighbor of current node has been visited
                if(n.visited){
                    frontier.add(n);
                } 
                else if(tentative_score >= n.distance_to_goal){
                   continue;
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
