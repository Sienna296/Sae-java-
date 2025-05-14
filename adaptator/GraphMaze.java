package main.adaptator;

import main.applications.Maze;
import main.graph.Graph;

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
        Set<Arc<String>> voisins = maze.openedNeighbours(s);

        for(Arc<String> voisin : voisins){
            arcs.add(new Arc<s, voisin, 1>);
        }

        return arcs;
    }
}

