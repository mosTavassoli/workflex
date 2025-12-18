package com.workflex.practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TwoSumWithTargetTest {

    private final TwoSumWithTarget twoSumWithTarget = new TwoSumWithTarget();


    @Test
    void twoSum_basicCase(){
        int[] nums = {2, 3, 4, 1, 3};
        int target = 6;

        List<List<Integer>> result =  twoSumWithTarget.twoSum(nums, target);

        List<List<Integer>> expected = List.of(
                List.of(2, 4),
                List.of(3, 3)
        );

        assertEquals(result, expected);

    }

    @Test
    void twoSum_duplicatesHandledOnce() {
        int[] nums = {3, 3, 3, 3};
        int target = 6;

        List<List<Integer>> result =
                twoSumWithTarget.twoSum(nums, target);

        assertEquals(
                List.of(List.of(3, 3)),
                result
        );
    }


}
