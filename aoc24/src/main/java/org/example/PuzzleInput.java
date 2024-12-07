package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInput {
    public static List<String> readInput(String filename, boolean partB) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File("aoc24\\src\\main\\resources\\" + filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        int emptyLinesCount = 0;
        if(!partB){
            while ((line = br.readLine()) != null && emptyLinesCount==0){
                if(line.isEmpty()){
                    emptyLinesCount++;
                    break;
                }
                list.add(line);
            }
        }
        if(partB){
            while((line = br.readLine()) != null && emptyLinesCount < 2){
                if(emptyLinesCount > 0) list.add(line);
                if(line.isEmpty()) emptyLinesCount++;
            }
        }

        return list;
    }
}
