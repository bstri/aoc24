package aoc.day06;

import aoc.Day;
import aoc.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day06 implements Day {
    private String convertCoordinateToString(int row, int col) {
        return "%d_%d".formatted(row,col);
    }

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
        int guardX = -1;
        int guardY = -1;
        char guardChar = '0';
        outerLoop:
        for (int row = 0, m = lines.size(); row < m; row++) {
            String line = lines.get(row);
            for (int col = 0, n = line.length(); col < n; col++) {
                char c = line.charAt(col);
                if (c == '<' || c == '>' || c == 'v' || c == '^') {
                    guardY = row;
                    guardX = col;
                    guardChar = c;
                    break outerLoop;
                }
            }
        }
        Set<String> positions = new HashSet<>();
        try {
            while (true) {
                positions.add(convertCoordinateToString(guardY, guardX));
                if (guardChar == 'v') {
                    if (lines.get(guardY + 1).charAt(guardX) == '#') {
                        guardChar = '<';
                        continue;
                    }
                    guardY += 1;
                } else if (guardChar == '<') {
                    if (lines.get(guardY).charAt(guardX - 1) == '#') {
                        guardChar = '^';
                        continue;
                    }
                    guardX -= 1;
                } else if (guardChar == '^') {
                    if (lines.get(guardY - 1).charAt(guardX) == '#') {
                        guardChar = '>';
                        continue;
                    }
                    guardY -= 1;
                } else if (guardChar == '>') {
                    if (lines.get(guardY).charAt(guardX + 1) == '#') {
                        guardChar = 'v';
                        continue;
                    }
                    guardX += 1;
                }
            }
        } catch (Exception e) {
            return Integer.toString(positions.size());
        }
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
        final int initGuardX;
        final int initGuardY;
        final char initGuardChar;
        int guardX = -1;
        int guardY = -1;
        char guardChar = '0';
        outerLoop:
        for (int row = 0, m = lines.size(); row < m; row++) {
            String line = lines.get(row);
            for (int col = 0, n = line.length(); col < n; col++) {
                char c = line.charAt(col);
                if (c == '<' || c == '>' || c == 'v' || c == '^') {
                    guardY = row;
                    guardX = col;
                    guardChar = c;
                    break outerLoop;
                }
            }
        }
        initGuardX = guardX;
        initGuardY = guardY;
        initGuardChar = guardChar;
        Set<String> positions = new HashSet<>();
        try {
            while (true) {
                positions.add(convertCoordinateToString(guardY, guardX));
                if (guardChar == 'v') {
                    if (lines.get(guardY + 1).charAt(guardX) == '#') {
                        guardChar = '<';
                        continue;
                    }
                    guardY += 1;
                } else if (guardChar == '<') {
                    if (lines.get(guardY).charAt(guardX - 1) == '#') {
                        guardChar = '^';
                        continue;
                    }
                    guardX -= 1;
                } else if (guardChar == '^') {
                    if (lines.get(guardY - 1).charAt(guardX) == '#') {
                        guardChar = '>';
                        continue;
                    }
                    guardY -= 1;
                } else if (guardChar == '>') {
                    if (lines.get(guardY).charAt(guardX + 1) == '#') {
                        guardChar = 'v';
                        continue;
                    }
                    guardX += 1;
                }
            }
        } catch (Exception ignored) {

        }
        System.out.printf("num positions = %d\n", positions.size());
        int numPossibilities = 0;
        List<String> positionArray = positions.stream().toList();
        for (int i = 1; i < positions.size(); i++) {
            String pos = positionArray.get(i);
            String[] coordinate = pos.split("_");
            int lineNum = Integer.parseInt(coordinate[0]);
            String line = lines.get(lineNum);
            char[] lineC = line.toCharArray();
            lineC[Integer.parseInt(coordinate[1])] = '#';
            String newline = new String(lineC);
            lines.set(lineNum, newline);
//            System.out.println(lines);

            numPossibilities += checkForLoop(lines, initGuardChar, initGuardY, initGuardX) ? 1 : 0; // is infinite loop?

            lines.set(lineNum, line);
        }
        return Integer.toString(numPossibilities);
    }

    private boolean checkForLoop(List<String> lines, char guardChar, int guardY, int guardX) {
        Set<String> positions = new HashSet<>();
        try {
            while (true) {
//                System.out.println();
                String key = "%d_%d_%s".formatted(guardY, guardX, guardChar);
                if (positions.contains(key)) {
                    return true;
                }
                positions.add(key);
//                System.out.printf("%s__",positions);
                if (guardChar == 'v') {
                    if (lines.get(guardY + 1).charAt(guardX) == '#') {
                        guardChar = '<';
                        continue;
                    }
                    guardY += 1;
                } else if (guardChar == '<') {
                    if (lines.get(guardY).charAt(guardX - 1) == '#') {
                        guardChar = '^';
                        continue;
                    }
                    guardX -= 1;
                } else if (guardChar == '^') {
                    if (lines.get(guardY - 1).charAt(guardX) == '#') {
                        guardChar = '>';
                        continue;
                    }
                    guardY -= 1;
                } else if (guardChar == '>') {
                    if (lines.get(guardY).charAt(guardX + 1) == '#') {
                        guardChar = 'v';
                        continue;
                    }
                    guardX += 1;
                }
            }
        } catch (Exception ignored) {
            return false;
        }
    }
}