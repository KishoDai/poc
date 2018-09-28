package org.poc.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class MinStackTest {

    @Test
    public void test1() {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(3);
        minStack.push(4);
        minStack.push(1);
        minStack.push(1);
        minStack.push(1);

        Assert.assertTrue(minStack.getMin() == 1);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == 1);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == 1);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == 2);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == 2);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == 2);
        minStack.pop();
        Assert.assertTrue(minStack.getMin() == null);
        Assert.assertTrue(minStack.pop() == null);
    }

}
