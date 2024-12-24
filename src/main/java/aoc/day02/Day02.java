package aoc.day02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import aoc.Day;
import aoc.Utils;

public class Day02 implements Day {

    private String calculate(String input, Function<List<Integer>, Boolean> isSafe) {
        int safeReports = 0;
        for (String report : Utils.splitLines(input)) {
            List<Integer> nums = Arrays.stream(report.split(" ")).map(Integer::parseInt).toList();
            if (isSafe.apply(nums)) {
                safeReports++;
            }
        }
        return Integer.toString(safeReports);
    }

    boolean isSafe1(List<Integer> nums) {
        int prev = nums.get(0);
        boolean isAsc = nums.get(1) > nums.get(0);
        for (int i = 1; i < nums.size(); ++i) {
            int cur = nums.get(i);
            if (isAsc != (cur > prev)) {
                return false;
            }
            int dif = Math.abs(cur - prev);
            if (dif < 1 || dif > 3) {
                return false;
            }
            prev = cur;
        }
        return true;
    }

    @Override
    public String part1(String input) {
        return calculate(input, this::isSafe1);
    }

    boolean naiveIsSafe2(List<Integer> nums) {
        int prev = nums.get(0);
        boolean isAsc = nums.get(1) > nums.get(0);
        int safe = 2;
        for (int i = 1; i < nums.size(); ++i) {
            int cur = nums.get(i);
            if (isAsc != (cur > prev)) {
                safe -= 1;
                if (safe == 0) {
                    return false;
                }
                continue;
            }
            int dif = Math.abs(cur - prev);
            if (dif < 1 || dif > 3) {
                safe -= 1;
                if (safe == 0) {
                    return false;
                }
                continue;
            }
            prev = cur; // TODO insufficient: in 54, 58, 61, you want to discard *54*, not 58!
        }
        return true;
    }

    @Override
    public String part2(String input) {
        // 100 0 1 2 3
        // 100
        // 0    magnitude problem 0->1, descIndex 0
        // 1    ascIndex 1
        
        // 100 0 1 0
        // same as above until
        // 0    descIndex 2
        //    descIndex was 0, do we complain at 2? no, it's the 2nd # so we say "it's a descending sequence", so if we found a 2nd ascending, we could return false
        // one thing we could do:
        // if we find 2+ asc & 2+ desc, return false
        // 0 100 1 2
        // 1 0 2 3
        // isAsc:
        return calculate(input, (nums) -> {
            // return naiveIsSafe2(nums);
            // return naiveIsSafe2(nums) || naiveIsSafe2(nums.reversed());
            return naiveIsSafe2(nums) || isSafe1(nums.subList(1,nums.size()));
        });
    }
}
