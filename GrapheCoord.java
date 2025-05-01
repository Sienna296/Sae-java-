package graph;
import java.util.*;

import sommet.Sommet;

public class GrapheCoord implements Graph<Sommet> {

    private final Map<Sommet, List<Sommet>> adj;
    private final boolean oriente; // indique si le graphe est orienté

    /**
     * Constructeur pour créer un graphe.
     * @param oriente true pour graphe orienté, false pour graphe non orienté.
     */
    public GrapheCoord(boolean oriente) {
        this.adj = new HashMap<>();
        this.oriente = oriente;
    }

    /**
     * Ajoute un sommet au graphe s'il n'existe pas.
     */
    public void ajouterSommet(Sommet c) {
        if (!adj.containsKey(c)) {
            adj.put(c, new ArrayList<>());
        }
    }

    /**
     * Ajoute un arc entre deux sommets.
     * - Si orienté : ajoute uniquement de a vers b.
     * - Si non orienté : ajoute dans les deux sens.
     */
    public void ajouterArc(Sommet a, Sommet b) {
        ajouterSommet(a);
        ajouterSommet(b);

        if (!adj.get(a).contains(b)) {
            adj.get(a).add(b);
        }
        if (!oriente && !adj.get(b).contains(a)) {
            adj.get(b).add(a);
        }
    }

    /**
     * Retourne l'arc de src vers dest, ou null s'il n'existe pas.
     */

    public Arc<Sommet> arc(Sommet src, Sommet dest) {
        List<Sommet> voisins = adj.get(src);
        if (voisins != null && voisins.contains(dest)) {
            return new Arc<>(1, dest); // poids = 1
        }
        return null;
    }

    /**
     * Retourne la liste des arcs sortants du sommet donné.
     */

    public List<Arc<Sommet>> getSucc(Sommet s) {
        List<Arc<Sommet>> res = new ArrayList<>();
        List<Sommet> voisins = adj.get(s);
        if (voisins != null) {
            for (Sommet v : voisins) {
                res.add(new Arc<>(1, v));
            }
        }
        return res;
    }

    /**
     * Retourne la table d'adjacence brute.
     */
    public Map<Sommet, List<Sommet>> getAdj() {
        return adj;
    }
}
