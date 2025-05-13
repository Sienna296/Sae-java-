package main.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomSet<E> implements Set<E> {
    List<E> dta;
    Map<E, Integer> idx;

    public static void main(String[] args) {
        RandomSet<String> set = new RandomSet<String>(List.of("toto", "titi", "tutu"));
        System.out.println((String)set.stream().collect(Collectors.joining(", ")));
        System.out.println(set.pollRandom(new Random()));
        System.out.println((String)set.stream().collect(Collectors.joining(", ")));
    }

    public RandomSet() {
        this.dta = new ArrayList();
        this.idx = new HashMap();
    }

    public RandomSet(Collection<E> items) {
        this();

        for(E item : items) {
            this.idx.put(item, this.dta.size());
            this.dta.add(item);
        }

    }

    public boolean add(E item) {
        if (this.idx.containsKey(item)) {
            return false;
        } else {
            this.idx.put(item, this.dta.size());
            this.dta.add(item);
            return true;
        }
    }

    private E removeAt(int id) {
        if (id >= this.dta.size()) {
            return null;
        } else {
            E res = (E)this.dta.get(id);
            this.idx.remove(res);
            E last = (E)this.dta.remove(this.dta.size() - 1);
            if (id < this.dta.size()) {
                this.idx.put(last, id);
                this.dta.set(id, last);
            }

            return res;
        }
    }

    public boolean remove(Object item) {
        Integer id = (Integer)this.idx.get(item);
        if (id == null) {
            return false;
        } else {
            this.removeAt(id);
            return true;
        }
    }

    public E pollRandom(Random rnd) {
        if (this.dta.isEmpty()) {
            throw new IllegalStateException("the set is empty");
        } else {
            return (E)this.removeAt(rnd.nextInt(this.dta.size()));
        }
    }

    public int size() {
        return this.dta.size();
    }

    public Iterator<E> iterator() {
        return this.dta.iterator();
    }

    public boolean isEmpty() {
        return this.dta.isEmpty();
    }

    public boolean contains(Object o) {
        return this.idx.containsKey(o);
    }

    public Object[] toArray() {
        return this.dta.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return (T[])this.dta.toArray(a);
    }

    public boolean containsAll(Collection<?> c) {
        return this.idx.keySet().containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;

        for(E e : c) {
            if (this.add(e)) {
                res = true;
            }
        }

        return res;
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        boolean res = false;

        for(Object e : c) {
            if (this.remove(e)) {
                res = true;
            }
        }

        return res;
    }

    public void clear() {
        this.dta.clear();
        this.idx.clear();
    }

    public String toString() {
        return (String)this.dta.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public static <E> void sort(RandomSet<E> r, Comparator<? super E> c) {
        Collections.sort(r.dta, c);
        r.idx.clear();
        int i = 0;

        for(E e : r.dta) {
            r.idx.put(e, i++);
        }

    }

    public static <E extends Comparable<? super E>> void sort(RandomSet<E> r) {
        sort(r, Comparator.naturalOrder());
    }
}
