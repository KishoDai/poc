public class Foo {

    public static final Long A = 1L;
    public static final String S = "hello1";
    public static final String S1 = new String("hello2");
    public static final String S2 = getS2();

    private static String getS2() {
        return "hello2";
    }

    public static int B = 2;

    static {
        System.out.println("Foo static!");
    }


}
