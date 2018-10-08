package aipacman;

/**
 *
 * InformedAgent is the super class for the concrete  A* and 
 * Greedy BFS search. Contains heuristic function and goal formulation methods
 * 
 * @author Alex Rueb and Dillon Tice
 * 
 */
public abstract class InformedAgent extends Agent{
    
    /**
     *
     * @return
     */
    public Node formulate_goal(){
        Node goal = new Node();
        for(Node[] row : maze){
            for(Node n : row){
                if(n.id == '*'){
                    goal = n;
                }
            }
        }
        return goal;
    }
    
    /**
     *
     * @param n1    The first node to be compared
     * @param n2    The second node to be compared
     * @return      The manhattan distance difference between the two nodes
     */
    public int manhattan_distance(Node n1, Node n2){
        return (Math.abs(n1.xCord - n2.xCord) + Math.abs(n1.yCord - n2.yCord));
    }

    /**
     *
     * @param n1    Node to find distance to goal
     * @return      Manhattan distance from n1 to goal
     */
    public int heuristic_function(Node n1){
        Node goal = formulate_goal();
        return manhattan_distance(n1, goal);
    }
}
