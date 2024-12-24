package aoc.day07;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 implements Day {
    private interface Operator {
        long result(long a, long b);
    }

    private static class Multiplier implements Operator {
        public long result(long a, long b) {
            return a*b;
        }
    }

    private static class Adder implements Operator {
        public long result(long a, long b) {
            return a + b;
        }
    }

    private static class Concatenator implements Operator {
        public long result(long a, long b) {
            return Long.parseLong("" + a + b);
        }
    }

    private final Operator[] operators = {new Multiplier(), new Adder(), new Concatenator()};

    private void addIntermediates(long result, long nextOperand, long threshold, List<Long> intermediates) {
        long intermediate;
        for (Operator operator : operators) {
            intermediate = operator.result(result, nextOperand);
            if (intermediate <= threshold) {
                intermediates.add(intermediate);
            }
        }
    }

    private boolean canBeSolved(long result, long[] operands) {
        List<Long> oldIntermediates = new ArrayList<>();
        List<Long> newIntermediates = new ArrayList<>();
//        List<Long> oldIntermediates = new ArrayList<>((int) (Math.pow(3, operands.length - 2)));
//        List<Long> newIntermediates = new ArrayList<>((int) (Math.pow(3, operands.length - 2)));
        List<Long> temp;
        oldIntermediates.add(operands[0]);
        for (int i = 1; i < operands.length; i++) {
            for (long oldIntermediate : oldIntermediates) {
                addIntermediates(oldIntermediate, operands[i], result, newIntermediates);
            }
            temp = oldIntermediates;
            oldIntermediates = newIntermediates;
            newIntermediates = temp;
            newIntermediates.clear();
        }
        return oldIntermediates.contains(result);
    }

    @Override
    public String part1(String input) {
        long start = System.nanoTime();
        List<String> lines = Utils.splitLines(input);
        long solutions = 0;
        for (String line : lines) {
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
//            System.out.println(Arrays.toString(split[1].split(" ")));
            long[] operands = Arrays.stream(split[1].split(" ")).mapToLong(Long::parseLong).toArray();
            solutions += canBeSolved(result, operands) ? result : 0;
        }
        System.out.println(System.nanoTime() - start);
        return Long.toString(solutions);
    }

    @Override
    public String part2(String input) {
        return input;
    }
}