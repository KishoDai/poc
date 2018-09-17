package org.poc.classinit;

public class Test {
    //-verbose:class -XX:+TraceClassLoading -Xlog:class+load=info
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
//        new ChildB();
//        System.out.println(ChildB.class.getDeclaredMethod("getI", null));

//        System.out.println(InterfaceChildB.F2);
        System.out.println(InterfaceParentA.class);
    }
}
