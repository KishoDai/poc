import org.junit.Assert;
import org.junit.Test;

/**
 * 测试值传递与引用传递
 */
public class ReferenceTransmitTest {

    private class Foo {
        public int a;
    }

    /**
     * 值传递。
     */
    @Test
    public void testIntTransmit() {
        // int a = 100 反编译后对应如下指令:
        // 0: bipush        100      //将100压入操作数栈
        // 2: istore_1               //将操作数栈中的数据存入该方法栈桢的局部变量表下标为1的变量中
        int a = 100;

        System.out.println("before testIntTransmit() a = " + a);

        // 参考addOne方法的相应注释
        addOne(a);

        // 反编译后对应指令如下：
        // 48: iload_1              //将局部变量表中下标为1的变量压入操作数栈
        // 49: invokevirtual #7     // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        // 52: invokevirtual #8     // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        // 55: invokevirtual #9     // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        System.out.println("after testIntTransmit() a = " + a);

        Assert.assertEquals(100, a);

        // 分析：
        // testIntTransmit()与addOne()方法有各自的方法栈桢
        // 调用addOne()方法传递的入参a的值为100，且该值copy到addOne()的方法栈桢中
        // 变量a值在testIntTransmit()与addOne()的方法栈桢中互不影响
    }

    /**
     * 引用传递。但是由于Integer不可变的特性，测试结果同值传递。
     */
    @Test
    public void testIntegerTransmit() {
        // Integer a = 100 反编译后对应如下指令:
        // 0: bipush        100      // 将100压入操作数栈
        // 2: invokestatic  #15      // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        // 5: astore_1               // 将操作数栈中的数据存入该方法栈桢的局部变量表下标为1的变量中
        // 分析：这里做了一次装箱操作，变量a为指向堆内存的引用。也就是说该方法栈桢的局部变量表下标为1存储的是一个引用值
        Integer a = 100;

        System.out.println("before testIntTransmit() a = " + a);

        // 参考addOne方法的相应注释
        addOne(a);

        // 51: aload_1
        // 52: invokevirtual #16                 // Method java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        // 55: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        // 58: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        System.out.println("after testIntTransmit() a = " + a);

        Assert.assertEquals(100, a.intValue());

        // 分析：
        // 由于Integer对象的不可变性(没有提供任何可以修改内部属性的途径)，
        // 在调用addOne方法时，不会也不可能改变a指向内存中的对象实例的值
    }

    /**
     * 引用传递。但是由于String不可变的特性，测试结果同值传递。
     */
    @Test
    public void testStringTransmit() {
        // String a = "Hello" 反编译后对应的指令。
        // 0: ldc           #19    // String Hello  从常量池中加载常量数据到操作数据栈中
        // 2: astore_1             // 将操作数据栈中的数据存储该方法栈桢的局部变量表下标为1中
        // 此处可以看出"Hello"这个值存在于运行时常量池中中
        String a = "Hello";

        System.out.println("before testStringTransmit() a = " + a);

        // 参考append()方法的注释
        append(a);

        System.out.println("after testStringTransmit() a = " + a);

        Assert.assertEquals("Hello", a);

        // 分析：
        // 由于String对象的不可变性(没有提供任何可以修改内部属性的途径)，
        // 在调用append方法时，不会也不可能改变a指向内存中的对象实例的值
    }

    /**
     * 引用传递。
     */
    @Test
    public void testObjectTransmit() {
        // Foo foo = new Foo() 反编译后对应的指令如下：
        // 0: new           #24    // class ReferenceTransmitTest$Foo  申请一块Foo实例对象内存,此时所有字段默认为0，并将其引用值压入栈顶
        // 3: dup                  // 复制栈顶数据值并将复制值压入栈顶，供后续JVM调用构造方法用
        // 4: aload_0
        // 5: aconst_null
        // 6: invokespecial #25    // Method ReferenceTransmitTest$Foo."<init>":(LReferenceTransmitTest;LReferenceTransmitTest$1;)V 包括：初始化值、构造语句块、构造方法
        // 9: astore_1             // 将操作数据栈中的数据存储到该方法栈桢的局部变量表下标为1中
        Foo foo = new Foo();

        System.out.println("before testObjectTransmit() foo.a = " + foo.a);

        // 参考addOne方法的注释
        addOne(foo);

        System.out.println("after testObjectTransmit() foo.a = " + foo.a);

        Assert.assertEquals(1, foo.a);
    }

    public void addOne(int a) {
        //JVM调用该方法时，会将参数a放在该方法栈桢的局部变量表下标为1的位置

        System.out.println("before addOne() a = " + a);

        //++a 反编译后得到如下指令，它的意思是将该方法栈桢的局部变量表下标为1的值加1
        //25: iinc          1, 1
        ++a;

        System.out.println("after addOne() a = " + a);
    }

    public void addOne(Integer a) {
        //JVM调用该方法时，会将参数a放在该方法栈桢的局部变量表下标为1的位置
        System.out.println("before addOne() a = " + a);

        // ++a 反编译后得到如下指令
        // 25: aload_1             //将该方法栈桢的局部变量表下标为1的值加载到操作数栈是
        // 26: invokevirtual #18   // Method java/lang/Integer.intValue:()I  //这里实际上做了个拆箱操作，获取a的int值并且压入操作数据栈
        // 29: iconst_1            // 将常量1压入操作数据栈
        // 30: iadd                // 将操作数栈中的两个操作数相加，把结果压入操作数栈
        // 31: invokestatic  #15   // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;  //对操作数栈中的数据做了一次装箱操作，并且把返回结果(是一个引用地址)压入操作数栈
        // 34: astore_1            // 将操作数栈中的值存储到该方法栈桢的局部变量表下标为1中
        ++a;
        // 上述指令等同于如下代码
        // int b = a.intValue();
        // int b = b + 1;
        // a = Integer.valueOf(b);
        System.out.println("after addOne() a = " + a);

        //分析:
        // 由于Integer的不可变性(没有提供任何可以修改内部属性的途径)，
        // 编译器通过拆箱之后获取int值，然后对int值做了加1的操作，
        // 最后通过装箱，生成了一个新的Integer对象，同时重新修改引用类型a的值指向新的Integer对象的内存地址。
    }

    public void append(String a) {
        //JVM调用该方法时，会将参数a放在该方法栈桢的局部变量表下标为1的位置
        System.out.println("before append() a = " + a);

        // a += " World!" 反编译后得到如下指令
        // 25: new           #3    // class java/lang/StringBuilder
        // 28: dup
        // 29: invokespecial #4    // Method java/lang/StringBuilder."<init>":()V
        // 32: aload_1
        // 33: invokevirtual #6    // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        // 36: ldc           #33   // String  World!
        // 38: invokevirtual #6    // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        // 41: invokevirtual #8    // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        // 44: astore_1
        a += " World!";
        // 上述指令等同于如下代码：
        // StringBuilder sb = new StringBuilder();
        // sb.append(a);
        // sb.append(" World");
        // a = sb.toString();

        System.out.println("after append() a = " + a);
        //分析:
        // 由于String的不可变性(没有提供任何可以修改内部属性的途径)，
        // 编译器对String的连接操作，通过生成StringBuilder来完成的
        // 最后通过StringBuilder的toString()方法生成了一个新的字符串对象，同时重新修改引用类型a的值指向新的String对象的内存地址。。
    }

    public void addOne(Foo foo) {
        //JVM调用该方法时，会将参数foo放在该方法栈桢的局部变量表下标为1的位置
        System.out.println("before addOne() foo.a = " + foo.a);

        // foo.a++ 反编译后对应的指令如下：
        // 28: aload_1              // 将该方法栈桢的局部变量表下标为1的值加载到操作数据栈
        // 29: dup                  // 复制栈顶数据值并将复制值压入栈顶
        // 30: getfield      #27    // Field ReferenceTransmitTest$Foo.a:I  获取对象属性a的值并且压入操作数栈
        // 33: iconst_1             // 将常量1压入操作数栈
        // 34: iadd                 // 将操作数栈中数据相加然后把结果压入操作数栈
        // 35: putfield      #27    // Field ReferenceTransmitTest$Foo.a:I  将操作数栈的值写入对象属性a中
        foo.a++;

        System.out.println("after addOne() foo.a = " + foo.a);
    }

}
