package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MostCommonWordTest {

    private final MostCommonWord mostCommonWord = new MostCommonWord();

    @Test
    public void testMostCommonWord_baseCase() {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};

        String res = mostCommonWord.mostCommonWord(paragraph, banned);

        assertEquals("ball", res);
    }
}
