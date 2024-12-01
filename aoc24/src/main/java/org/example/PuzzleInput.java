package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInput {
    public static List<String> readInput(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File("aoc24\\src\\main\\resources\\" + filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null){
            list.add(line);
        }
        return list;
    }
}
