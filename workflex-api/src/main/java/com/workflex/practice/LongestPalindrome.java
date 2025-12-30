package com.workflex.practice;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String res = "";

        for(int i = 0; i < s.length(); i++){
            String str1 = expandCounterAndCount(s, i, i);
            String str2 = expandCounterAndCount(s, i, i + 1);

            if(str1.length() > res.length() ){
                res = str1;
            }

            if(str2.length() > res.length() ){
                res = str2;
            }
        }

        return res;

    }

    private String expandCounterAndCount(String s, int l, int r){
        int len = s.length();

        while(l>=0 && r<len && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }

        return s.substring(l+1 ,r);
    }
}
