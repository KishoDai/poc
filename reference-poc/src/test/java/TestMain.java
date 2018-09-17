public class TestMain {
    private interface A {

    }

    public static void main(String[] args) {

        System.out.println(int[].class instanceof Object);
        Class c = int[].class;
        System.out.println(c.isArray());
        System.out.println(A.class instanceof Object);
        System.out.println(A.class.isInterface());
        System.out.println(A.class.isLocalClass());
        System.out.println(new Object().getClass());

    }
}
