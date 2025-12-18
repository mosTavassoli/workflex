package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayPairSumTest {

    private final ArrayPairSum arrayPairSum = new ArrayPairSum();

    @Test
    void arrayPairSum_baseCase() {
        int[] array = {6,2,6,5,1,2};
        int res = arrayPairSum.arrayPairSum(array);

        assertEquals(9 , res);
    }
}
