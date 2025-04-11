public class dijkstra {

    public class Dijkstra implements Graphe<T> {
        private static int Xdepart, Ydepart, Xarrivee, Yarrivee;
        private static final int MAX_LIGNES ;
        private static final int MAX_COLONNES;

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

            distances.put(Xdepart, 0);
            distances.put(Ydepart, 0);

            file.add(Xdepart);
            file.add(Ydepart);


            if ( Xdepart < 0 || Xdepart >= MAX_LIGNES || Ydepart < 0 || Ydepart >= MAX_COLONNES) {
                System.out.println("Départ invalide !");
                return null;
            }

            if (Xarrivee < 0 || Xarrivee >= MAX_LIGNES || Yarrivee < 0 || Yarrivee >= MAX_COLONNES) {
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
}
