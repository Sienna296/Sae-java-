package main.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.HashMap;
import java.util.Map;

public class DisjointsSets<T> {
    private Map<T, T> friends = new HashMap();

    public DisjointsSets() {
    }

    public void add(T t) {
        if (t == null) {
            throw new NullPointerException();
        } else if (this.friends.containsKey(t)) {
            throw new IllegalArgumentException();
        } else {
            this.friends.put(t, t);
        }
    }

    private T find(T t) {
        if (!this.friends.containsKey(t)) {
            throw new IllegalArgumentException();
        } else {
            T x = t;

            T r;
            for(r = (T)this.friends.get(t); x != r; r = (T)this.friends.get(r)) {
                x = r;
            }

            while(t != r) {
                x = (T)this.friends.get(t);
                this.friends.put(t, r);
                t = x;
            }

            return r;
        }
    }

    public boolean sameSet(T t1, T t2) {
        return this.find(t1) == this.find(t2);
    }

    public void union(T t1, T t2) {
        this.friends.put(this.find(t1), this.find(t2));
    }

    public int size() {
        return this.friends.size();
    }

    public String toString() {
        return this.friends.toString();
    }
}
