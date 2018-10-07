/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

import java.util.Comparator;

/**
 *
 * @author root
 */
public class FrontierOrder implements Comparator<Node>{
    @Override
    public int compare(Node n1, Node n2){
        return n2.distance_to_goal - n1.distance_to_goal;
    }
}
