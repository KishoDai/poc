package org.poc;

public class OverloadTest {
    public static void main(String[] args) {
        ChildrenFoo foo11 = new ChildrenFoo();
        ChildrenFoo foo22 = new ChildrenFoo();
        ChildrenFoo foo = new ChildrenFoo();
        foo.test("hi");
    }
}

class SuperFoo {
    public void test(String s) {
        System.out.println("super1->" + s);
    }
}

class ChildrenFoo extends SuperFoo {

    public void test(Object s) {
        System.out.println("children12->" + s);
    }

//    public void test(String s) {
//        System.out.println("children1->" + s);
//    }

}
