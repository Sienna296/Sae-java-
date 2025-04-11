
import java.util.*;

public class Implementation<T> implements Graphe<T> {
    private int ligne;
    private int colonne;
    private HashMap<T, List<Arc<T>>> m_adjacence;

    public Implementation() {
        this.m_adjacence = new HashMap<>();  // constructeur pour creer le  hashmap//
    }
    //Methode pour ajouter un Sommet//
    //(if Absent) va verifier si le sommet est dans la Hashmap//
    // si pas dedant methode ajoute avec liste vide d'arcs(essentielle pour prochaine methode ajout d'arc//
    // si dedant fais rien )
    public void ajoutersommet(T sommet){
        m_adjacence.putIfAbsent(sommet, new ArrayList<>());
    }

    public void ajouterarc(T src, T dst,int val){ //Voir classe arc pour ces param//
        ajoutersommet(src);
        ajoutersommet(dst);
        m_adjacence.get(src).add(new Arc<>(val,dst)); //prend la source puis lui ajoute un arc avec sa valuation puis sa destination//
    }
    @Override // redefini la mtehode parce que elle est deja presente dans l'interface graphe//
    public List<Arc<T>> getSucc(T s) {
        return m_adjacence.getOrDefault(s, Collections.emptyList());
        // va essayer de recuperer les sucesseurs de s(sommet) dans la Hashmap//
        //si il n'a pas de sucesseurs alors retourne une liste vide pour pas retourner null//
    }
    public Map<T, Integer> algo_djikstra(T depart){
        Map<T, Integer> djikstra = new HashMap<>();
        return djikstra;
    }

    private boolean estValide(int l, int c) {
        return l >= 0 && l < ligne && c >= 0 && c < colonne;
    }

    public List<Graphe.Arc<Coord>> getSucc(Coord s) {
        List<Graphe.Arc<Coord>> successeurs = new ArrayList<>();
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}}; // bas, haut, droite, gauche

        for (int[] dir : directions) {
            int nl = s.ligne + dir[0];
            int nc = s.colonne + dir[1];
            if (estValide(nl, nc)) {
                successeurs.add(new Graphe.Arc<>(1, new Coord(nl, nc)));
            }
        }

        return successeurs;
    }

    public void afficherGraphe() {
        for (int l = 0; l < lignes; l++) {
            for (int c = 0; c < colonnes; c++) {
                Coord s = new Coord(l, c);
                System.out.print(s + " → ");
                for (Graphe.Arc<Coord> arc : getSucc(s)) {
                    System.out.print(arc.dst() + " ");
                }
                System.out.println();
            }
        }
    }

    @Override
    public List<Arc<String>> getSucc(String s) {
        return List.of();
    }
}



