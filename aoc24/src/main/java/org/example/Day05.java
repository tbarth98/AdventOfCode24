package org.example;

import java.io.IOException;
import java.util.*;

public class Day05 {
    public static int partOne() throws IOException {
        int total = 0;
        List<String> orderRules = PuzzleInput.readInput("Day05.txt", false);
        List<String> pages = PuzzleInput.readInput("Day05.txt", true);

        Map<String, Set<String>> pageOrder = new HashMap<>();


        for (String order : orderRules){
            String[] nums = order.split("\\|");
            Set<String> set = pageOrder.get(nums[0]);
            if(set == null){
                set = new HashSet<>();
            }
            set.add(nums[1]);
            pageOrder.put(nums[0], set);
        }

        for (String page : pages){
            boolean isValid = true;
            String[] nums = page.split(",");
            for (int i = 0; i < nums.length-1; i++){
                if (pageOrder.get(nums[i]) == null ||  !pageOrder.get(nums[i]).contains(nums[i+1])){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                total += Integer.parseInt(nums[nums.length/2]);
            }
        }
        return total;
    }

    public static int partTwo() throws IOException {
        int total = 0;
        List<String> orderRules = PuzzleInput.readInput("Day05.txt", false);
        List<String> pages = PuzzleInput.readInput("Day05.txt", true);

        Map<String, Set<String>> pageOrder = new HashMap<>();


        for (String order : orderRules){
            String[] nums = order.split("\\|");
            Set<String> set = pageOrder.get(nums[0]);
            if(set == null){
                set = new HashSet<>();
            }
            set.add(nums[1]);
            pageOrder.put(nums[0], set);
        }

        for (String page : pages){
            boolean isValid = true;
            String[] nums = page.split(",");
            for (int i = 0; i < nums.length-1; i++){
                if (pageOrder.get(nums[i]) == null ||  !pageOrder.get(nums[i]).contains(nums[i+1])){
                    isValid = false;
                    break;
                }
            }
            if(!isValid){
                Arrays.sort(nums, (a, b) -> checkOrder(pageOrder, b, a));
                total += Integer.parseInt(nums[nums.length/2]);
            }
        }

        // after finished check perfect solution https://www.geeksforgeeks.org/java-program-for-topological-sorting/
        return total;
    }

    private static int checkOrder(Map<String,Set<String>> data, String key, String value){
        if(data.get(key) != null &&  data.get(key).contains(value)){
            return 1;
        }
        return -1;
    }
}
