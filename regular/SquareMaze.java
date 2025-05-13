package main.regular;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.LinkedHashSet;
import java.util.Set;

public final class SquareMaze extends RegularMaze {
    private static final long serialVersionUID = 1L;

    public SquareMaze(int width, int height, double radius) {
        super(width, height, radius);
    }

    public Set<Integer> neighbours(Integer from) {
        if (from != null && from >= 0 && from < this.width * this.height) {
            Set<Integer> neighbours = new LinkedHashSet();
            this.addWest(from, neighbours);
            this.addNorth(from, neighbours);
            this.addEast(from, neighbours);
            this.addSouth(from, neighbours);
            return neighbours;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String var10001 = "---+".repeat(this.width);
        sb.append("+" + var10001 + System.lineSeparator());

        for(int line = 0; line < this.height; ++line) {
            this.line1(sb, line);
            this.line2(sb, line);
        }

        return sb.toString();
    }

    private void line1(StringBuilder sb, int line) {
        sb.append("|");

        for(int column = 0; column < this.width; ++column) {
            sb.append("   ");
            if (this.isOpen(this.get(column, line), this.get(column + 1, line))) {
                sb.append(" ");
            } else {
                sb.append("|");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line2(StringBuilder sb, int line) {
        sb.append("+");

        for(int column = 0; column < this.width; ++column) {
            if (this.isOpen(this.get(column, line), this.get(column, line + 1))) {
                sb.append("   ");
            } else {
                sb.append("---");
            }

            sb.append("+");
        }

        sb.append(System.lineSeparator());
    }

    protected void drawCell(Graphics2D g, int p) {
        Point2D center = this.center(p);
        this.drawCenter(g, center);
        double delta = 2.356194490192345;
        Point2D from = new Point2D.Double(center.getX() + this.radius * Math.cos(delta), center.getY() + this.radius * Math.sin(delta));
        double theta = (Math.PI / 2D);

        for(int n = 1; n <= 4; ++n) {
            Point2D to = new Point2D.Double(center.getX() + this.radius * Math.cos(delta + (double)n * theta), center.getY() + this.radius * Math.sin(delta + (double)n * theta));
            if (this.isFrontier(p, n)) {
                this.drawClosedWall(g, from, to);
            } else if (n > 2) {
                if (this.isOpenWall(p, n)) {
                    this.drawOpenWall(g, from, to);
                } else {
                    this.drawClosedWall(g, from, to);
                }
            }

            from = to;
        }

    }

    private boolean isFrontier(int p, int n) {
        int column = p % this.width;
        int line = p / this.width;
        return n == 1 && column == 0 || n == 2 && line == 0 || n == 3 && column == this.width - 1 || n == 4 && line == this.height - 1;
    }

    private boolean isOpenWall(int from, int n) {
        int to = from + (n == 3 ? 1 : this.width);
        return this.isOpen(from, to);
    }

    protected Point2D center(int p) {
        int column = p % this.width;
        int line = p / this.width;
        double c = (double)2.0F * this.radius / Math.sqrt((double)2.0F);
        return new Point2D.Double(Math.cos((Math.PI / 4D)) * this.radius + (double)column * c, Math.sin((Math.PI / 4D)) * this.radius + (double)line * c);
    }

    public Rectangle rectangle() {
        Point2D c = this.center(this.width * this.height - 1);
        double d = Math.cos((Math.PI / 4D)) * this.radius;
        return new Rectangle((int)Math.round(c.getX() + d), (int)Math.round(c.getY() + d));
    }

    protected double innerRadius(int pos) {
        return this.radius / Math.sqrt((double)2.0F);
    }
}
