package com.workflex.practice;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null || nums.length ==0) return List.of(List.of());

        List<List<Integer>> result = new ArrayList<>();
        subsetsRec(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void subsetsRec(int[] nums, int idx, List<Integer> currSub, List<List<Integer>> result){
        if(idx == nums.length){
            result.add(new ArrayList<>(currSub));
            return;
        }

        subsetsRec(nums, idx + 1, currSub, result);
        List<Integer> newSub = new ArrayList<>(currSub);
        newSub.add(nums[idx]);
        subsetsRec(nums, idx + 1, newSub, result);
    }
}
