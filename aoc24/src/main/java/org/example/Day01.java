package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

    public static Integer partOne() throws IOException {
        String regexPattern = " {3}";
        Integer count = 0;
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        List<String> fullList = PuzzleInput.readInput("Day01.txt");
        for(String line : fullList){
            listA.add(Integer.parseInt(line.split(regexPattern)[0]));
            listB.add(Integer.parseInt(line.split(regexPattern)[1]));
        }

        listA.sort(Integer::compare);
        listB.sort(Integer::compare);

        for(int i = 0; i < listA.size(); i++){
            count += Math.abs(listA.get(i) - listB.get(i));
        }
        return count;
    }
}
