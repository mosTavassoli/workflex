package com.workflex.practice;

import java.util.Arrays;

public class MissingInteger {
    public int missingInteger(int[] A) {

        if (A == null || A.length == 0) return 1;
        int len = A.length;

        boolean[] seen = new boolean[len + 1];

        for(int a : A){
            if(a > 0 && a <= len && !seen[a]){
                seen[a] = true;
            }
        }

        for(int i = 1; i < len; i++){
            if(!seen[i]) return i;
        }

        return len + 1;


//        int result  = 1;
//
//        if (A == null || A.length == 0) return result;
//
//        Arrays.sort(A);
//
//        for(int num : A){
//            if(num == result){
//                result++;
//            }
//        }
//
//        return result;
    }
}
