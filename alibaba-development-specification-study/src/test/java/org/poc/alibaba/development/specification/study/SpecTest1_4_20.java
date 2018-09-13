package org.poc.alibaba.development.specification.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpecTest1_4_20 {

    @Test
    public void testShallowCopy() throws CloneNotSupportedException {
        Student stu = new Student();
        stu.setName("张三");
        List<String> hobbies = new ArrayList<String>();
        hobbies.add("旅游");
        stu.setHobbies(hobbies);
        stu.setFriend(new Student());

        Student cloneStu = (Student) stu.clone();
        cloneStu.getHobbies().add("看书");

        Assert.assertTrue(stu != cloneStu);
        Assert.assertTrue(stu.getFriend() == cloneStu.getFriend());
        Assert.assertEquals(stu.getHobbies(), cloneStu.getHobbies());
        Assert.assertEquals(stu.getName(), cloneStu.getName());
        Assert.assertEquals(2, stu.getHobbies().size());
        Assert.assertEquals(2, cloneStu.getHobbies().size());
    }


    class Student implements Cloneable {

        private String name;
        private List<String> hobbies;
        private Student friend;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<String> hobbies) {
            this.hobbies = hobbies;
        }

        public Student getFriend() {
            return friend;
        }

        public void setFriend(Student friend) {
            this.friend = friend;
        }

        @Override
        public String toString() {
            return super.toString() + "-Student{" +
                    "name='" + name + '\'' +
                    ", hobbies=" + hobbies +
                    '}';
        }
    }

}
