package org.poc.jvm.load;

import com.sun.imageio.plugins.common.LZWCompressor;

public class LoadTest {
    private LoadTest() {
    }

    private static class LazyHolder {
        public static final String S = "hi";
        public static final Integer I = 1;
        public static final int I2 = 1;

        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static void main(String[] args) {
        System.out.println(LazyHolder.I2);
        System.out.println(LazyHolder.S);
        System.out.println(LazyHolder.I);
    }

}