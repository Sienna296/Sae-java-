package dijkstra;


import graph.Graph;
import graph.ShortestPath;

import java.util.*;


public class Dijkstra<String> implements ShortestPath<String> {
	private Map<String, Integer> dist = new HashMap<>(); // Pour stocker la distance minimale pour chaque sommet
	private Map<String, String> pred = new HashMap<>(); // Stocke le prédécesseur pour chaque sommet
	private Map<String, Boolean> marque = new HashMap<>(); // Stocke les sommet marqués (--> on connait deja sa distance minimale)
	private List<String> voisinsAVisiter = new ArrayList<>(); // Stocke les sommets pas encore visités


	@Override
	public Distances<String> compute(Graph<String> g, String src, Animator<String> animator) throws IllegalArgumentException {


		// Initialisation
		dist.put(src, 0);
		pred.put(src, null);
		marque.put(src, false);
		voisinsAVisiter.add(src);

		// On visite les sommets voisins
		for (int i = 0; i < voisinsAVisiter.size(); i++) {
			String sommetActuel = voisinsAVisiter.get(i);
			for (Graph.Arc<java.lang.String> arc : g.getSucc((java.lang.String) sommetActuel)) {
				String voisin = (String) arc.dst();

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
			String sommetCourant = null;
			int minDist = Integer.MAX_VALUE; // Infini

			// on recherche un sommet non marqué avec sa distance minimale
			for (String s : dist.keySet()) {
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
			for (Graph.Arc<java.lang.String> arc : g.getSucc((java.lang.String) sommetCourant)) {
				String sommetVoisin = (String) arc.dst();
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

