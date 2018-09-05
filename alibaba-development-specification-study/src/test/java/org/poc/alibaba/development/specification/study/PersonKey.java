package org.poc.alibaba.development.specification.study;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PersonKey {
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
        return name.equals(((PersonKey)obj).getName());
    }

    @Override
    public String toString() {
        return super.toString() + "-PersonKey{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Map<PersonKey, String> map = new HashMap<PersonKey, String>();

        PersonKey key = new PersonKey();
        key.setName("kisho1");
        key.setId("1");
        map.put(key, "1");

        PersonKey key2 = new PersonKey();
        key2.setName("kisho1");
        key2.setId("2");
        map.put(key2, "2");
        System.out.println(map.get(key2));

        Set<PersonKey> set = new HashSet<PersonKey>();
        PersonKey key11 = new PersonKey();
        key11.setName("kisho1");
        key11.setId("1");
        set.add(key11);

        PersonKey key22 = new PersonKey();
        key22.setName("kisho1");
        key22.setId("12");
        set.add(key22);
        System.out.println(set);
    }
}
