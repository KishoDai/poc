package cn.memedai.poc.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kisho on 2017/12/8.
 */
public class FooServiceTest {

    @Test
    public void test1() {
        FooService fooService = new FooService();
        Assert.assertTrue(2 == fooService.add(1, 1));
    }
}
