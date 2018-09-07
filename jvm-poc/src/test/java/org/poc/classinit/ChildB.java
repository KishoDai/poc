package org.poc.classinit;

public class ChildB extends ParentA {

    private static int I = getI();

    static {
        System.out.println("ChildB static {}");
    }

    public static int getI() {
        System.out.println("ChildB getI()");
        return 1;
    }

    {
        System.out.println("ChildB building block before constructor");
    }

    public ChildB() {
        System.out.println("ChildB constructor");
    }

    {
        System.out.println("ChildB building block  after constructor");
    }

}
