package main.Panel;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.applications.Maze;

public class MazeView<C> extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SHIFT = 5;
    private final transient Maze<C> maze;
    private final transient Map<C, String> annotations;
    private final transient Map<C, Color> highlighted;
    private final transient Map<Color, Set<Map.Entry<C, C>>> steps;

    public static <C> MazeView<C> view(Maze<C> maze, String subtitle) {
        MazeView<C> view = new MazeView<C>(maze);
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Maze viewer - " + subtitle);
            f.setDefaultCloseOperation(3);
            f.setResizable(false);
            f.add(view, "Center");
            f.pack();
            f.setLocationRelativeTo((Component)null);
            f.setVisible(true);
        });
        return view;
    }

    public MazeView(Maze<C> maze) {
        this.maze = maze;
        this.annotations = new ConcurrentHashMap();
        this.highlighted = new ConcurrentHashMap();
        this.steps = new ConcurrentHashMap();
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = maze.rectangle();
        this.setPreferredSize(new Dimension(Math.min(dm.width, rect.width + 10), Math.min(dm.height, rect.height + 10)));
        this.setBackground(Color.white);
    }

    public void clear() {
        this.annotations.clear();
        this.highlighted.clear();
        this.steps.clear();
    }

    public void annotate(C pos, String s) {
        this.annotations.put(pos, s);
    }

    public void desannotate(C pos) {
        this.annotations.remove(pos);
    }

    public void highlight(C pos, Color col) {
        this.highlighted.put(pos, col);
    }

    public void unhighlight(C pos) {
        this.highlighted.remove(pos);
    }

    public void addStep(C from, C to, Color col) {
        ((Set)this.steps.computeIfAbsent(col, (c) -> new CopyOnWriteArraySet())).add(Map.entry(from, to));
    }

    public void addPath(List<C> path, Color col) {
        if (path.size() > 1) {
            Iterator<C> it = path.iterator();

            C to;
            for(C from = (C)it.next(); it.hasNext(); from = to) {
                to = (C)it.next();
                this.addStep(from, to, col);
            }
        }

    }

    public void removeStep(C from, C to, Color col) {
        this.steps.computeIfPresent(col, (c, s) -> {
            s.remove(Map.entry(from, to));
            return s.isEmpty() ? null : s;
        });
    }

    public void removeSteps(Color col) {
        this.steps.remove(col);
    }

    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D)gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(5, 5);

        for(Map.Entry<Color, Set<Map.Entry<C, C>>> cs : this.steps.entrySet()) {
            for(Object s : (Set)cs.getValue()) {
                this.maze.drawStep(g, s.getKey(), s.getValue(), (Color)cs.getKey());
            }
        }

        for(Map.Entry<C, Color> h : this.highlighted.entrySet()) {
            this.maze.highlight(g, h.getKey(), (Color)h.getValue());
        }

        for(Map.Entry<C, String> a : this.annotations.entrySet()) {
            this.maze.annotate(g, a.getKey(), (String)a.getValue());
        }

        this.maze.draw(g);
        g.translate(-5, -5);
    }
}
