package com.workflex.practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringPermutationTest {

    StringPermutation stringPermutation = new StringPermutation();

    @Test
    void stringPermutation_basic_case() {
        String input = "abc";

        List<String> res = stringPermutation
                .stringPermutation(input);

        List<String> expected =
                List.of("abc", "acb", "bac", "bca", "cba", "cab");


        assertEquals(expected, res);
    }

    @Test
    void stringPermutation_basic_case_1() {


        List<String> res = stringPermutation
                .stringPermutation(null);

        List<String> expected =
                List.of();


        assertEquals(expected, res);
    }

    @Test
    void stringPermutation_empty_string() {
        List<String> res = stringPermutation.stringPermutation("");

        List<String> expected = List.of("");

        assertEquals(expected, res);
    }

    @Test
    void stringPermutation_single_character() {
        List<String> res = stringPermutation.stringPermutation("a");

        List<String> expected = List.of("a");

        assertEquals(expected, res);
    }


    @Test
    void stringPermutation_two_characters() {
        List<String> res = stringPermutation.stringPermutation("ab");

        List<String> expected = List.of("ab", "ba");

        assertEquals(expected, res);
    }

    @Test
    void stringPermutation_with_duplicates() {
        List<String> res = stringPermutation.stringPermutation("aab");

        List<String> expected = List.of(
                "aab",
                "aba",
                "baa"
        );

        assertEquals(expected, res);
    }

    @Test
    void stringPermutation_result_is_independent_of_internal_state() {
        List<String> res1 = stringPermutation.stringPermutation("abc");
        List<String> res2 = stringPermutation.stringPermutation("abc");

        assertEquals(res1, res2);
    }

    @Test
    void stringPermutation_order_independent_check() {
        List<String> res = stringPermutation.stringPermutation("abc");

        assertEquals(6, res.size());
        assertTrue(res.containsAll(
                List.of("abc", "acb", "bac", "bca", "cba", "cab")
        ));
    }

}
