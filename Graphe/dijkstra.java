package Graphe;

import java.util.*;

public class dijkstra {

    public class Dijkstra implements Graphe<T> {
        private static T Xdepart, Ydepart, Xarrivee, Yarrivee;
        private static final int MAX_LIGNES = 0;
        private static final int MAX_COLONNES = 0;

        public dijkstra(T Xdepart, T Ydepart, T Xarrivee, T Yarrivee ){
            this.Xdepart = Xdepart;
            this.Ydepart = Ydepart;
            this.Xarrivee = Xarrivee;
            this.Yarrivee = Yarrivee;
            T d = new T(Xdepart, Ydepart);
            T a = new T(Xarrivee, Yarrivee);

        }

        public static <T> List<T> dijkstra() {
            Map<T, Integer> distances = new HashMap<>();
            Queue<T> file = new LinkedList<>();
            Set<T> visites = new HashSet<>();

            distances.put(Xdepart.get(), 0);
            distances.put(Ydepart.get(), 0);

            file.add(Xdepart.get());
            file.add(Ydepart.get());


            if ( Xdepart.get() < 0 || Xdepart.get() >= MAX_LIGNES || Ydepart.get() < 0 || Ydepart.get() >= MAX_COLONNES) {
                System.out.println("Départ invalide !");
                return null;
            }

            if (Xarrivee.get() < 0 || Xarrivee.get() >= MAX_LIGNES || Yarrivee.get() < 0 || Yarrivee.get() >= MAX_COLONNES) {
                System.out.println("Arrivée invalide !");
                return null;
            }

            while (!file.isEmpty()) {
                T courant = file.poll();

                if (!visites.contains(courant)) continue;


                for (Arc<T> arc : file.getSucc(courant)) {
                    T voisin = arc.getVois();

                    if (!distances.containsKey(voisin)) {
                        distances.put(voisin, distances.get(courant) + 1);
                        file.add(voisin);
                    }
                }
            }
            // Reconstruire le chemin
            List<T> chemin = new LinkedList<>();
            T courant = T a;
            while (courant != null ) {
                chemin.add(0, courant);
            }
            if (courant != null && courant.equals(d)) {
                chemin.add(0, d);
            }

            return chemin;
        }
    }

    public static class Graphe {
    }
}
