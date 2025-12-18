package com.workflex.practice;

import java.util.*;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        //Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]

//        Set<String> bannSet = new HashSet<>(Arrays.asList(banned));
    
        Set<String> bannSet = new HashSet<>();
        Map<String, Integer> myMap = new HashMap<>();
        String[] paraArr = paragraph.toLowerCase().split("\\W+");


        for(String word : banned){
            bannSet.add(word.toLowerCase());
        }

        for(String word : paraArr) {
            if (!bannSet.contains(word)) {
                myMap.put(word, myMap.getOrDefault(word, 0) + 1);
            }
        }

        int maxVal = Integer.MIN_VALUE;
        String maxWord = "";

        for(Map.Entry<String, Integer> item : myMap.entrySet()) {
            if (item.getValue() > maxVal) {
                maxVal = item.getValue();
                maxWord = item.getKey();
            }
        }

        return maxWord;

//        return Collections.max(
//                myMap.entrySet(),
//                Map.Entry.comparingByValue()
//        ).getKey();

    }
}
