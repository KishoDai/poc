package org.poc.algorithm.leetcode;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class TwoAdd2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode next = new ListNode(0);
        ListNode root = next;
        int carryVal = 0;
        while (l1 != null || l2 != null || carryVal > 0) {
            int sum = carryVal;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            carryVal = sum / 10;
            next.val = sum % 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (l1 != null || l2 != null || carryVal > 0) {
                next.next = new ListNode(0);
                next = next.next;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        TwoAdd2 solution = new TwoAdd2();
        // 342
        // 465
        // 807
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        ListNode next = solution.addTwoNumbers(l1, l2);
        do {
            System.out.println(next.val);
        } while ((next = next.next) != null);

        System.out.println(solution.addTwoNumbers(l1, l2));
    }
}
