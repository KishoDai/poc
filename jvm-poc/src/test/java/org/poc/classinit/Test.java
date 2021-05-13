package org.poc.classinit;

public class Test {

    static {
        i = 12;
    }
    static int i = 1;
    //-verbose:class -XX:+TraceClassLoading -Xlog:class+load=info
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
//        new ChildB();
//        System.out.println(ChildB.class.getDeclaredMethod("getI", null));

//        System.out.println(InterfaceChildB.F2);
//        System.out.println(InterfaceChildB.class);
//        System.out.println(InterfaceChildB.F2);
        InterfaceChildB[] bs = new InterfaceChildB[1];
    }
}
