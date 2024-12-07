package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    enum SortOrder {
        ASC,
        DESC
    }

    public static Integer partOne() throws IOException {
        int safeReports = 0;
        List<String> reports = PuzzleInput.readInput("Day02.txt", false);

        for (String report : reports) {
            List<Integer> levels = Arrays.stream(report.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            if(checkIsSafe(levels))safeReports++;
        }
        return safeReports;
    }

    public static Integer partTwo() throws IOException {
        int safeReports = 0;
        List<String> reports = PuzzleInput.readInput("Day02.txt", false);

        for (String report : reports) {
            List<Integer> levels = Arrays.stream(report.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            for (int i = 0; i < levels.size(); i++){
                List<Integer> shortenedList = new ArrayList<>(levels);
                shortenedList.remove(i);
                if(checkIsSafe(shortenedList)) {safeReports++; break;}
            }
        }
        return safeReports;
    }

    private static boolean checkIsSafe(List<Integer> list){
        boolean isSafe = true;
        SortOrder sortOrder = null;

        //-1 since there is no need to compare the last level to anything
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i+1))) {
                isSafe = false;
                break;
            }
            if (i == 0 && checkOrder(list.get(i), list.get(i+1), SortOrder.DESC)) sortOrder = SortOrder.DESC;
            if (i == 0 && checkOrder(list.get(i), list.get(i+1), SortOrder.ASC)) sortOrder = SortOrder.ASC;
            if (!checkOrder(list.get(i), list.get(i+1), sortOrder) || !checkInRange(list.get(i), list.get(i+1))) {
                isSafe = false;
                break;
            }
        }
    return isSafe;
    }

    private static boolean checkOrder(int value1, int value2, SortOrder sortOrder) {
        if (sortOrder == SortOrder.DESC) {
            return value1 > value2;
        }
        if (sortOrder == SortOrder.ASC) {
            return value1 < value2;
        }
        return false;
    }

    private static boolean checkInRange(int value1, int value2) {
        int absDifference = Math.abs(value1 - value2);
        return 1 <= absDifference && absDifference <= 3;
    }
}
