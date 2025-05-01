package graph;

import sommet.Sommet;

import java.util.List;

/**
 * Interface représentant un graphe.
 *
 * Elle n'impose qu'une unique méthode permettant de programmer un plus court chemin.
 *
 * @param <Coord>> Identifiant des sommets. Le type doit disposer d'une méthode hashcode.
 */
public interface Graph<Coord> {
    void ajouterSommet(Sommet a);

    void ajouterArc(Sommet a, Sommet b);



    /**
     * Un arc du graphe (désigné par sa valuation et le sommet destination).
     *
     * @param <Coord>> Identifiant des so
     *                  mmets. Le type T doit être "hachable".
     */
    public record Arc<Coord>(int val, Coord dst) {
    }

    /**
     * Donne la liste des arcs sortants d'un sommet.
     *
     * @param s Le sommet.
     * @return La liste des arcs sortants de {@code s}.
     */
    List<Arc<Coord>> getSucc(Coord s);
}
