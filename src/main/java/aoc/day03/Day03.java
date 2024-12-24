package aoc.day03;

import aoc.Day;
import aoc.Utils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {

    @Override
    public String part1(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        int sum = 0;
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return Integer.toString(sum);
    }

    @Override
    public String part2(String input) {
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");
        int sum = 0;
        Matcher matcher = pattern.matcher(input);
        boolean enabled = true;
        while (matcher.find()) {
            if (matcher.group(0).equals("do()")) {
                enabled = true;
                continue;
            } else if (matcher.group(0).equals("don't()")) {
                enabled = false;
                continue;
            }
            if (enabled) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        return Integer.toString(sum);
    }
}