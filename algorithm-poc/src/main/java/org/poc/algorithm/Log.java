package org.poc.algorithm;

public class Log {

    public static void log(int[] arr) {
        System.out.print("[");
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index]);
            if (index < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        log(new int[]{1, 2, 3});
    }
}
