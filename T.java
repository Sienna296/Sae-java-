import java.util.*;

import java.util.List;

public class T {
    private int xDepart;
    private int yDepart;
    private int xArrivee;
    private int yArrivee;
    //private List<Arete> voisins;

    public T(int xDepart, int yDepart) {
        this.xDepart = xDepart;
        this.yDepart = yDepart;
    }

    public T(int xArrivee, int yArrivee) {
        this.xArrivee = xArrivee;
        this.yArrivee = yArrivee;
    }

    //jveux justement avoir un T pour définir un peu les coordonnées différentes : celles de départ et celles d'arrivée mais voilà
    //j'ai perdu mon cerveau

    public int getXd() {
        return xDepart;
    }

    public int getYd() {
        return yDepart;
    }

    public int getXa() {
        return xArrivee;
    }

    public int getYa() {
        return yArrivee;
    }
}




