package org.poc.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (int index = 0; index < nums.length; index++) {
            int otherValue = target - nums[index];
            if (numMap.containsKey(otherValue)) {
                return new int[]{numMap.get(otherValue), index};
            }
            numMap.put(nums[index], index);
        }
        return null;
    }


    public static void main(String[] args) {
        TwoSum1 solution = new TwoSum1();
        System.out.println(solution.twoSum(new int[]{3, 2, 4}, 6));
    }
}
