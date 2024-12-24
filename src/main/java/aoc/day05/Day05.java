package aoc.day05;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
        String line = lines.get(0);
        int l = 0;
        Map<String, List<String>> rules = new HashMap<>(); // k -> [list of pages that can't come before]
        while (!line.isBlank()) {
            String[] ruleParts = line.split("\\|");
            if (!rules.containsKey(ruleParts[0])) {
                rules.put(ruleParts[0], new ArrayList<>());
            }
            rules.get(ruleParts[0]).add(ruleParts[1]);

            l++;
            line = lines.get(l);
        }

        int middleSum = 0;
        do {
            l++;
            line = lines.get(l);

            String[] update = line.split(",");
            List<String> seenPages = new ArrayList<>(update.length);
            boolean brokenRule = false;
            outerloop:
            for (String page : update) {
                List<String> pageAncestors = rules.getOrDefault(page, List.of());
                for (String seenPage : seenPages) {
                    for (String ancestor : pageAncestors) {
                        if (seenPage.equals(ancestor)) {
                            brokenRule = true;
                            break outerloop;
                        }
                    }
                }
                seenPages.add(page);
            }
            if (!brokenRule) {
                int middleNumber = Integer.parseInt(update[update.length / 2]);
                middleSum += middleNumber;
            }
        } while (l < lines.size() - 1);
        return Integer.toString(middleSum);
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
        String line = lines.get(0);
        int l = 0;
        Map<String, List<String>> rules = new HashMap<>(); // k -> [list of pages that can't come before]
        while (!line.isBlank()) {
            String[] ruleParts = line.split("\\|");
            if (!rules.containsKey(ruleParts[0])) {
                rules.put(ruleParts[0], new ArrayList<>());
            }
            rules.get(ruleParts[0]).add(ruleParts[1]);

            l++;
            line = lines.get(l);
        }

        int middleSum = 0;
        do {
            l++;
            line = lines.get(l);

            String[] update = line.split(",");
            List<String> seenPages = new ArrayList<>(update.length);
            boolean brokenRule = false;
            outerloop:
            for (String page : update) {
                List<String> pageAncestors = rules.getOrDefault(page, List.of());
                for (String seenPage : seenPages) {
                    for (String ancestor : pageAncestors) {
                        if (seenPage.equals(ancestor)) {
                            brokenRule = true;
                            break outerloop;
                        }
                    }
                }
                seenPages.add(page);
            }
            if (brokenRule) {
                // fix the ordering and find the middle number
                int middleNumber = Integer.parseInt(update[update.length / 2]);
                middleSum += middleNumber;
            }
        } while (l < lines.size() - 1);
        return Integer.toString(middleSum);
    }
}