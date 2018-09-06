package org.poc.concurrency;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class UnsafeUtil {

    private static final Unsafe UNSAFE;

    static {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static long objectFieldOffset(Class klass, String fieldName) {
        try {
            return UnsafeUtil.getUnsafe().objectFieldOffset
                    (klass.getDeclaredField(fieldName));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

}
