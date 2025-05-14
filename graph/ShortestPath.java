package main.graph;
import java.io.*;
import java.util.Map;

public interface ShortestPath<T> {

	@FunctionalInterface
	public static interface Animator<T> {
		void accept(T s, int dist);
	}

	public record Distances<T>(Map<T, Integer> dist, Map<T, T> pred) implements Serializable {

		public static <T> void writeDist(Distances<T> dist, String name) throws IOException {
			try (FileOutputStream f = new FileOutputStream(name);
				 ObjectOutputStream out = new ObjectOutputStream(f)) {
				out.writeObject(dist);
				out.flush();
			}
		}

		@SuppressWarnings("unchecked")
		public static Distances<Integer> readDist(String name) throws IOException, ClassNotFoundException {
			try (FileInputStream f = new FileInputStream(name);
				 ObjectInputStream in = new ObjectInputStream(f)) {
				return (Distances<Integer>) in.readObject();
			}
		}
	}

	Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException;

	default Distances<T> compute(Graph<T> g, T src) throws IllegalArgumentException {
		return compute(g, src, (n, d) -> {});
	}
}
