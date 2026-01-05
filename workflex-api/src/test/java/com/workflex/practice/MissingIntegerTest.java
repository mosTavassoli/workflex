package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MissingIntegerTest {

    MissingInteger missingInteger = new MissingInteger();

    @Test
    void MissingInteger_basic_case() {
        int[] test = {1, 3, 6, 4, 1, 2};

        int res = missingInteger.missingInteger(test);

        assertEquals(5, res);
    }

    @Test
    void MissingInteger_basic_case_1() {
        int[] test = {1, 2, 3};

        int res = missingInteger.missingInteger(test);

        assertEquals(4, res);
    }

    @Test
    void MissingInteger_basic_case_2() {
        int[] test = {-1, -3};

        int res = missingInteger.missingInteger(test);

        assertEquals(1, res);
    }

    @Test
    void MissingInteger_basic_case_4() {
        int[] test = {3, 0, 1};

        int res = missingInteger.missingInteger(test);

        assertEquals(2, res);
    }

    @Test
    void MissingInteger_basic_case_3() {
        int res = missingInteger.missingInteger(new int[]{});

        assertEquals(1, res);
    }

    @Test
    void MissingInteger_basic_case_5() {
        int res = missingInteger.missingInteger(null);

        assertEquals(1, res);
    }

    @Test
    void MissingInteger_basic_case_6() {
        int[] test = {0, 1, 2, 2};
        int res = missingInteger.missingInteger(test);

        assertEquals(3, res);
    }


}
