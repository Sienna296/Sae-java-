package applications;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedHashSet;
import java.util.Set;

public interface Maze<C> {
	// walking
	C start();
	
	Set<C> neighbours(C from); // must contain at least any cell e such that isOpen(from, e)
	
	boolean isOpen(C from, C to);
	
	default Set<C> openedNeighbours(C from) {
		Set<C> neighbours = new LinkedHashSet<>();
		for (C to : neighbours(from))
			if (isOpen(from, to))
				neighbours.add(to);
		return neighbours;
	}
	
	// building (not supported by default)
	default void close(C from, C to) {
		throw new UnsupportedOperationException();
	}
	
	default void open(C from, C to) {
		throw new UnsupportedOperationException();
	}
	
	// drawing (not supported by default)
	default void draw(Graphics2D g) {
		throw new UnsupportedOperationException();
	}
	
	default Rectangle rectangle() {
		throw new UnsupportedOperationException();
	}
	
	default void highlight(Graphics2D g, C cell, Color color) {
		throw new UnsupportedOperationException();		
	}
	
	default void annotate(Graphics2D g, C cell, String s) {
		throw new UnsupportedOperationException();		
	}
	
	default void drawStep(Graphics2D g, C from, C to, Color color) {
		throw new UnsupportedOperationException();		
	}
}
