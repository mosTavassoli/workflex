package com.workflex.practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesTest {

    private final RemoveDuplicates removeDuplicates = new RemoveDuplicates();

    @Test
    void removeDuplicates_basicCase() {
        int[] nums = {1, 1, 2};

        int k = removeDuplicates.removeDuplicates(nums);

        assertEquals(2, k);
        assertArrayEquals(new int[]{1, 2}, Arrays.copyOf(nums, k));
    }

    @Test
    void removeDuplicates_allDuplicates() {
        int[] nums = {2, 2, 2, 2};

        int k = removeDuplicates.removeDuplicates(nums);

        assertEquals(1, k);
        assertArrayEquals(new int[]{2}, Arrays.copyOf(nums, k));
    }

    @Test
    void removeDuplicates_alreadyUnique() {
        int[] nums = {1, 2, 3, 4};

        int k = removeDuplicates.removeDuplicates(nums);

        assertEquals(4, k);
        assertArrayEquals(new int[]{1, 2, 3, 4}, Arrays.copyOf(nums, k));
    }

    @Test
    void removeDuplicates_singleElement() {
        int[] nums = {5};

        int k = removeDuplicates.removeDuplicates(nums);

        assertEquals(1, k);
        assertArrayEquals(new int[]{5}, Arrays.copyOf(nums, k));
    }

    @Test
    void removeDuplicates_emptyArray() {
        int[] nums = {};

        int k = removeDuplicates.removeDuplicates(nums);

        assertEquals(0, k);
    }
}




//package com.workflex.practice.removeDuplicates;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RemoveDuplicatesTest {
//
//    private final Solution  removeDuplicates =
//            new Solution();
//
//    @Test
//    void removeDuplicatesSuccessfully(){
//        int[] nums = new int[]{1,1,2};
//        int[] expected = new int[]{1,2};
//
//        int result = removeDuplicates.removeDuplicates(nums);
//
//        assertNotEquals(0, result);
//        assertEquals(2, result);
//    }
//
//    @Test
//    void returnZeroIfArrayIsEmpty(){
//        int[] nums = new int[]{};
//        int result = removeDuplicates.removeDuplicates(nums);
//
//        assertEquals(0, result);
//    }
//
//}
