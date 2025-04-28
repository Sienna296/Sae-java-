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
            //j'ai mis des get pour espérer de régler l'erreur comme quoi on peut pas
            distances.put(Xdepart.getXd(), 0);
            distances.put(Ydepart.getYd(), 0);

            file.add(Xdepart.getXd());
            file.add(Ydepart.getYd());


            if ( Xdepart.getXd() < 0 || Xdepart.getXd() >= MAX_LIGNES || Ydepart.getYd() < 0 || Ydepart.getYd() >= MAX_COLONNES) {
                System.out.println("Départ invalide !");
                return null;
            }

            if (Xarrivee.getXa() < 0 || Xarrivee.getXa() >= MAX_LIGNES || Yarrivee.getYa() < 0 || Yarrivee.getYa() >= MAX_COLONNES) {
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
