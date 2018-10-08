//This is the initial java file for the depth-first search agent
package aipacman;

import java.util.Stack;
import java.util.PriorityQueue;

/**
 * Astar implements a heuristic function, the Manhattan distance from the current
 * node to the goal, and an addition function, the step cost function, that 
 * determines how many steps it takes from the start to reach the node.
 * 
 * The frontier is represented as a minimized priority queue that always pops
 * off the node with the smallest evaluation value (Manhattan distance from 
 * current node to goal + step cost from start to current node).
 * 
 * @author Dillon Tice and Alex Rueb
 */
public class Astar extends InformedAgent {

    /**
     *
     * @param chararr   A text representation of the maze
     */
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
    private int evaluation_function(Node target) {
        return manhattan_distance(target, root);
    }

    /**
     *
     * @param target    Start node
     * @param flag      Whether or not the reconstruction is draw on the maze
     * @return          Number of steps to start node
     */
    public int reconstruct(Node target, boolean flag) {
        int cost = 0;
        while (target.parent != null) {
            target = target.parent;
            if (target.id != 'P') {         
                cost++;
                if (flag) {
                    maze[target.yCord][target.xCord].id = '.';
                }
            }
        }
        return cost;
    }

    /**
     *
     * @param n Node to find step cost of from start node
     * @return  number of steps to reach node from start node
     */
    public int path_cost(Node n) {
        return reconstruct(n, false);
    }

    //the method that solves the maze

    /**
     *
     * @return a node map of the maze with the solution marked as dots
     * @throws java.lang.InterruptedException
     */
    @Override
    public Node[][] solve() throws InterruptedException {

        //initialize frontier, with (heuristic+step_cost(n,root)) minimized
        PriorityQueue<Node> frontier = new PriorityQueue<>(4, new FrontierAStarOrder());

        //initialize node array
        create_node_arr(chararr);

        //goal formulation
        Node goal = formulate_goal();

        //find start point
        root = find_start_point();

        //determine which node to expand using heuristic function
        frontier.add(root);

        while (!frontier.isEmpty()) {
            nodesExpanded++;
            //always expand node that has lowest manhattan score to goal
            Node current = frontier.poll();
            if (current.id == '*') {
                answer.push(current);

                stepsTaken = reconstruct(answer.pop(), true);
                System.out.println("Found the path!");
                return maze;
            }

            current.visited = true;

            for (Node n : current.neighbors) {
                //print_board();
                if (n == null) {
                    continue;
                }
                if (n.visited) {
                    continue;
                }

                //System.out.println(path_cost(current));
                int tentative_score = path_cost(current);

                //If neighbor of current node has been visited
                if (!frontier.contains(n)) {
                    frontier.add(n);
                } else if (tentative_score >= n.distance_to_goal) {
                    continue;
                }
                n.parent = current;
                n.distance_to_start = tentative_score;
                n.estimated_cost = n.distance_to_start + heuristic_function(n);
            }
        }
        return maze;
    }
}
