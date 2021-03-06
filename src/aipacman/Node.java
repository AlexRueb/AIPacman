package aipacman;

/**
 *
 * Node class is a data structure that holds the necessary information for
 * map traversal such as node parents, coordinates, and heuristic/evaluation
 * values.
 * 
 * @author Alex Rueb and Dillon Tice
 * 
 */
public class Node{

    public Node[] neighbors = new Node[4];
    public int xCord;
    public int yCord;
    public char id;
    public int spot = 0;
    public boolean visited = false;
    public boolean inSolution = false;
    public int distance_to_start;
    public int distance_to_goal = 9999999;
    public int estimated_cost = 9999999;
    public Node parent;
    
    public Node(){
        xCord = 0;
        yCord = 0;
    }
    
    public Node(int x, int y){
        xCord = x;
        yCord = y;
    }

    public void addNeighbor(Node target){
        neighbors[spot] = target;
        spot += 1;
    }
    public Node addNeighbor(char target, int x, int y){
        neighbors[spot] = new Node();
        neighbors[spot].id = target;
        neighbors[spot].xCord = x;
        neighbors[spot].yCord = y;
        spot += 1;
        return neighbors[spot-1];
    }
    @Override
    public String toString(){
        return "distance to goal: " + distance_to_goal;
    }
}
