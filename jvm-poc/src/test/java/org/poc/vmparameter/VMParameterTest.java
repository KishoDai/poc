package org.poc.vmparameter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VMParameterTest {
    // 内存相关
    //-Xms10m -Xmx10m   Xms默认为物理内存的1/64 Xmx默认为物理内存的1/4
    //-Xss10m
    //-Xmn10m    大小 = eden + 2 survivor space 建议为整个堆的3/8
    //-XX:NewSize
    //-XX:MaxNewSize
    //-XX:NewRatio
    //-XX:SurvivorRatio
    //-XX:PermSize
    //-XX:MaxPermSize

    //GC相关
    //-XX:+PrintGC                        打印GC回收信息
    //-XX:+PrintGCDetails                 打印GC回收详细信息
    //-XX:+PrintGCTimeStamps              打印GC回收时间信息
    //-XX:+PrintGCApplicationStoppedTime  打印GC回收时应用停止时间
    //-XX:+PrintHeapAtGC                  打印GC回收前和回收后的堆使用情况
    //-Xnoclassgc                         禁用垃圾回收
    //-Xloggc:                            设置垃圾回收日志文件
    //-XX:HeapDumpOnOutOfMemoryError      OOM时，dump出Heap文件
    //-XX:HeapDumpPath                    OOM时，heap dump文件的目录
    //-XX:+DisableExplicitGC              禁用显示的调用GC
    //-XX:MaxTenuringThreshold            设置垃圾最大年龄
    //-XX:+UseBiasedLocking               使用偏向锁
    //-XX:PretenureSizeThreshold          设置对象超过多大时直接在老年代分配


    //监控相关
    //-XX:+TraceClassLoading
    //-XX:+PrintClassHistogram


    /**
     * -Xmx10m -Xmn2m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime
     */
    @Test
    public void testVMParameter() {
        int count = 1000000;
        List<Integer> list = new ArrayList<>(count);
        while (--count >= 0) {
            list.add(count);
        }
        System.out.println("xxx");
    }

    @Test
    public void testVmParameter2() {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }

}
