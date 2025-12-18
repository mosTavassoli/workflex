package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwapArrayInPlaceTest {

    private final SwapArrayInPlace swapArrayInPlace = new SwapArrayInPlace();

    @Test
    void swapArrayInPlace_basicCase() {
        int[] nums = {5,8,1,4};

        swapArrayInPlace.swapArrayInPlace(nums);

        int[] expected = {4,1,8,5};

        assertArrayEquals(expected, nums);

    }

    @Test
    void swapArrayInPlace_ifArrayIsEmpty() {
        int[] nums = {};
        swapArrayInPlace.swapArrayInPlace(nums);
        assertArrayEquals(new int[]{}, nums);
    }
}
