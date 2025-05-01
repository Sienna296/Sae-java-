package graph;

import org.junit.jupiter.api.Test;
import sommet.Sommet;

public class GrapheCoordTest {
    graph.GrapheCoord g1 = new graph.GrapheCoord(true);
    Sommet s1 = new Sommet(4, 8);
    Sommet s2 = new Sommet(3, 7);
    Sommet s3 = new Sommet(2, 6);
    Sommet s4 = new Sommet(5, 4);
    Sommet s5 = new Sommet(6, 3);

    @Test
    void ajouterSommetTest() {

        g1.ajouterSommet(s1);
        g1.ajouterSommet(s2);
        g1.ajouterSommet(s3);
        g1.ajouterSommet(s4);
        //System.out.println(g1.getAdj());
    }

    @Test
    void ajouterArcTest() {
        //ajouterSommetTest();
        g1.ajouterArc(s1, s2);
        //System.out.println(g1.getAdj());
        g1.ajouterArc(s3, s4);
        g1.ajouterArc(s5, s3);
        g1.ajouterArc(s4, s5);
        g1.ajouterArc(s1, s5);
        g1.ajouterArc(s2, s5);
        //System.out.println(g1.getAdj());
        //System.out.println(g1.getAdj());
    }

    @Test
    void arc() {
        ajouterSommetTest();
        ajouterArcTest();
        System.out.println(g1.arc(s1, s2));
        System.out.println(g1.arc(s2, s1));
        System.out.println(g1.arc(s3, s2));

    }


    @Test
    void testGetSucc() {
        ajouterSommetTest();
        ajouterArcTest();
        System.out.println(g1.getSucc(s1));

    }
}