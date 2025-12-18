package com.workflex.practice;

public class CountVowels {
    public int count(String str) {
        int countVowels = 0;
        if(str == null || str.isEmpty())
            return countVowels;

        for(char c : str.toCharArray()){
            if("aouieAOUIE".indexOf(c) != -1){
                countVowels++;
            }
        }

        return countVowels;
    }

}
