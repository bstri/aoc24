package aoc.day08;

import aoc.Day;
import aoc.Utils;

import java.util.*;

public class Day08 implements Day {
    private static class Coordinate {
        public int row;
        public int col;

        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "%d_%d".formatted(row, col);
        }
    }

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
        int gridX = lines.getFirst().length();
        int gridY = lines.size();
        Map<Character,List<Coordinate>> antennae = new HashMap<>();
        for (int lineNum = 0; lineNum < gridY; lineNum++) {
            String line = lines.get(lineNum);
            for (int colNum = 0; colNum < gridX; colNum++) {
                char c = line.charAt(colNum);
                if (c == '.') {
                    continue;
                }
                Coordinate coord = new Coordinate(lineNum, colNum);
                if (!antennae.containsKey(c)) {
                    antennae.put(c, new ArrayList<>(Collections.singletonList(coord)));
                } else {
                    antennae.get(c).add(coord);
                }
            }
        }

        Set<String> antinodes = new HashSet<>();
        antennae.forEach((ignored,coords)->{
            for (int i = 0; i < coords.size() - 1; i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    Coordinate coord1 = coords.get(i);
                    Coordinate coord2 = coords.get(j);
                    int dX = coord2.col - coord1.col;
                    int dY = coord2.row - coord1.row;
                    List<Coordinate> antinodeCandidates = new ArrayList<>();
                    antinodeCandidates.add(new Coordinate(coord2.row + dY, coord2.col + dX));
                    antinodeCandidates.add(new Coordinate(coord1.row - dY, coord1.col - dX));
                    for (Coordinate candidate : antinodeCandidates) {
                        if (candidate.col < 0 || candidate.col >= gridX) {
                            continue;
                        }
                        if (candidate.row < 0 || candidate.row >= gridY) {
                            continue;
                        }
                        antinodes.add(candidate.toString());
                    }
                }
            }
        });

        return Integer.toString(antinodes.size());
    }

    private boolean coordinateValid(Coordinate c, int gridX, int gridY) {
        if (c.col < 0 || c.col >= gridX) {
            return false;
        }
        return c.row >= 0 && c.row < gridY;
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
        int gridX = lines.getFirst().length();
        int gridY = lines.size();
        Map<Character,List<Coordinate>> antennae = new HashMap<>();
        for (int lineNum = 0; lineNum < gridY; lineNum++) {
            String line = lines.get(lineNum);
            for (int colNum = 0; colNum < gridX; colNum++) {
                char c = line.charAt(colNum);
                if (c == '.') {
                    continue;
                }
                Coordinate coord = new Coordinate(lineNum, colNum);
                if (!antennae.containsKey(c)) {
                    antennae.put(c, new ArrayList<>(Collections.singletonList(coord)));
                } else {
                    antennae.get(c).add(coord);
                }
            }
        }

        Set<String> antinodes = new HashSet<>();
        antennae.forEach((ignored,coords)->{
            for (int i = 0; i < coords.size() - 1; i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    Coordinate coord1 = coords.get(i);
                    Coordinate coord2 = coords.get(j);
                    int dx = coord2.col - coord1.col;
                    int dy = coord2.row - coord1.row;
                    // reduce the fraction dy/dx by finding the gcd
                    int gcd = gcd(dy, dx); // coord2 is guaranteed to be "greater than" coord 1 in both dimensions, so dy and dx can't be negative
//                    int gcd = Math.abs(gcd(dy, dx));
//                    if (gcd == 0) {
//
//                    }
//                    dx /= gcd;
//                    dy /= gcd;
                    int n = 0;
                    while (true) {
                        Coordinate candidate = new Coordinate(coord2.row + n*dy, coord2.col + n*dx);
                        if (coordinateValid(candidate, gridX, gridY)) {
                            antinodes.add(candidate.toString());
                        } else {
                            break;
                        }
                        n++;
                    }
                    n = 0;
                    while (true) {
                        Coordinate candidate = new Coordinate(coord1.row - n*dy, coord1.col - n*dx);
                        if (coordinateValid(candidate, gridX, gridY)) {
                            antinodes.add(candidate.toString());
                        } else {
                            break;
                        }
                        n++;
                    }
                }
            }
        });

        return Integer.toString(antinodes.size());
    }

    private int _gcdHelper(int a, int b) {
        if (b == 0) {
            return a;
        }
        return _gcdHelper(b, a%b);
    }

    private int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return _gcdHelper(a, b);
    }
}