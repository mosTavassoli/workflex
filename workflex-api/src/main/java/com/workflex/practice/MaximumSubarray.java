package com.workflex.practice;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // kadanes algr
        if (nums == null || nums.length ==0 ) return 0;

        int currMax = nums[0];
        int bestMax = nums[0];

        for(int i = 1; i < nums.length; i++){

            currMax = Math.max(nums[i], currMax + nums[i]);
            bestMax = Math.max(currMax, bestMax);
        }

        return bestMax;
    }
}
