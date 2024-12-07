package org.example;

import java.io.IOException;
import java.util.*;

public class Day06 {
    static final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // Up, Right, Down, Left

    public static int partOne() throws IOException {
        List<String> map = PuzzleInput.readInput("Day06.txt", false);
        int[] guardPosition = getStartPosition(map);
        Guard guard = new Guard(guardPosition[0], guardPosition[1], 0);

        while(guard.onMap(map)){
            guard.move(map);
        }
        return guard.getVisitedPositions().size();
    }

    public static int partTwo() throws IOException{
        List<String> map = PuzzleInput.readInput("Day06.txt", false);
        Set<Position> uniqueObstructions = new HashSet<>();
        int[] guardStartPosition = getStartPosition(map);
        Guard guard = new Guard(guardStartPosition[0], guardStartPosition[1], 0);

        while(guard.onMap(map)){
            guard.move(map);
            Position possibleObstruction = checkPossibleObstructionAhead(map, guard.getX(), guard.getY(), guard.getDirection());
            if(possibleObstruction != null && !guard.getVisitedPositions().contains(possibleObstruction)) {
                uniqueObstructions.add(possibleObstruction);
                System.out.println(possibleObstruction);
                }
            }
        return uniqueObstructions.size();
        }


    private static Position checkPossibleObstructionAhead(List<String> map , int x, int y, int guardDirection){
        List<String> testMap = new ArrayList<>(map);
        Guard loopGuard = new Guard(x, y, guardDirection);
        Position positionToCheck = new Position (x+directions[loopGuard.getDirection()][0],y+directions[loopGuard.getDirection()][1]);

        try{
            if(testMap.get(positionToCheck.y()).charAt(positionToCheck.x()) != '#'){
                String testString = testMap.get(positionToCheck.y());
                testString = testString.substring(0, positionToCheck.x()) + '#' + testString.substring(positionToCheck.x()+1);
                testMap.set(positionToCheck.y(), testString);
                while(loopGuard.onMap(testMap) && loopGuard.getRevisitCount() < 1000){
                    loopGuard.move(testMap);
                }
                if(loopGuard.getRevisitCount() >= 1000){
                    return positionToCheck;
                }
            }
        } catch (RuntimeException e) {
            return null;
        }
        return null;
    }

    private static int[] getStartPosition(List<String> data){
        for (int i = 0; i < data.size(); i++){
           if(data.get(i).contains("^")){
               return new int[]{data.get(i).indexOf('^'), i};
           }
        }
        throw new RuntimeException("No Guard found");
    }


}

 class Guard{
    private int x;
    private int y;
    private int direction;
    private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // Up, Right, Down, Left
    private final Set<Position> visitedPositions = new HashSet<>();
    private int revisitCount = 0;

    public Guard(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnRight(){
        direction = ((direction + 1 ) % 4);
    }

    public void move(List<String> map){
        int nextX = x + directions[getDirection()][0];
        int nextY = y + directions[getDirection()][1];

        if(onMap(map, nextX, nextY) && map.get(nextY).charAt(nextX) == '#'){
            turnRight();
        }else{
            x = nextX;
            y = nextY;
            if (onMap(map, nextX, nextY)){
                boolean isFirstVisit = visitedPositions.add(new Position(x, y));
                if (!isFirstVisit) revisitCount++;
            }
        }


    }

    public boolean onMap(List<String> map){
        return this.x < map.getFirst().length() && this.x >= 0 && this.y < map.size() && this.y >= 0;
    }

     public boolean onMap(List<String> map, int x, int y){
         return x < map.getFirst().length() && x >= 0 && y < map.size() && y >= 0;
     }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public Set<Position> getVisitedPositions(){
        return visitedPositions;
    }

     public int getRevisitCount() {
         return revisitCount;
     }
 }

record Position(int x, int y){}
