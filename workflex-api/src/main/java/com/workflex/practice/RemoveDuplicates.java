package com.workflex.practice;

class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        if (nums.length == 0) return 0;

        for (int fast = 1; fast<nums.length; fast++){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }
}
