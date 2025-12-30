package com.workflex.practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class SubsetsTest {

    private final Subsets subsets = new Subsets();

    @Test
    void subsets_happy_path() {
        int[] nums = {1, 2, 3};

        List<List<Integer>> expected = List.of(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(1, 2),
                List.of(3),
                List.of(1, 3),
                List.of(2, 3),
                List.of(1, 2, 3)
        );

        assertThat(subsets.subsets(nums))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void subsets_whenArrayIsNull_returnsEmptySubset() {
        assertEquals(List.of(List.of()), subsets.subsets(null));
    }

    @Test
    void subsets_whenArrayIsEmpty_returnsEmptySubset() {
        assertEquals(List.of(List.of()), subsets.subsets(new int[]{}));
    }

    @Test
    void subsets_singleElement() {
        assertThat(subsets.subsets(new int[]{42}))
                .containsExactlyInAnyOrder(
                        List.of(),
                        List.of(42)
                );
    }

    @Test
    void subsets_sizeIsPowerOfTwo() {
        assertEquals(16, subsets.subsets(new int[]{1, 2, 3, 4}).size());
    }
}

