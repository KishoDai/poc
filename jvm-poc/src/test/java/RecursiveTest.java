public class RecursiveTest {

    public static void main(String[] args) {
        System.out.println(recursive(1));
        System.out.println(recursive(3));
        System.out.println(recursive(4));
        System.out.println(recursive(20));
    }

    public static long recursive(int i) {
        return i == 0 ? 1 : i * recursive(i - 1);
    }
}
