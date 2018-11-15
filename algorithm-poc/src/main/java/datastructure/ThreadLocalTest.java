package datastructure;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "kisho";
            }
        };
        System.out.println(tl.get());
        tl.set("kisho2");
        System.out.println(tl.get());
        tl.remove();
        System.out.println(tl.get());
    }
}
