package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



public class GrapheHHAdj implements VarGraph {

	private final Map<String, List<Arc<String>>> adj;

	public GrapheHHAdj() {
		this.adj = new HashMap<>();
	}

	@Override
	public void ajouterSommet(String sommet) {
		if (!adj.containsKey(sommet)) {
			adj.put(sommet, new ArrayList<>());
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if (valeur == null) {
			throw new IllegalArgumentException("La valeur de l'arc ne peut pas être null");
		}

		ajouterSommet(source);
		ajouterSommet(destination);

		if (containsArc(source, destination)) {
			throw new IllegalArgumentException("Arc déjà présent : " + source + " -> " + destination);
		}

		adj.get(source).add(new Arc<>(valeur, destination));
		adj.get(destination).add(new Arc<>(valeur, source)); // symétrie car le graphe est  non orienté
	}



	public void oterSommet(String sommet) {
		if (!adj.containsKey(sommet)) return;

		adj.remove(sommet);
		for (List<Arc<String>> arcs : adj.values()) {
			arcs.removeIf(arc -> arc.dst().equals(sommet));
		}
	}

	public void oterArc(String source, String destination) {
		if (!containsArc(source, destination)) {
			throw new IllegalArgumentException("Arc inexistant : " + source + " -> " + destination);
		}

		adj.get(source).removeIf(arc -> arc.dst().equals(destination));
		adj.get(destination).removeIf(arc -> arc.dst().equals(source));
	}


	public Arc<String> arc(String src, String dest) {
		List<Arc<String>> voisins = adj.get(src);
		if (voisins != null) {
			for (Arc<String> arc : voisins) {
				if (arc.dst().equals(dest)) {
					return arc;
				}
			}
		}
		return null;
	}

	@Override
	public List<Arc<String>> getSucc(String s) {
		List<Arc<String>> voisins = adj.get(s);
		if (voisins != null) {
			return new ArrayList<>(voisins);
		}
		return new ArrayList<>();
	}

	private boolean containsArc(String src, String dest) {
		List<Arc<String>> arcs = adj.get(src);
		if (arcs != null) {
			for (Arc<String> arc : arcs) {
				if (arc.dst().equals(dest)) {
					return true;
				}
			}
		}
		return false;
	}

	public Map<String, List<Arc<String>>> getAdj() {
		return adj;
	}
}



