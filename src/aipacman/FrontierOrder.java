package aipacman;
import java.util.Comparator;

/**
 *
 * Frontier Order contains the logic for sorting the priority queue (frontier)
 * in Greedy BFS. 
 * 
 * @author Dillon Tice and Alex Rueb
 * 
 */

public class FrontierOrder implements Comparator<Node>{
    @Override
    public int compare(Node n1, Node n2){
        return n1.distance_to_goal - n2.distance_to_goal;
    }
}
