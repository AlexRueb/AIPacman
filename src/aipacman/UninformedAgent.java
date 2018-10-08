package aipacman;

/**
 * UninformedAgent is the super class for DFS and BFS.
 * 
 * @author Dillon Tice and Alex Rueb
 */
abstract public class UninformedAgent extends Agent{
    QueueOrStack<Node> frontier;

    /**
     * Tree search implementation for BFS and DFS
     * @param target    The start node
     */
    public void find_path(Node target) {
        int x = target.xCord;
        int y = target.yCord;
        //maze[y][x].id = '.';
        nodesExpanded++;
        //for each neighbor of the node
        for (int i = 0; i < target.spot; i++) {
            target.visited = true;
            //if the node has already been checked
            if (!target.neighbors[i].visited) {
                //if the node is a goal state
                if (target.neighbors[i].id == '*') {
                    solved = true;
                    target.neighbors[i].parent = target;
                    answer.push(target.neighbors[i]);
                    return;
                }
                target.neighbors[i].parent = target;
                target.neighbors[i].visited = true;
                frontier.push(target.neighbors[i]);

            } else {}
        }
    }
}
