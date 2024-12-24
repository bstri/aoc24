package aoc.day10;

import aoc.Board;
import aoc.Day;
import aoc.Utils;

import java.awt.*;
import java.util.List;

public class Day10 implements Day {

    @Override
    public String part1(String input) {
        Board map = new Board(input);

        int score = 0;
        Point trailHead = null;
        while ((trailHead = map.find('0', trailHead)) != null) {
            System.out.printf("trailhead at %s\n", trailHead);

            // BFS starting from trailheads, finding 9,
            // nodes only point to nodes 1 greater than them
            // mark points as "visited" to avoid repeating work
            // for every unique 9 found, increment score

//            trailHead = map.find('0', trailHead);
        }

        return Integer.toString(score);
    }

    @Override
    public String part2(String input) {
        int score = 0;
        // same as part 1 except don't mark nodes as visited?
        // there's still no need to explore that path twice..
        // instead of boolean visited, have int visits that gets incremented
        // when going from one node to an adjacent one, add the parent's visits to the child's
        // when finding a 9, add the visits to score instead of adding 1
        return Integer.toString(score);
    }
}