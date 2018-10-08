package aipacman;
import java.util.Comparator;

/**
 *
 * FrontierAStarOrder contains the logic for sorting the priority 
 * queue (frontier) in A* Search.
 * 
 * @author Alex Rueb and Dillon Tice
 * 
 */
public class FrontierAStarOrder implements Comparator<Node>{
    @Override
    public int compare(Node n1, Node n2){
        return (n1.estimated_cost - n2.estimated_cost);
    }
}
