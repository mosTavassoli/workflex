package com.workflex.practice;

public class RotateArrayWithK {
    public void rotate(int[] nums, int k){
        if(nums == null || nums.length ==0){
            return;
        }

        k = k % nums.length;
        if (k == 0) return;

        swap(nums, 0, nums.length - 1);
        swap(nums, 0, k - 1);
        swap(nums, k, nums.length - 1);

    }


    private void swap(int[] nums, int left, int right){
        while(left < right){
            int t = nums[left];
            nums[left] = nums[right];
            nums[right] = t;

            left++;
            right--;
        }
    }
}
