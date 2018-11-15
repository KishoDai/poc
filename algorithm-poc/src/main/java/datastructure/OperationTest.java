package datastructure;

public class OperationTest {

    public static void main(String[] args) {
        // 取反运算
        System.out.println("~5 = " + ~5);
        System.out.println("~-5 = " + ~-5);

        // 与运算
        System.out.println("5 & 1 = " + (5 & 1));
        // 或运算
        System.out.println("5 | 1 = " + (5 | 2));
        // 异或运算
        System.out.println("5 ^ 1 = " + (5 ^ 1));
        // 左移运算
        System.out.println("-5 << 1 = " + (-5 << 1));
        // 右移运算
        System.out.println("-5 >> 1 = " + (-5 >> 1));
        System.out.println("5 >> 1 = " + (5 >> 1));
        // 无符号右移运算
        System.out.println("-5 >>> 1 = " + (-5 >>> 1));
    }
}
