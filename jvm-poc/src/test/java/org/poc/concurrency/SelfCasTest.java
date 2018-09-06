package org.poc.concurrency;


public class SelfCasTest {

    // setup to use Unsafe.compareAndSwapInt for updates
    private static long valueOffset = UnsafeUtil.objectFieldOffset(SelfCasTest.class, "value");

    private volatile int value;

    public SelfCasTest(int initialValue) {
        value = initialValue;
    }


    public static void main(String[] args) throws InstantiationException {
        SelfCasTest test = new SelfCasTest(1);
        System.out.println(UnsafeUtil.getUnsafe().getAndAddInt(test, valueOffset, 1) + 1);
        System.out.println(UnsafeUtil.getUnsafe().getAndAddInt(test, valueOffset, 1) + 1);
        System.out.println(UnsafeUtil.getUnsafe().getAndAddInt(test, valueOffset, 1) + 1);
        System.out.println(UnsafeUtil.getUnsafe().getAndAddInt(test, valueOffset, 10) + 1);
        System.out.println(test.value);
        UnsafeUtil.getUnsafe().ensureClassInitialized(SelfCasTest.class);
        System.out.println(UnsafeUtil.getUnsafe().shouldBeInitialized(SelfCasTest.class));
        UnsafeUtil.getUnsafe().loadFence();

        SelfCasTest test2 = (SelfCasTest) UnsafeUtil.getUnsafe().allocateInstance(SelfCasTest.class);
        System.out.println(test2);
        System.out.println(test2.value);
    }
}
