package adaptator;

import applications.Maze;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
