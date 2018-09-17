package org.poc.concurrency;

import java.util.Vector;

public class LockCoarseningTest {

    public static Vector<String> getSchoolNames() {
        Vector<String> vector = new Vector();
        vector.add("北京大学");
        vector.add("清华大学");
        vector.add("交通大学");
        return vector;
    }

    public static void main(String[] args) {
        System.out.println(getSchoolNames());
    }

}
