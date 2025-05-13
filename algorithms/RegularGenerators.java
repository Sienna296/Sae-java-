package main.algorithms;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import main.algorithms.RegularGenerators;
import main.regular.SquareMaze;
import main.util.RandomSet;

public class RegularGenerators {
    public static final Map<String, Consumer<SquareMaze>> all = Map.of("BynaryTree", RegularGenerators::recursiveDivision, "RecursDiv", RegularGenerators::recursiveDivision);
    private static Random rd = new Random();

    private RegularGenerators() {
    }

    private static boolean isEastOrSouth(int from, int to, int w) {
        return to % w == from % w + 1 || to / w == from / w + 1;
    }

    public static void binaryTree(SquareMaze maze) {
        binaryTree(maze, (a) -> {
        }, (a, b) -> {
        });
    }

    public static void binaryTree(SquareMaze maze, IntConsumer animCell, BiConsumer<Integer, Integer> animStep) {
        Generators.closeAll(maze);

        for(int pos = 0; pos < maze.width() * maze.height(); ++pos) {
            animCell.accept(pos);
            Set<Integer> neighbours = maze.neighbours(pos);
            List<Integer> candidates = neighbours.stream().filter((tox) -> isEastOrSouth(pos, tox, maze.width())).toList();
            if (!candidates.isEmpty()) {
                Integer to = (Integer)candidates.stream().skip((long)rd.nextInt(candidates.size())).findFirst().orElse((Object)null);
                maze.open(pos, to);
                animStep.accept(pos, to);
            } else if (maze.openedNeighbours(pos).isEmpty() && !neighbours.isEmpty()) {
                Integer to = (Integer)neighbours.stream().skip((long)rd.nextInt(candidates.size())).findFirst().orElse((Object)null);
                maze.open(pos, to);
                animStep.accept(pos, to);
            }
        }

    }

    public static void recursiveDivision(SquareMaze m) {
        recursiveDivision(m, (a, b) -> {
        });
    }

    public static void recursiveDivision(SquareMaze m, BiConsumer<Integer, Integer> animClose) {
        Generators.openAll(m);
        Deque<Zone> toseparate = new ArrayDeque();
        if (m.width() > 1 || m.height() > 1) {
            toseparate.push(new Zone(0, 0, m.width(), m.height()));
        }

        while(!toseparate.isEmpty()) {
            Zone z = (Zone)toseparate.pop();
            boolean vertical = z.width > 1;
            if (z.width > 1 && z.height > 1) {
                vertical = rd.nextBoolean();
            }

            if (vertical) {
                buildWestWalls(m, z, toseparate, animClose);
            } else {
                buildNorthWalls(m, z, toseparate, animClose);
            }
        }

    }

    private static void buildNorthWalls(SquareMaze m, Zone z, Deque<Zone> toseparate, BiConsumer<Integer, Integer> animClose) {
        int line = z.line + z.height / 2;
        RandomSet<Wall> walls = new RandomSet();

        for(int column = z.column; column < z.column + z.width; ++column) {
            int from = line * m.width() + column;

            for(Integer to : m.neighbours(from)) {
                if (isNorth(from, to, m.width())) {
                    walls.add(RegularGenerators.Wall.makeWall(from, to));
                }
            }
        }

        closeWalls(m, walls, animClose);
        if (z.width > 1 || z.height / 2 > 1) {
            toseparate.push(new Zone(z.column, z.line, z.width, z.height / 2));
        }

        if (z.width > 1 || z.height - z.height / 2 > 1) {
            toseparate.push(new Zone(z.column, z.line + z.height / 2, z.width, z.height - z.height / 2));
        }

    }

    private static void buildWestWalls(SquareMaze m, Zone z, Deque<Zone> toseparate, BiConsumer<Integer, Integer> animClose) {
        int column = z.column + z.width / 2;
        RandomSet<Wall> walls = new RandomSet();

        for(int line = z.line; line < z.line + z.height; ++line) {
            int from = line * m.width() + column;

            for(Integer to : m.neighbours(from)) {
                if (isWest(from, to, m.width())) {
                    walls.add(RegularGenerators.Wall.makeWall(from, to));
                }
            }
        }

        closeWalls(m, walls, animClose);
        if (z.width / 2 > 1 || z.height > 1) {
            toseparate.push(new Zone(z.column, z.line, z.width / 2, z.height));
        }

        if (z.width - z.width / 2 > 1 || z.height > 1) {
            toseparate.push(new Zone(z.column + z.width / 2, z.line, z.width - z.width / 2, z.height));
        }

    }

    private static void closeWalls(SquareMaze m, RandomSet<Wall> walls, BiConsumer<Integer, Integer> animClose) {
        walls.pollRandom(rd);
        RandomSet.sort(walls);

        for(Wall w : walls) {
            m.close(w.from, w.to);
            animClose.accept(w.from, w.to);
        }

    }

    private static boolean isNorth(int from, Integer to, int w) {
        return to / w == from / w - 1;
    }

    private static boolean isWest(int from, Integer to, int w) {
        return to % w == from % w - 1;
    }

    private static record Wall(int from, int to) implements Comparable<Wall> {
        private Wall(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public static Wall makeWall(int from, int to) {
            return new Wall(Math.min(from, to), Math.max(from, to));
        }

        public int compareTo(Wall w) {
            return this.from == w.from ? this.to - w.to : this.from - w.from;
        }

        public final String toString() {
            return "(" + this.from + ", " + this.to + ")";
        }

        public int from() {
            return this.from;
        }

        public int to() {
            return this.to;
        }
    }

    private static record Zone(int column, int line, int width, int height) {
        private Zone(int column, int line, int width, int height) {
            this.column = column;
            this.line = line;
            this.width = width;
            this.height = height;
        }

        public final String toString() {
            return "from (" + this.column + ", " + this.line + ") to (" + (this.column + this.width - 1) + ", " + (this.line + this.height - 1) + ") representing " + this.width * this.height + " cells";
        }

        public int column() {
            return this.column;
        }

        public int line() {
            return this.line;
        }

        public int width() {
            return this.width;
        }

        public int height() {
            return this.height;
        }
    }
}
