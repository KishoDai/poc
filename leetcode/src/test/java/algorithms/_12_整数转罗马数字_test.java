package algorithms;

import org.junit.Assert;
import org.junit.Test;

public class _12_整数转罗马数字_test {

    @Test
    public void test() {
        _12_整数转罗马数字 test = new _12_整数转罗马数字();
        Assert.assertEquals("III", test.intToRoman(3));
        Assert.assertEquals("IV", test.intToRoman(4));
        Assert.assertEquals("IX", test.intToRoman(9));
        Assert.assertEquals("LVIII", test.intToRoman(58));
        Assert.assertEquals("MCMXCIV", test.intToRoman(1994));
    }
}
