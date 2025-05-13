package main.regular;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public final class HexagonalMaze extends RegularMaze {
    private static final long serialVersionUID = 1L;

    public HexagonalMaze(int width, int height, double radius) {
        super(width, height, radius);
    }

    public Set<Integer> neighbours(Integer from) {
        if (from != null && from >= 0 && from < this.width * this.height) {
            Set<Integer> neighbours = new HashSet();
            boolean north = from % this.width % 2 == 0;
            this.addWest(from, neighbours);
            if (north) {
                this.addNorthWest(from, neighbours);
            }

            this.addNorth(from, neighbours);
            if (north) {
                this.addNorthEast(from, neighbours);
            }

            this.addEast(from, neighbours);
            if (!north) {
                this.addSouthEast(from, neighbours);
            }

            this.addSouth(from, neighbours);
            if (!north) {
                this.addSouthWest(from, neighbours);
            }

            return neighbours;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String var10001 = "+---+       ".repeat((this.width + 1) / 2);
        sb.append("  " + var10001 + System.lineSeparator());

        for(int line = 0; line < this.height; ++line) {
            this.line1(sb, line);
            this.line2(sb, line);
            this.line3(sb, line);
            this.line4(sb, line);
        }

        this.end(sb);
        return sb.toString();
    }

    private void line1(StringBuilder sb, int line) {
        sb.append(" /");

        for(int column = 0; column < this.width; ++column) {
            if (column % 2 == 0) {
                boolean open = this.isOpen(this.get(column, line), this.get(column + 1, line - 1));
                sb.append(open ? "      " : "     \\");
            } else {
                boolean open = line == 0 && column == this.width - 1 || line != 0 && this.isOpen(this.get(column, line - 1), this.get(column + 1, line));
                sb.append(open ? "      " : "     /");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line2(StringBuilder sb, int line) {
        sb.append("+");

        for(int column = 0; column < this.width; ++column) {
            if (column % 2 == 0) {
                sb.append("       +");
            } else {
                boolean open = line != 0 && this.isOpen(this.get(column, line), this.get(column, line - 1));
                sb.append(open ? "   +" : "---+");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line3(StringBuilder sb, int line) {
        sb.append(" \\");

        for(int column = 0; column < this.width; ++column) {
            if (column % 2 == 0) {
                boolean open = this.isOpen(this.get(column, line), this.get(column + 1, line));
                sb.append(open ? "      " : "     /");
            } else {
                boolean open = this.isOpen(this.get(column, line), this.get(column + 1, line));
                sb.append(open ? "      " : "     \\");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void line4(StringBuilder sb, int line) {
        sb.append("  +");

        for(int column = 0; column < this.width; ++column) {
            if (column % 2 == 1) {
                sb.append("       +");
            } else {
                boolean open = this.isOpen(this.get(column, line), this.get(column, line + 1));
                sb.append(open ? "   +" : "---+");
            }
        }

        sb.append(System.lineSeparator());
    }

    private void end(StringBuilder sb) {
        sb.append("       ");

        for(int column = 0; column < this.width / 2; ++column) {
            sb.append("\\     /     ");
        }

        sb.append(System.lineSeparator());
        sb.append("        ");

        for(int column = 0; column < this.width / 2; ++column) {
            sb.append("+---+       ");
        }

        sb.append(System.lineSeparator());
    }

    protected void drawCell(Graphics2D g, int p) {
        Point2D center = this.center(p);
        this.drawCenter(g, center);
        double delta = this.columnIsOdd(p) ? Math.PI : 2.0943951023931953;
        Point2D from = new Point2D.Double(center.getX() + this.radius * Math.cos(delta), center.getY() + this.radius * Math.sin(delta));
        double theta = (Math.PI / 3D);

        for(int n = 1; n <= 6; ++n) {
            Point2D to = new Point2D.Double(center.getX() + this.radius * Math.cos(delta + (double)n * theta), center.getY() + this.radius * Math.sin(delta + (double)n * theta));
            if (this.isFrontier(p, n)) {
                this.drawClosedWall(g, from, to);
            } else {
                boolean oddColumn = this.columnIsOdd(p);
                if (oddColumn && n >= 3 && n <= 5 || !oddColumn && n >= 4) {
                    if (this.isOpenWall(p, n)) {
                        this.drawOpenWall(g, from, to);
                    } else {
                        this.drawClosedWall(g, from, to);
                    }
                }
            }

            from = to;
        }

    }

    private boolean isFrontier(int p, int n) {
        int column = p % this.width;
        int line = p / this.width;
        boolean oddColumn = this.columnIsOdd(p);
        return this.lineFrontier(line, oddColumn, n) ? true : this.columnFrontier(column, oddColumn, n);
    }

    private boolean columnFrontier(int column, boolean oddColumn, int n) {
        if (column != 0 || n != 1 && n != 2) {
            if (column == this.width - 1) {
                if (oddColumn && (n == 3 || n == 4)) {
                    return true;
                }

                if (!oddColumn && (n == 4 || n == 5)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    private boolean lineFrontier(int line, boolean oddColumn, int n) {
        if (line == 0) {
            if (oddColumn && n == 2) {
                return true;
            }

            if (!oddColumn && n >= 2 && n <= 4) {
                return true;
            }
        }

        if (line == this.height - 1) {
            if (oddColumn && n >= 4 && n <= 6) {
                return true;
            }

            if (!oddColumn && n == 6) {
                return true;
            }
        }

        return false;
    }

    private boolean isOpenWall(int from, int n) {
        boolean oddColumn = this.columnIsOdd(from);
        int to;
        if (oddColumn) {
            if (n == 3) {
                to = from + 1;
            } else if (n == 4) {
                to = from + this.width + 1;
            } else {
                to = from + this.width;
            }
        } else if (n == 4) {
            to = from + 1 - this.width;
        } else if (n == 5) {
            to = from + 1;
        } else {
            to = from + this.width;
        }

        return this.isOpen(from, to);
    }

    protected Point2D center(int p) {
        int column = p % this.width;
        int line = p / this.width;
        double x = this.radius;
        double y = Math.sqrt((double)3.0F) * this.radius / (double)2.0F;
        double dy = this.columnIsOdd(p) ? this.radius * Math.sqrt((double)3.0F) * (double)0.5F : (double)0.0F;
        return new Point2D.Double(x + (double)column * (double)1.5F * this.radius, y + (double)line * this.radius * Math.sqrt((double)3.0F) + dy);
    }

    private boolean columnIsOdd(int p) {
        return p % this.width % 2 == 1;
    }

    public Rectangle rectangle() {
        Point2D c = this.center(this.width * this.height - 1);
        double dx = this.radius;
        double dy = Math.sqrt((double)3.0F) * this.radius / (double)2.0F;
        if (this.width > 1 && this.width % 2 == 1) {
            dy += this.radius * Math.sqrt((double)3.0F) * (double)0.5F;
        }

        return new Rectangle((int)Math.round(c.getX() + dx), (int)Math.round(c.getY() + dy));
    }

    protected double innerRadius(int pos) {
        return Math.sqrt((double)3.0F) * this.radius / (double)2.0F;
    }
}
