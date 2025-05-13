package main.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LoopErasedWalk<T> {
    private Deque<T> path = new ArrayDeque();
    private Set<T> visited = new HashSet();

    public LoopErasedWalk(T start) {
        this.makeStep(start);
    }

    public void makeStep(T step) {
        if (!this.visited.contains(step)) {
            this.path.push(step);
            this.visited.add(step);
        } else {
            while(!this.path.peek().equals(step)) {
                this.visited.remove(this.path.pop());
            }
        }

    }

    public Deque<T> getPath() {
        return this.path;
    }

    public String toString() {
        return this.path.toString();
    }
}
