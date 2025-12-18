package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaximumSubarrayTest {

    private final MaximumSubarray maximumSubarray = new MaximumSubarray();

    @Test
    void maximumSubarray_basicCase(){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        int res = maximumSubarray.maxSubArray(nums);

        assertEquals(6, res);

    }

    @Test
    void returnZeroIfArrayIsEmpty(){
        assertEquals(0, maximumSubarray.maxSubArray(new int[]{}));
    }

    @Test
    void allNegativeNumber(){
        int[] nums = {-8, -3, -6, -2, -5, -4};

        assertEquals(-2, maximumSubarray.maxSubArray(nums));
    }

    @Test
    void allPositiveNumbers(){
        int[] nums =  {1, 2, 3, 4};

        assertEquals(10, maximumSubarray.maxSubArray(nums));
    }

    @Test
    void onlyOneElement(){
        assertEquals(5, maximumSubarray.maxSubArray(new int[]{5}));
    }

    @Test
    void nullArray_returnsZero() {
        assertEquals(0, maximumSubarray.maxSubArray(null));
    }
}
