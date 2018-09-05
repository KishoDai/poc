package org.poc.alibaba.development.specification.study;

import org.junit.Test;

import java.util.*;

public class SpecTest1_5_8 {

    @Test
    public void testComparator() {
        List<Student> studentList = new ArrayList<Student>();

        Student student1 = new Student();
        student1.setId(1);

        Student student2 = new Student();
        student2.setId(2);

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student2);
        studentList.add(student1);

        Collections.sort(studentList, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        });

        System.out.println("studentList = " + studentList);

        Student[] studentArr = new Student[studentList.size()];
        Arrays.sort(studentList.toArray(studentArr), new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        });
    }

}

class Student {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}

