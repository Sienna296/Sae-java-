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

public final class TriangularMaze extends RegularMaze {
    private static final long serialVersionUID = 1L;

    public TriangularMaze(int width, int height, double radius) {
        super(width, height, radius);
    }

    public Set<Integer> neighbours(Integer from) {
        if (from != null && from >= 0 && from < this.width * this.height) {
            Set<Integer> neighbours = new LinkedHashSet();
            boolean north = from % this.width % 2 != from / this.width % 2;
            this.addWest(from, neighbours);
            if (north) {
                this.addNorth(from, neighbours);
            }

            this.addEast(from, neighbours);
            if (!north) {
                this.addSouth(from, neighbours);
            }

            return neighbours;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String var10001 = "-----+".repeat(this.width / 2);
        sb.append("   +" + var10001 + System.lineSeparator());

        for(int line = 0; line < this.height; ++line) {
            this.line1(sb, line);
            this.line2(sb, line);
            this.line3(sb, line);
        }

        return sb.toString();
    }

    private void line1(StringBuilder sb, int line) {
        if (line % 2 == 0) {
            sb.append("  /");
        } else {
            sb.append(" \\");
        }

        for(int column = 0; column < this.width; ++column) {
            boolean open = this.isOpen(this.get(column, line), this.get(column + 1, line));
            if (line % 2 == column % 2) {
                sb.append(open ? "  " : " \\");
            } else {
                sb.append(open ? "    " : "   /");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line2(StringBuilder sb, int line) {
        if (line % 2 == 0) {
            sb.append(" /");
        } else {
            sb.append("  \\");
        }

        for(int column = 0; column < this.width; ++column) {
            boolean open = this.isOpen(this.get(column, line), this.get(column + 1, line));
            if (line % 2 == column % 2) {
                sb.append(open ? "    " : "   \\");
            } else {
                sb.append(open ? "  " : " /");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line3(StringBuilder sb, int line) {
        if (line % 2 != 0) {
            sb.append("   ");
        }

        sb.append("+");

        for(int column = line % 2; column < this.width; column += 2) {
            boolean open = this.isOpen(this.get(column, line), this.get(column, line + 1));
            sb.append(open ? "     +" : "-----+");
        }

        sb.append(System.lineSeparator());
    }

    protected void drawCell(Graphics2D g, int p) {
        Point2D center = this.center(p);
        this.drawCenter(g, center);
        double delta = this.isFliped(p) ? -3.665191429188092 : (Math.PI / 2D);
        Point2D from = new Point2D.Double(center.getX() + this.radius * Math.cos(delta), center.getY() + this.radius * Math.sin(delta));
        double theta = 2.0943951023931953;

        for(int n = 1; n <= 3; ++n) {
            Point2D to = new Point2D.Double(center.getX() + this.radius * Math.cos(delta + (double)n * theta), center.getY() + this.radius * Math.sin(delta + (double)n * theta));
            if (this.isFrontier(p, n)) {
                this.drawClosedWall(g, from, to);
            } else if (n > 1 && this.isFliped(p) || n > 2 && !this.isFliped(p)) {
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
        return n == 1 && column == 0 || n == 2 && !this.isFliped(p) && line == 0 || (n == 2 && this.isFliped(p) || n == 3 && !this.isFliped(p)) && column == this.width - 1 || n == 3 && this.isFliped(p) && line == this.height - 1;
    }

    private boolean isOpenWall(int from, int n) {
        int to = from + (n == 3 && this.isFliped(from) ? this.width : 1);
        return this.isOpen(from, to);
    }

    protected Point2D center(int p) {
        int column = p % this.width;
        int line = p / this.width;
        double dx = (double)1.5F * this.radius / Math.sqrt((double)3.0F);
        double dy = (double)0.75F * this.radius;
        double fdy = this.isFliped(p) ? this.radius * (double)0.25F : -this.radius * (double)0.25F;
        return new Point2D.Double(dx + (double)column * dx, dy + (double)line * this.radius * (Math.sqrt((double)3.0F) - (double)0.25F) + fdy);
    }

    private boolean isFliped(int p) {
        return p % this.width % 2 == p / this.width % 2;
    }

    public Rectangle rectangle() {
        int p = this.width * this.height - 1;
        Point2D c = this.center(p);
        double dx = (double)1.5F * this.radius / Math.sqrt((double)3.0F);
        double dy = this.isFliped(p) ? this.radius * (double)0.5F : this.radius;
        return new Rectangle((int)Math.round(c.getX() + dx), (int)Math.round(c.getY() + dy));
    }

    protected double innerRadius(int pos) {
        return this.radius / (double)2.0F;
    }
}
