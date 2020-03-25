package org.poc.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Three3 {
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int length = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int j = 0;//[j,i]
        for (int i = 0; i < length; i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            longest = Math.max(longest, i - j + 1);
            map.put(s.charAt(i), i);
        }
        return longest;
    }

    public static void main(String[] args) {
        Three3 solution = new Three3();
        System.out.println(solution.lengthOfLongestSubstring("dvdf"));
    }
}
