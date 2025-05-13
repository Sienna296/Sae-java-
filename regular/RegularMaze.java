package main.regular;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import main.applications.Maze;

public abstract sealed class RegularMaze implements Serializable, Maze<Integer> permits main.algorithms.HexagonalMaze, HexagonalMaze, SquareMaze, TriangularMaze {
    private static final long serialVersionUID = 1L;
    protected final int width;
    protected final int height;
    private final Set<Issue> issues;
    protected final int start;
    protected final double radius;

    public static RegularMaze makeMaze(Shape s, int w, int h, double r) {
        Object var10000;
        switch (s) {
            case TRIANGLE -> var10000 = new TriangularMaze(w, h, r);
            case SQUARE -> var10000 = new SquareMaze(w, h, r);
            case HEXAGON -> var10000 = new HexagonalMaze(w, h, r);
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return (RegularMaze)var10000;
    }

    public static void writeMaze(RegularMaze maze, String name) throws Throwable {
        Throwable var2 = null;
        Object var3 = null;

        try {
            FileOutputStream f = new FileOutputStream(name);

            try {
                ObjectOutputStream out = new ObjectOutputStream(f);

                try {
                    out.writeObject(maze);
                    out.flush();
                } finally {
                    if (out != null) {
                        out.close();
                    }

                }
            } catch (Throwable var16) {
                if (var2 == null) {
                    var2 = var16;
                } else if (var2 != var16) {
                    var2.addSuppressed(var16);
                }

                if (f != null) {
                    f.close();
                }

                throw var2;
            }

            if (f != null) {
                f.close();
            }

        } catch (Throwable var17) {
            if (var2 == null) {
                var2 = var17;
            } else if (var2 != var17) {
                var2.addSuppressed(var17);
            }

            throw var2;
        }
    }

    public static RegularMaze readMaze(String name) throws Throwable {
        Throwable var1 = null;
        Object var2 = null;

        try {
            FileInputStream f = new FileInputStream(name);

            RegularMaze var10000;
            try {
                ObjectInputStream in = new ObjectInputStream(f);

                try {
                    var10000 = (RegularMaze)in.readObject();
                } finally {
                    if (in != null) {
                        in.close();
                    }

                }
            } catch (Throwable var15) {
                if (var1 == null) {
                    var1 = var15;
                } else if (var1 != var15) {
                    var1.addSuppressed(var15);
                }

                if (f != null) {
                    f.close();
                }

                throw var1;
            }

            if (f != null) {
                f.close();
            }

            return var10000;
        } catch (Throwable var16) {
            if (var1 == null) {
                var1 = var16;
            } else if (var1 != var16) {
                var1.addSuppressed(var16);
            }

            throw var1;
        }
    }

    protected RegularMaze(int width, int height, double radius) {
        if (width > 0 && height > 0 && !(radius < (double)5.0F)) {
            this.width = width;
            this.height = height;
            this.issues = new CopyOnWriteArraySet();
            this.start = 0;
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.height, this.issues, this.start, this.width});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            RegularMaze other = (RegularMaze)obj;
            return this.height == other.height && this.width == other.width && this.start == other.start && Objects.equals(this.issues, other.issues);
        } else {
            return false;
        }
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    public Integer start() {
        return this.start;
    }

    public boolean isOpen(Integer from, Integer to) {
        return this.issues.contains(RegularMaze.Issue.get(from, to));
    }

    public void close(Integer from, Integer to) {
        if (!this.neighbours(from).contains(to)) {
            throw new IllegalArgumentException();
        } else {
            this.issues.remove(RegularMaze.Issue.get(from, to));
        }
    }

    public void open(Integer from, Integer to) {
        if (!this.neighbours(from).contains(to)) {
            throw new IllegalArgumentException();
        } else {
            this.issues.add(RegularMaze.Issue.get(from, to));
        }
    }

    protected int get(int column, int line) {
        return line * this.width + column;
    }

    protected void addWest(int from, Set<Integer> neighbours) {
        if (from % this.width > 0) {
            neighbours.add(from - 1);
        }

    }

    protected void addEast(int from, Set<Integer> neighbours) {
        if (from % this.width < this.width - 1) {
            neighbours.add(from + 1);
        }

    }

    protected void addNorth(int from, Set<Integer> neighbours) {
        if (from / this.width > 0) {
            neighbours.add(from - this.width);
        }

    }

    protected void addSouth(int from, Set<Integer> neighbours) {
        if (from / this.width < this.height - 1) {
            neighbours.add(from + this.width);
        }

    }

    protected void addNorthWest(int from, Set<Integer> neighbours) {
        if (from % this.width > 0 && from / this.width > 0) {
            neighbours.add(from - this.width - 1);
        }

    }

    protected void addNorthEast(int from, Set<Integer> neighbours) {
        if (from % this.width < this.width - 1 && from / this.width > 0) {
            neighbours.add(from - this.width + 1);
        }

    }

    protected void addSouthWest(int from, Set<Integer> neighbours) {
        if (from % this.width > 0 && from / this.width < this.height - 1) {
            neighbours.add(from + this.width - 1);
        }

    }

    protected void addSouthEast(int from, Set<Integer> neighbours) {
        if (from % this.width < this.width - 1 && from / this.width < this.height - 1) {
            neighbours.add(from + this.width + 1);
        }

    }

    protected abstract void drawCell(Graphics2D var1, int var2);

    protected abstract Point2D center(int var1);

    protected abstract double innerRadius(int var1);

    public void draw(Graphics2D g) {
        for(int p = 0; p < this.width * this.height; ++p) {
            this.drawCell(g, p);
        }

    }

    protected void drawCenter(Graphics2D g, Point2D center) {
        double SIZE = (double)3.0F;
        g.fill(new Ellipse2D.Double(center.getX() - (double)1.5F, center.getY() - (double)1.5F, (double)3.0F, (double)3.0F));
    }

    protected void drawClosedWall(Graphics2D g, Point2D from, Point2D to) {
        g.draw(new Line2D.Double(from, to));
    }

    protected void drawOpenWall(Graphics2D g, Point2D from, Point2D to) {
        Point2D dest = dest(from, to);
        g.draw(new Line2D.Double(from, dest));
        dest = dest(to, from);
        g.draw(new Line2D.Double(to, dest));
    }

    private static Point2D dest(Point2D a, Point2D b) {
        double x = a.getX() + (b.getX() - a.getX()) / (double)6.0F;
        double y = a.getY() + (b.getY() - a.getY()) / (double)6.0F;
        return new Point2D.Double(x, y);
    }

    public void highlight(Graphics2D g, Integer cell, Color color) {
        Color org = g.getColor();
        g.setColor(color);
        Point2D c = this.center(cell);
        double size = this.innerRadius(cell);
        g.fill(new Ellipse2D.Double(c.getX() - size / (double)2.0F, c.getY() - size / (double)2.0F, size, size));
        g.setColor(org);
    }

    public void annotate(Graphics2D g, Integer cell, String s) {
        Point2D c = this.center(cell);
        g.drawString(s, (int)(c.getX() + (double)2.0F), (int)(c.getY() - (double)2.0F));
    }

    public void drawStep(Graphics2D g, Integer from, Integer to, Color color) {
        Stroke currentStroke = g.getStroke();
        Color org = g.getColor();
        g.setStroke(new BasicStroke(3.0F, 0, 2));
        g.setColor(color);
        g.draw(new Line2D.Double(this.center(from), this.center(to)));
        g.setColor(org);
        g.setStroke(currentStroke);
    }

    public static enum Shape {
        TRIANGLE,
        SQUARE,
        HEXAGON;

        private Shape() {
        }
    }

    protected static record Issue(int from, int to) implements Serializable {
        protected Issue(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public static Issue get(int from, int to) {
            return from < to ? new Issue(from, to) : new Issue(to, from);
        }

        public int from() {
            return this.from;
        }

        public int to() {
            return this.to;
        }
    }
}
