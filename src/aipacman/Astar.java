//This is the initial file for the A* search agent
package aipacman;

public class Astar {
  public Astar() {}

  public Node[] frontier;

  //the method that solves the maze
  public char[][] solve(char[][] maze) {
        //initializing variables
        int startX = 0;
        int startY = 0;
        int x, y;
        y = maze.length;
        char[] t = maze[1];
        x = t.length;

        //finding start point of maze
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (maze[i][j] == 'P') {
                    startX = j;
                    startY = i;
                    break;
                }
            }
        }
        System.out.println("Starting at: X = " + startX + " and Y = " + startY);
        return maze;
    }
}
