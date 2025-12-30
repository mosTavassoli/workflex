package com.workflex.practice;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;

        for(int end = 0; end < s.length(); end++){
            char cur = s.charAt(end);

            while(seen.contains(cur)){
                seen.remove(s.charAt(start));
                start++;
            }

            seen.add(cur);
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}
