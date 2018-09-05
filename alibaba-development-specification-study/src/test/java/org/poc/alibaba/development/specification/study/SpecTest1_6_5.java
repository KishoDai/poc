package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class SpecTest1_6_5 {

    private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testSimpleDateFormatIsNotThreadSafe() throws ExecutionException, InterruptedException {
        int threadCount = 10;
        //创建threadCount个不同的日期
        Set<Date> dateSet = new TreeSet<Date>();
        long time = 1536138700515L;
        for (int i = 0; i < threadCount; i++) {
            dateSet.add(new Date(time + i * 5000L));
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<String>> futureList = new ArrayList();
        for (final Date date : dateSet) {
            futureList.add(executor.submit(new Callable<String>() {

                public String call() {
                    return format(date);
                }
            }));
        }

        Set<String> formattedDateStrSet = new TreeSet<String>();
        for (Future<String> future : futureList) {
            formattedDateStrSet.add(future.get());
        }

        System.out.println("dateSet = " + dateSet);
        System.out.println("formattedDateStrSet = " + formattedDateStrSet);

        Assert.assertFalse(dateSet.size() == formattedDateStrSet.size());
        executor.shutdown();
    }

    private String format(Date date) {
        return SDF.format(date);
    }
}

