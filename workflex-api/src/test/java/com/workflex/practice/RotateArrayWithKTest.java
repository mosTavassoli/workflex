package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RotateArrayWithKTest {
    private final RotateArrayWithK rotateArrayWithK = new RotateArrayWithK();

    @Test
    void rotateBasicCase(){
        int [] nums = {-1,-100,3,99};
        rotateArrayWithK.rotate(nums, 2);

        assertArrayEquals(new int[]{3,99,-1,-100}, nums);
    }

    @Test
    void rotateWithNewArr(){
        int [] nums = {1,2,3,4,5,6,7};
        rotateArrayWithK.rotate(nums, 3);

        assertArrayEquals(new int[]{5,6,7,1,2,3,4}, nums);
    }

    @Test
    void rotateWithIfArrIsEmpty(){
        int [] nums = {};
        rotateArrayWithK.rotate(nums, 3);

        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    void rotate_ifArrayIsNull_doesNotThrow() {
        assertDoesNotThrow(() -> rotateArrayWithK.rotate(null, 3));
    }

//    @Test
//    void rotate_ifArrayIsNull_throwsException() {
//        assertThrows(NullPointerException.class,
//                () -> rotateArrayWithK.rotate(null, 3));
//    }

    @Test
    void rotate_kIsZero_noChange() {
        int[] nums = {1, 2, 3};

        rotateArrayWithK.rotate(nums, 0);

        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void rotate_kEqualsLength_noChange() {
        int[] nums = {1, 2, 3};

        rotateArrayWithK.rotate(nums, 3);

        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void rotate_kGreaterThanLength_behavesCorrectly() {
        int[] nums = {1, 2, 3};

        rotateArrayWithK.rotate(nums, 5);

        assertArrayEquals(new int[]{2, 3, 1}, nums);
    }

    @Test
    void rotate_inPlace_sameReference() {
        int[] nums = {1, 2, 3};
        int identityHash = System.identityHashCode(nums);

        rotateArrayWithK.rotate(nums, 1);

        assertEquals(identityHash, System.identityHashCode(nums));
    }
}
