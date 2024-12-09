package org.example;

import java.io.IOException;
import java.util.*;

public class Day08 {
    public static int partOne() throws IOException {
        List<String> map = PuzzleInput.readInput("Day08.txt", false);
        Map<Character, List<Position>> antennaPositions = new HashMap<>();
        Set<Position> uniqueAntinodePositions = new HashSet<>();

        findAntennas(map, antennaPositions);
        findAllAntinodes(map, antennaPositions, uniqueAntinodePositions, false);

        return uniqueAntinodePositions.size();
    }

    public static int partTwo() throws IOException {
        List<String> map = PuzzleInput.readInput("Day08.txt", false);
        Map<Character, List<Position>> antennaPositions = new HashMap<>();
        Set<Position> uniqueAntinodePositions = new HashSet<>();

        findAntennas(map, antennaPositions);

        //add all antennas with more than one antenna of same type on the map, since they are an antinode aswell now
        for (List<Position> antennas : antennaPositions.values()){
            if(antennas.size() > 1){
                uniqueAntinodePositions.addAll(antennas);
            }
        }
        findAllAntinodes(map, antennaPositions, uniqueAntinodePositions, true);

        return uniqueAntinodePositions.size();
    }

    private static void findAntennas(List<String> map, Map<Character, List<Position>> antennaPositions){
        final String matchChar = "[a-zA-Z0-9]";
        for (int y = 0; y < map.size(); y++){
            for (int x = 0; x < map.get(y).length(); x++){
                char c = map.get(y).charAt(x);
                if (Character.toString(c).matches(matchChar)){
                    addToAntennaPositions(antennaPositions, c, new Position(x, y));
                }
            }
        }
    }

    private static void findAllAntinodes(List<String> map, Map<Character, List<Position>> antennaPositions, Set<Position> uniqueAntinodePositions, boolean withResonantHarmonics){
        for (List<Position> antennas : antennaPositions.values()){
            for (int i = 0; i < antennas.size(); i++){
                for (int j = 0; j < antennas.size(); j++){
                    if(antennas.get(i).equals(antennas.get(j))) continue;
                    Position possibleAntinode = new Position(antennas.get(i).x() + (antennas.get(i).x() - antennas.get(j).x()), antennas.get(i).y() + (antennas.get(i).y() - antennas.get(j).y()));
                    if(!isOnMap(map, possibleAntinode)) continue;
                    uniqueAntinodePositions.add(possibleAntinode);
                    int multiplier = 2;
                    Position test;
                    while (withResonantHarmonics && isOnMap(map, test = new Position(antennas.get(i).x() + (antennas.get(i).x() - antennas.get(j).x())*multiplier, antennas.get(i).y() + (antennas.get(i).y() - antennas.get(j).y())*multiplier))){
                        uniqueAntinodePositions.add(test);
                        multiplier++;
                    }
                }
            }
        }
    }

    private static void addToAntennaPositions(Map<Character, List<Position>> map, char key, Position value){
        List<Position> list = map.get(key);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(value);
        map.put(key, list);
    }

    public static boolean isOnMap(List<String> map, Position position){
        return position.x() < map.getFirst().length() && position.x() >= 0 && position.y() < map.size() && position.y() >= 0;
    }

    record Position(int x, int y){}
}

