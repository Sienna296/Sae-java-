package adaptator;

import graph.Graph;
import maze.*;
import java.util.ArrayList;
import java.util.List;

public class GraphMaze<T> implements Graph<T> {
    private final Maze<T> maze;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    public List<Arc<T>> getSucc(T s) {
        List<Arc<T>> arcs = new ArrayList<>();
        for (T voisin : maze.openedNeighbours(s)) {
            arcs.add(new Arc<>(1, voisin));
        }
        return arcs;
    }
}
