package adaptator;

import graph.Graph;
import maze.*;
import java.util.ArrayList;
import java.util.List;

public class GraphMaze implements Graph {
    private Maze maze;

    public GraphMaze(Maze maze) {
        this.maze = maze;

    }

    public List<Arc<String>> getSucc(String s) {
        List<Arc<String>> arcs = new ArrayList<>();
        for (Object voisin : maze.openedNeighbours(s)) {
            arcs.add(new Arc<>(1, (String) voisin));
        }
        return arcs;
    }
}
