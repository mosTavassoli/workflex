package com.workflex.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> result = new HashSet<>();
        if(nums == null) {
            return List.of();
        }

        for(int i = 0; i < nums.length; i++){
            int idx = Math.abs(nums[i]) - 1;

            if(nums[idx] > 0){
                nums[idx] *= -1;
            } else{
                result.add(Math.abs(nums[i]));
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return new ArrayList<>(result);
    }

    public static class LengthOfLongestSubstring {
    }
}
