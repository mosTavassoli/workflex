package com.workflex.practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindDuplicatesTest {
    private final FindDuplicates findDuplicates = new FindDuplicates();

    @Test
    void basiCase (){
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> res = findDuplicates.findDuplicates(nums);

        assertEquals(2 , res.size());
        assertTrue(res.containsAll(List.of(3,2)));
    }

    @Test
    void basiCase_2 (){
        int[] nums = {1,1,2};
        List<Integer> expected = List.of(1);

        assertEquals(expected, findDuplicates.findDuplicates(nums));
    }

    @Test
    void basiCase_3 (){
        int[] nums = {1,1,1};
        List<Integer> expected = List.of(1);

        assertEquals(expected, findDuplicates.findDuplicates(nums));
    }

    @Test
    void findDuplicates_noDuplicates() {
        int[] nums = {1,2,3,4};

        List<Integer> res = findDuplicates.findDuplicates(nums);

        assertTrue(res.isEmpty());
    }

    @Test
    void findDuplicates_nullInput_returnsEmptyList() {
        List<Integer> result = findDuplicates.findDuplicates(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findDuplicates_emptyArrInput_returnsEmptyList() {
        List<Integer> result = findDuplicates.findDuplicates(new int[]{});
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
