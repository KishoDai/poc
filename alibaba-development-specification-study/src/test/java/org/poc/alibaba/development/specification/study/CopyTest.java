package org.poc.alibaba.development.specification.study;

import java.util.ArrayList;
import java.util.List;

public class CopyTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Foo a = new Foo();
        a.setName("张三");
        List<String> hobies = new ArrayList<String>();
        hobies.add("旅游");
        a.setHobies(hobies);

        Foo b = (Foo) a.clone();
        b.setName("张三");
        b.getHobies().add("看书");
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}

class Foo implements Cloneable {
    private String name;
    private List<String> hobies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobies() {
        return hobies;
    }

    public void setHobies(List<String> hobies) {
        this.hobies = hobies;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + "-Foo{" +
                "name='" + name + '\'' +
                ", hobies=" + hobies +
                '}';
    }
}
