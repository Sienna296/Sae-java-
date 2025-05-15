package dijkstra;


import graph.Graph;
import graph.ShortestPath;

import java.util.*;


public class Dijkstra<T> implements ShortestPath<T> {
	private Map<T, Integer> dist = new HashMap<>(); // Pour stocker la distance minimale pour chaque sommet
	private Map<T, T> pred = new HashMap<>(); // Stocke le prédécesseur pour chaque sommet
	private Map<T, Boolean> marque = new HashMap<>(); // Stocke les sommet marqués (--> on connait deja sa distance minimale)
	private List<T> voisinsAVisiter = new ArrayList<>(); // Stocke les sommets pas encore visités


	@Override
	public Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException {


		// Initialisation
		dist.put(src, 0);
		pred.put(src, null);
		marque.put(src, false);
		voisinsAVisiter.add(src);

		// On visite les sommets voisins
		for (int i = 0; i < voisinsAVisiter.size(); i++) {
			T sommetActuel = voisinsAVisiter.get(i);
			for (Graph.Arc<T> arc : g.getSucc((T) sommetActuel)) {
				T voisin = (T) arc.dst();

				// Ajoute les sommets découverts à voisinsAVisiter si nouveaux
				if (!dist.containsKey(voisin)) {
					dist.put(voisin, Integer.MAX_VALUE); // Infini
					pred.put(voisin, null);
					marque.put(voisin, false);
					voisinsAVisiter.add(voisin);
				}

				if (arc.val() < 0) {
					throw new IllegalArgumentException("Un arc à valuation négative a été détecté entre " + sommetActuel + " et " + voisin);
				}
			}
		}


		while (true) {
			T sommetCourant = null;
			int minDist = Integer.MAX_VALUE; // Infini

			// on recherche un sommet non marqué avec sa distance minimale
			for (T s : dist.keySet()) {
				if (!marque.get(s) && dist.get(s) < minDist) {
					minDist = dist.get(s); // distance minimale mise à jour
					sommetCourant = s;
				}
			}


			// Lorsque tous les sommets ont été traités
			if (sommetCourant == null) break;
			marque.put(sommetCourant, true); // Marquage du sommet
			animator.accept(sommetCourant, dist.get(sommetCourant));

			// Construction du chemin et calcul de sa valuation
			for (Graph.Arc<T> arc : g.getSucc((T) sommetCourant)) {
				T sommetVoisin = (T) arc.dst();
				int altChemin = dist.get(sommetCourant) + arc.val();

				// Cas ou on a trouve un chemin plus court que celui déjà connu
				if (altChemin < dist.get(sommetVoisin)) {
					dist.put(sommetVoisin, altChemin);
					pred.put(sommetVoisin, sommetCourant);
				}
			}
		}

		return new Distances<>(dist, pred);
	}
}

