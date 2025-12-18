package com.workflex.practice;

public class IsPalindrome {
    public boolean isPalindrome(String s) {
        s = s.trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "");

        int L = 0;
        int R = s.length() - 1 ;

        while(L < R){
            if(s.charAt(L) != s.charAt(R)){
                return false;
            }

            L++;
            R--;
        }

        return true;
    }
}