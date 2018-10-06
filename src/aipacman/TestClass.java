/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipacman;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author root
 */
public class TestClass {
    public static void main(String[] args){
        Stack stack1 = new Stack();
        List stack2 = new Stack();
        int j = 10;
        for(int i = 0; i <= 9; i++){
            stack1.push(i);
            stack2.add(i);
        }
        System.out.println("1:");
        for(int i = 0; i <= 9; i++){
            System.out.println(stack1.pop());
        }
        System.out.println("2");
        for(int i = 0; i <= 9; i++){
            System.out.println(stack2.get(stack2.size() - 1));
        }
    }
}
