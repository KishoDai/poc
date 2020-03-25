package org.poc.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Three4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeds = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, mergeds, 0, nums1.length);
        System.arraycopy(nums2, 0, mergeds, nums1.length, nums2.length);
        Arrays.sort(mergeds);
        if (mergeds.length % 2 == 0) {
            int temp = mergeds.length / 2;
            return (mergeds[temp - 1] + mergeds[temp]) / 2.0;
        } else {
            return mergeds[mergeds.length / 2];
        }
    }

    public static void main(String[] args) {
        Three4 solution = new Three4();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
        System.out.println(solution.findMedianSortedArrays(new int[]{4, 5, 6}, new int[]{1, 2, 3}));
        System.out.println(solution.findMedianSortedArrays(new int[]{4, 5, 6}, new int[]{4, 5, 6}));
        System.out.println(solution.findMedianSortedArrays(new int[]{4, 5, 6}, new int[]{5, 5, 5}));
    }
}
