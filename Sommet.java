package sommet;



public class Sommet {
    public final int x;
    public final int y;



    public Sommet(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Sommet c) {
        return x == c.x && y == c.y;
    }


    public String toString() {
        return "(" + x + "," + y + ")";

    }
}



