package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsPalindromeTest {

    private final IsPalindrome isPalindrome = new IsPalindrome();

    @Test
    void isPalindrome_basicCase() {
        String s = "A man, a plan, a canal: Panama";

        boolean res = isPalindrome.isPalindrome(s);
        assertTrue(res);
    }

    @Test
    void isNotPalindrome() {
        String s = "race a caar";

        boolean res = isPalindrome.isPalindrome(s);
        assertFalse(res);
    }


    @Test
    void alphanumericPalindrome() {
        assertTrue(isPalindrome.isPalindrome("A1b2B1a"));
    }

    @Test
    void numericPalindrome() {
        assertTrue(isPalindrome.isPalindrome("12321"));
    }

    @Test
    void onlySpecialCharacters_isPalindrome() {
        assertTrue(isPalindrome.isPalindrome("!!!,,,   "));
    }


}
