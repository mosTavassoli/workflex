package com.workflex.practice;

import java.util.*;

public class StringPermutation {
    public List<String> stringPermutation(String input) {
        if (input == null) return List.of();
        if (input.isEmpty()) return List.of("");


        List<String> res = new ArrayList<>();

        permutationRec(input.toCharArray(), 0, res);

        return res;

    }

    private void permutationRec(char[] input, int idx, List<String> res){
        if(idx == input.length){
            res.add(new String(input));
            return;
        }

        Set<Character> used = new HashSet<>();

        for(int i = idx; i < input.length; i++){

            if (used.contains(input[i])) {
                continue; // skip duplicate at this level
            }
            used.add(input[i]);

            swap(input, idx, i);
            permutationRec(input, idx + 1, res);
            swap(input, idx, i);
        }
    }

    private void swap(char[] input, int i, int j){
        char t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
}
