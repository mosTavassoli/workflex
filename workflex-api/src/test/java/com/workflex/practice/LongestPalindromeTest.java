package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestPalindromeTest {

    LongestPalindrome longestPalindrome = new LongestPalindrome();

    @Test
    void LongestPalindrome_basic_case() {
        assertEquals("bab", longestPalindrome.longestPalindrome("babad"));
    }

    @Test
    void LongestPalindrome_basic_case_1() {
        assertEquals("bb", longestPalindrome.longestPalindrome("cbbd"));
    }

    @Test
    void LongestPalindrome_basic_case_2() {
        assertEquals("c", longestPalindrome.longestPalindrome("cd"));
    }

    @Test
    void LongestPalindrome_basic_case_3() {
        assertEquals("c", longestPalindrome.longestPalindrome("c"));
    }

    @Test
    void LongestPalindrome_basic_case_4() {
        assertEquals("", longestPalindrome.longestPalindrome(""));
    }

    @Test
    void LongestPalindrome_basic_case_5() {
        assertEquals("AAAAAAA", longestPalindrome.longestPalindrome("AAAAAAA"));
    }
}
