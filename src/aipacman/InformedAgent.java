/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

/**
 *
 * @author root
 */
public abstract class InformedAgent extends Agent{
    
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
    
    public int manhattan_distance(Node n1, Node n2){
        return (Math.abs(n1.xCord - n2.xCord) + Math.abs(n1.yCord - n2.yCord));
    }
    
    //(Manhattan distance to goal) + (the cost to reach the node)
    public int heuristic_function(Node n1){
        Node goal = formulate_goal();
        return manhattan_distance(n1, goal);
    }
}
