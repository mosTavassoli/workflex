package com.workflex.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoSumWithTarget {
    public List<List<Integer>> twoSum(int[] nums, int target) {
        // sum(x,y) == target === pair(x,y)
        // no sort

        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> unique = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        for(int num : nums){
            int complement = target - num;

            if(seen.contains(complement)){
                List<Integer> list = List.of(Math.min(num, complement), Math.max(complement, num));
                if(unique.add(list)){
                    result.add(list);
                }
            }

            seen.add(num);

        }

        return result;
    }
}


//Complexity
//Time: O(n)
//Space: O(n)
