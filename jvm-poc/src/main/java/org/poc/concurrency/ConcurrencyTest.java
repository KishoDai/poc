package org.poc.concurrency;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Outcome(id = {"0,0", "0,2", "1,0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1,2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class ConcurrencyTest {
    int a = 0;
    int b = 0;

    @Actor
    public void method1(IntResult2 r) {
        r.r2 = a;
        b = 1;
    }

    @Actor
    public void method2(IntResult2 r) {
        r.r1 = b;
        a = 2;
    }
}
