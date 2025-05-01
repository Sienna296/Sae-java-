package dijktra;

import graph.Graph;
import graph.GrapheCoord;
import org.junit.jupiter.api.Test;
import sommet.Sommet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DijkstraTest {

    @Test
    void dijkstra() {
        Graph g = new GrapheCoord(true);

        Sommet A = new Sommet(1,8);
        Sommet B = new Sommet(7,5);
        Sommet C = new Sommet(3,3);
        Sommet D = new Sommet(9,1);

        g.ajouterSommet(A);
        g.ajouterSommet(B);
        g.ajouterSommet(C);
        g.ajouterSommet(D);

        g.ajouterArc(A, B);
        g.ajouterArc(B, C);
        g.ajouterArc(C, A); // cycle
        g.ajouterArc(C, D);

        Dijkstra algo = new Dijkstra(g, A, D);
        List<Sommet> chemin = algo.dijkstra();
    }
}