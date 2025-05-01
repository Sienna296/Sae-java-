package main;

import dijktra.Dijkstra;
import graph.Graph;
import graph.GrapheCoord;
import sommet.Sommet;

import java.util.List;

public class Appli {
        public static void main(String[] args) {
            // Création du graphe
            Graph g = new GrapheCoord(true);

            // Création de sommets
            Sommet A = new Sommet(1,8);
            Sommet B = new Sommet(7,5);
            Sommet C = new Sommet(3,3);
            Sommet D = new Sommet(9,1);

            // Ajout des sommets au graphe (si nécessaire)
            g.ajouterSommet(A);
            g.ajouterSommet(B);
            g.ajouterSommet(C);
            g.ajouterSommet(D);

            // Ajout des arcs (graphe non pondéré, orienté ou non)
            g.ajouterArc(A, B);
            g.ajouterArc(B, C);
            g.ajouterArc(A, D);
            g.ajouterArc(D, C);

            // Définir départ et arrivée
            Sommet depart = A;
            Sommet arrivee = C;

            // Appel de Dijkstra (ou plutôt BFS dans ce cas)
            Dijkstra algo = new Dijkstra(g, depart, arrivee);
            List<Sommet> chemin = algo.dijkstra();

            // Affichage du chemin
            if (chemin != null) {
                System.out.println("Chemin le plus court : ");
                for (Sommet s : chemin) {
                    System.out.print(s + " ");
                }
            } else {
                System.out.println("Aucun chemin trouvé !");
            }
        }
    }

