package com.workflex.practice;

import java.util.Arrays;

public class MissingInteger {
    public int missingInteger(int[] A) {
        int result  = 1;

        if (A == null || A.length == 0) return result;

        Arrays.sort(A);

        for(int num : A){
            if(num == result){
                result++;
            }
        }

        return result;
    }
}
