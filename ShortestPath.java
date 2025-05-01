package dijktra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public interface ShortestPath<Sommet> {
    /**
     * Permet de personnaliser l'animation de l'algorithme.
     *
     * @param <Sommet> Identifiant des sommets. Le type doit être "hachable".
     */
    @FunctionalInterface
    public static interface Animator<Sommet> {
        /**
         * Méthode invoquée chaque fois qu'une distance d'un sommet au point de départ est
         * définitivement connue.
         *
         * @param s    Le sommet.
         * @param dist Sa distance au point de départ.
         */
        void accept(Sommet s, int dist);
    }

    /**
     * Résultat d'un calcul de plus court chemin (à partir d'un sommet {@code src}).
     * <p>
     * {@code dist} associe à chaque sommet accessible, la distance minimale qui le
     * sépare de {@code src}. {@code pred} associe à chaque sommet accessible
     * {@code s}, le sommet qui le précède dans un des chemins de longueur minimale
     * partant de {@code src} et conduisant à {@code s}.
     * <p>
     * Les sommets non accessibles à partir de {@code src} sont absents de
     * {@code dist} et {@code pred}. {@code src} est associée à 0 dans {@code dist},
     * et à {@code null} dans {@code pred}.
     *
     * @param <Sommet> Identifiant des sommets. Le type T doit être "hachable".
     */
    public record Distances<Sommet>(Map<Sommet, Integer> dist, Map<Sommet, Sommet> pred) implements Serializable {

        public static <Sommet> void writeDist(Distances<Sommet> dist, String name) throws IOException {
            try (FileOutputStream f = new FileOutputStream(name);
                 ObjectOutputStream out = new ObjectOutputStream(f)) {
                out.writeObject(dist);
                out.flush();
            }
        }
    }
}