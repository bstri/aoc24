package aoc;

import java.awt.Point;
import java.util.List;
import java.util.function.BiFunction;

public class Board {

    public Integer height;
    public Integer width;
    char[][] board;

    public Board(String input) {
        List<String> lines = Utils.splitLines(input);
        this.height = lines.size();
        this.width = lines.getFirst().length();
        this.board = new char[height][];
        for (int y = 0; y < height; ++y) {
            String line = lines.get(y);
            board[y] = line.toCharArray();
        }
    }

    public Point getNextPoint(Point point) {
        if (point.x == width - 1) {
            if (point.y == height - 1) {
                return null;
            }
            return new Point(0, point.y + 1);
        }
        return new Point(point.x + 1, point.y);
    }

    public Point find(char c, Point startingAfter) {
        if (startingAfter == null) {
            return find(c);
        }
        Point starting = getNextPoint(startingAfter);
        if (starting == null) {
            return null;
        }
        char[] line = board[starting.y];
        for (int x = starting.x; x < width; ++x) {
            if (line[x] == c) {
                return new Point(x, starting.y);
            }
        }
        for (int y = starting.y + 1; y < height; ++y) {
            line = board[y];
            for (int x = 0; x < width; ++x) {
                if (line[x] == c) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    public Point find(char c) {
        return find(c, new Point(width - 1,-1));
    }

    public char get(Point p) {
        return this.board[p.y][p.x];
    }

    public char get(int x, int y) {
        return this.board[y][x];
    }

    public void set(Point p, char c) {
        this.board[p.y][p.x] = c;
    }

    public void set(int x, int y, char c) {
        this.board[y][x] = c;
    }

    public void forEach(BiFunction<Point, Character, Boolean> fn) {
        for (int y = 0; y < height; ++y) {
            char[] line = board[y];
            for (int x = 0; x < width; ++x) {
                if (fn.apply(new Point(x, y), line[x]) == true) {
                    return;
                }
            }
        }
    }

    public boolean inBounds(Point p) {
        return inBounds(p.x, p.y);
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public static Point add(Point p1, Point p2) {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    public static Point sub(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

}