/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

import java.lang.Math;

/**
 *
 * @author Alex
 */
public class Node implements Comparable<Node>{

    public Node[] neighbors = new Node[4];
    public int xCord;
    public int yCord;
    public char id;
    public int spot = 0;
    public boolean visited = false;
    public boolean inSolution = false;
    public Node parent;
    
    public Node(){

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
    public int compareTo(Node n){
        //Returns Manhattan distance between nodes
        return Math.abs(xCord - n.xCord) + Math.abs(yCord-n.yCord);
    }
}
