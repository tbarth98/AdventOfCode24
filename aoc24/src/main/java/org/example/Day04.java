package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04 {

    public static int partOne() throws IOException {
        int total = 0;
        List<String> input = PuzzleInput.readInput("Day04.txt");
        //find X
        for(int lineNum = 0; lineNum < input.size(); lineNum++) {
            List<Integer> allXIndexOfLine = getAllIndexOfLetter('X', input.get(lineNum));
            for(int index : allXIndexOfLine) {
                    total += checkMAS(input, index, lineNum, 1 , 0); //right
                    total += checkMAS(input, index, lineNum, -1 , 0); //left
                    total += checkMAS(input, index, lineNum, 0 , -1); //up
                    total += checkMAS(input, index, lineNum, 0 , 1); //down
                    total += checkMAS(input, index, lineNum, 1 , -1); //upright
                    total += checkMAS(input, index, lineNum, -1 , -1); //upleft
                    total += checkMAS(input, index, lineNum, 1 , 1); //downright
                    total += checkMAS(input, index, lineNum, -1 , 1); //downleft
            }
        }
            //at X search for M in every direction
                //M found -> look for next letter in same direction
                //no M found -> ignore X
        return total;
    }

    public static int partTwo() throws IOException{
        int total = 0;
        List<String> input = PuzzleInput.readInput("Day04.txt");
        for(int lineNum = 0; lineNum < input.size(); lineNum++) {
            List<Integer> allXIndexOfLine = getAllIndexOfLetter('A', input.get(lineNum));
            for(int index : allXIndexOfLine){
                total += checkXMAS(input, index, lineNum);
            }

        }
        return total;
    }

    private static int checkMAS(List<String> data, int x, int y, int xDir, int yDir){
        try{
            if (data.get(y+1*yDir).charAt(x+1*xDir) == 'M' && data.get(y+2*yDir).charAt(x+2*xDir) == 'A' && data.get(y+3*yDir).charAt(x+3*xDir) == 'S'){
                return 1;
            }
        }catch(Exception ignored){}
        return 0;
    }

    private static int checkXMAS(List<String> data, int x, int y){
        try{
            if (((data.get(y-1).charAt(x-1) == 'M' && data.get(y+1).charAt(x+1) == 'S') || (data.get(y-1).charAt(x-1) == 'S' && data.get(y+1).charAt(x+1) == 'M')) &&
                    ((data.get(y-1).charAt(x+1) == 'M' && data.get(y+1).charAt(x-1) == 'S') || (data.get(y-1).charAt(x+1) == 'S' && data.get(y+1).charAt(x-1) == 'M'))){
                return 1;
            }
        }catch(Exception ignored){}
        return 0;
    }

    private static List<Integer> getAllIndexOfLetter(char letter, String string) {
        ArrayList<Integer> allIndex = new ArrayList<>();
        int index = string.indexOf(letter);
        if(index >= 0) allIndex.add(index);
        while (index >= 0) {
            index = string.indexOf(letter, index + 1);
            if(index >= 0) allIndex.add(index);
        }
        return allIndex;
    }
}