package org.poc.example;

public class ParentA {

    private static int I = getI();

    static {
        System.out.println("ParentA static {}");
    }

    private static int getI() {
        System.out.println("ParentA getI()");
        return 1;
    }

    {
        System.out.println("ParentA building block before constructor");
    }

    public ParentA() {
        System.out.println("ParentA constructor");
    }

    {
        System.out.println("ParentA building block  after constructor");
    }
}
