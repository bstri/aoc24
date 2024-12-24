package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Day {
    ArrayList<Integer> firstList;
    ArrayList<Integer> secondList;

    private void createSortedLists(String input) {
        List<String> lines = Utils.splitLines(input);
        firstList = new ArrayList<>(lines.size());
        secondList = new ArrayList<>(lines.size());
        lines.forEach(s-> {
            String[] entries = s.split("\\s+");
            firstList.add(Integer.parseInt(entries[0]));
            secondList.add(Integer.parseInt(entries[1]));
        });
        Collections.sort(firstList);
        Collections.sort(secondList);
    }

    @Override
    public String part1(String input) {
        createSortedLists(input);
        Integer distance = 0;
        for (int i = 0; i < firstList.size(); i++){
            distance += Math.abs(firstList.get(i) - secondList.get(i));
        }
        return distance.toString();
    }

    @Override
    public String part2(String input) {
        createSortedLists(input);
        Integer similarity = 0;
        int firstListIndex = 0;
        for (int i = 0; i < secondList.size(); i++) {
            if (secondList.get(i) == firstList.get(firstListIndex)) {
                similarity += secondList.get(i);
                
            } else {
                
            }
        }
        return input;
    }

}
