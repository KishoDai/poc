package org.poc.alibaba.development.specification.study;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpecTest1_5_1 {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((SpecTest1_5_1)obj).getName());
    }

    @Override
    public String toString() {
        return super.toString() + "-SpecTest1_5_1{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Map<SpecTest1_5_1, String> map = new HashMap<SpecTest1_5_1, String>();

        SpecTest1_5_1 key = new SpecTest1_5_1();
        key.setName("kisho1");
        key.setId("1");
        map.put(key, "1");

        SpecTest1_5_1 key2 = new SpecTest1_5_1();
        key2.setName("kisho1");
        key2.setId("2");
        map.put(key2, "2");
        System.out.println(map.get(key2));

        Set<SpecTest1_5_1> set = new HashSet<SpecTest1_5_1>();
        SpecTest1_5_1 key11 = new SpecTest1_5_1();
        key11.setName("kisho1");
        key11.setId("1");
        set.add(key11);

        SpecTest1_5_1 key22 = new SpecTest1_5_1();
        key22.setName("kisho1");
        key22.setId("12");
        set.add(key22);
        System.out.println(set);
    }
}
