package dijkstra.test;

import java.util.List;

import graph.GrapheHHAdj;
import graph.VarGraph;
import org.junit.jupiter.api.Test;

public class GrapheTest {

    private static final String GRAPH1 = "A-B(6), A-C(1), A-D(2), B-E(1), C-E(4), D-B(1), E-F(1)";
    private static final String GRAPH_NEG = "A-B(6), A-C(1), A-D(2), B-E(-3), C-E(4), D-B(1), E-F(1)"; // B-E negatif !
    private static final String FROM = "A";
    private static final String TO = "F";
    private static final int EXPECTED_DIST = 5;
    private static final List<String> EXPECTED_PATH = List.of("F", "E", "B", "D", "A");
    //private static final Dijkstra<String> dijkstra = new Dijkstra<>();

    @Test
    void test() {
        VarGraph g = new GrapheHHAdj();
        VarGraph g2 = new GrapheHHAdj();
        VarGraph g3 = new GrapheHHAdj();
        g.peupler(GRAPH1);

        System.out.println(g);
        System.out.println("TEST AJOUT SOMMETS ET ARCS");
        g.ajouterSommet("A");
        g.ajouterSommet("B");
        g.ajouterSommet("C");

        g.ajouterArc("A", "B", 5);
        g.ajouterArc("A", "C", 10);

        System.out.println("Successeurs de A : " + g.getSucc("A"));
        System.out.println("Successeurs de B : " + g.getSucc("B"));
        System.out.println("Successeurs de C : " + g.getSucc("C"));

        System.out.println("\n TEST ARC EXISTANT ");
        System.out.println("Arc A-B : " + g.arc("A", "B"));
        System.out.println("Arc B-C (inexistant) : " + g.arc("B", "C"));

        System.out.println("\n TEST SUPPRESSION ARC ");
        g.oterArc("A", "B");
        System.out.println("Successeurs de A après suppression A-B : " + g.getSucc("A"));
        System.out.println("Successeurs de B après suppression A-B : " + g.getSucc("B"));

        System.out.println("\n TEST SUPPRESSION SOMMET ");
        g.oterSommet("C");
        System.out.println("Successeurs de A après suppression sommet C : " + g.getSucc("A"));
        System.out.println("Successeurs de C après suppression (devrait être vide) : " + g.getSucc("C"));

        System.out.println("\n TEST AJOUT ARC DOUBLON (DOIT LANCER EXCEPTION) ");
        try {
            g.ajouterArc("A", "B", 5);
            g.ajouterArc("A", "B", 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception attendue : " + e.getMessage());
        }

        System.out.println("\n TEST SUPPRESSION ARC INEXISTANT (DOIT LANCER EXCEPTION)");
        try {
            g.oterArc("A", "C");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception attendue : " + e.getMessage());
        }
    }

}
