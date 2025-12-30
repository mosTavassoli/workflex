package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthOfLongestSubstringTest {

    LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();


    @Test
    void LengthOfLongestSubstring_basic_case(){

        assertEquals(3,
        lengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew"));


    }

}
