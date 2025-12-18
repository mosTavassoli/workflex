package com.workflex.practice;

public class SwapArrayInPlace {
    public void swapArrayInPlace(int[] nums) {
        if(nums.length <=1){
            return;
        }
        swapArrayInPlaceRec(nums, 0);

    }

    private void swapArrayInPlaceRec(int[] nums, int idx){
        if(idx >= nums.length / 2){
            return;
        }

        int t = nums[idx];
        nums[idx] = nums[nums.length - idx - 1];
        nums[nums.length - idx - 1] = t;

        swapArrayInPlaceRec(nums, idx + 1   );
    }

}
