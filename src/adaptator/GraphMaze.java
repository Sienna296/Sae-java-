package main.adaptator;

import main.applications.Maze;
import main.graph.Graph;

import java.util.List;

public class GraphMaze implements Graph {
    private Maze maze;

    public GraphMaze(Maze maze) {
        this.maze = maze;

    }

    public List<Arc<String>> getSucc(String s) {
        return null; // jsp sa retourne quoi//
    }
}

