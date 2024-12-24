package aoc.day04;

import aoc.Day;
import aoc.Utils;
import java.util.List;

public class Day04 implements Day {
    private int numLines;
    private int numCols;

    int[][] searchMatrix = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

    // 0,1 right
    // 0,-1 left
    // 1,0 down
    // -1,0 up
    // 1,1 down right
    // 1,-1 down left
    // -1,1 up right
    // -1,-1 up left

    private boolean search(List<String> input, String match, int line, int col, int lineCoef, int colCoef) {
        try {
            for (int c = 0; c < match.length(); c++) {
                if (input.get(line + c*lineCoef).charAt(col + c*colCoef) != match.charAt(c)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
        numLines = lines.size();
        numCols = lines.getFirst().length();
        int captures = 0;
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numCols; j++) {
                if (lines.get(i).charAt(j) == 'X') {
                    for (int[] pair : searchMatrix) {
                        if (search(lines, "XMAS", i, j, pair[0], pair[1])) {
                            captures += 1;
                        }
                    }
                }
            }
        }
        return Integer.toString(captures);
    }

    private boolean findXMas(List<String> input, int line, int col) {
        String[] matches = {"MAS", "SAM"};
        boolean topFound = false;
        for (String match : matches) {
            int i = 0;
            while (i < match.length()) {
                if (input.get(line + i).charAt(col + i) != match.charAt(i)) {
                    break;
                }
                i++;
            }
            if (i == match.length()) {
                topFound = true;
                break;
            }
        }
        if (!topFound) {
            return false;
        }
        for (String match : matches) {
            int i = 0;
            while (i < match.length()) {
                int l = line + match.length() - 1 - i;
                int c = col + i;
                char searchChar = input.get(l).charAt(c);
                if (searchChar != match.charAt(i)) {
                    break;
                }
                i++;
            }
            if (i == match.length()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
        numLines = lines.size();
        numCols = lines.getFirst().length();
        int captures = 0;
        for (int i = 0; i < numLines - 2; i++) {
            for (int j = 0; j < numCols - 2; j++) {
                captures += findXMas(lines, i, j) ? 1 : 0;
            }
        }
        return Integer.toString(captures);
    }
}