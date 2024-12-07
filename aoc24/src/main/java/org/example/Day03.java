package org.example;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static int partOne() throws IOException {
        int total = 0;
        List<String> input = PuzzleInput.readInput("Day03.txt", false);
        for (String line : input) {
            /*
             * Matcher Groups
             * Group0 = mul(%d,%d)
             * Group1 = num one of mull(%d,%d)
             * Group2 = num two of mull(%d,%d)
             */
            Matcher matcher = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").matcher(line);
            while (matcher.find()) {
                total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        return total;
    }

    public static int partTwo() throws IOException {
        boolean multiply = true;
        int total = 0;
        List<String> input = PuzzleInput.readInput("Day03.txt", false);
        for (String line : input) {
            /*
             * Matcher Groups
             * Group0 = "do()" or "don't()" or mul(%d,%d)
             * Group1 = num one of mull(%d,%d)
             * Group2 = num two of mull(%d,%d)
             */
            Matcher matcher = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\)").matcher(line);
            while (matcher.find()) {
                if (matcher.group(0).equals("don't()")) {
                    multiply = false;
                    continue;
                }
                if (matcher.group(0).equals("do()")) {
                    multiply = true;
                    continue;
                }
                if (multiply) {
                    total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                }
            }
        }
        return total;
    }
}
