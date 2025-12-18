package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountVowelsTest {

    private final CountVowels countVowels = new CountVowels();

    @Test
    void countVowels_uppercaseWord() {
        assertEquals(3, countVowels.count("MOSTAFA"));
    }

    @Test
    void countVowels_lowercaseWord() {
        assertEquals(3, countVowels.count("mostafa"));
    }

    @Test
    void countVowels_mixedCaseWord() {
        assertEquals(3, countVowels.count("MoStAfA"));
    }

    @Test
    void countVowels_emptyString_returnsZero() {
        assertEquals(0, countVowels.count(""));
    }

    @Test
    void countVowels_noVowels_returnsZero() {
        assertEquals(0, countVowels.count("bcdfg"));
    }

    @Test
    void countVowels_onlyVowels() {
        assertEquals(5, countVowels.count("aeiou"));
    }

    @Test
    void countVowels_nullInput_returnsZero() {
        assertEquals(0, countVowels.count(null));
    }
}

