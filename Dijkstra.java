package dijktra;

import graph.Graph;
import sommet.Sommet;
import graph.GrapheCoord;

import java.util.*;

public class Dijkstra {
    private Graph g;
    private Sommet sommetDep;
    private Sommet sommetArr;

    public Dijkstra(Graph g, Sommet sommetDep, Sommet sommetArr) {
        this.g = g;
        this.sommetDep = sommetDep;
        this.sommetArr = sommetArr;
    }

    public List<Sommet> dijkstra() {
        Queue<Sommet> file = new LinkedList<>();
        Set<Sommet> visites = new HashSet<>();
        Map<Sommet, Integer> distances = new HashMap<>();
        Map<Sommet, Sommet> predecesseurs = new HashMap<>();

        file.add(sommetDep);
        distances.put(sommetDep, 0);

        while (!file.isEmpty()) {
            Sommet courant = file.poll();

            if (visites.contains(courant)) continue;
            visites.add(courant);

            if (courant.equals(sommetArr)) break; // Arrêt dès qu'on atteint le sommet d'arrivée

            for (Object voisin : g.getSucc(courant)) {
                if (!distances.containsKey(voisin)) {
                    distances.put((Sommet) voisin, distances.get(courant) + 1);
                    predecesseurs.put((Sommet) voisin, courant);
                    file.add((Sommet) voisin);
                }
            }
        }

        // Reconstruction du chemin
        List<Sommet> chemin = new LinkedList<>();
        Sommet courant = sommetArr;

        while (courant != null && predecesseurs.containsKey(courant)) {
            chemin.add(0, courant);
            courant = predecesseurs.get(courant);
        }

        if (courant != null && courant.equals(sommetDep)) {
            chemin.add(0, sommetDep);
            return chemin;
        } else {
            // Aucun chemin trouvé
            return null;
        }
    }
}