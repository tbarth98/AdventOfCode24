package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day07 {

    public static long partOne() throws IOException {
        List<String> input = PuzzleInput.readInput("Day07.txt", false);
        long total = 0L;

        for (String line : input){
            long result = Long.parseLong(line.split(": | ")[0]);
            long[] numbers = Arrays.stream(line.substring(line.indexOf(": ")+2).split(": | ")).mapToLong(Long::parseLong).toArray();
            total += calcAll(numbers, 1, numbers[0], result, false);
        }
        return total;
    }

    public static long partTwo() throws IOException {
        List<String> input = PuzzleInput.readInput("Day07.txt", false);
        long total = 0L;

        for (String line : input){
            long result = Long.parseLong(line.split(": | ")[0]);
            long[] numbers = Arrays.stream(line.substring(line.indexOf(": ")+2).split(": | ")).mapToLong(Long::parseLong).toArray();
            total += calcAll(numbers, 1, numbers[0], result, true);
        }

        return total;
    }

    private static long calcAll(long[] numbers, int nextNumIndex, long prev, long result, boolean useConcat) {
        if (nextNumIndex == numbers.length && prev == result) return prev;
        if (nextNumIndex >= numbers.length || prev > result) return 0L;

        long concat = 0L;
        if (useConcat) {
            concat = calcAll(numbers, nextNumIndex + 1, Long.parseLong(Long.toString(prev).concat(Long.toString(numbers[nextNumIndex]))), result, useConcat);
        }

        long add = calcAll(numbers, nextNumIndex + 1, prev + numbers[nextNumIndex], result, useConcat);
        long mult = calcAll(numbers, nextNumIndex + 1, prev * numbers[nextNumIndex], result, useConcat);

        if (add == result) return add;
        if (mult == result) return mult;
        if (concat == result) return concat;
        return 0L;
    }
}
