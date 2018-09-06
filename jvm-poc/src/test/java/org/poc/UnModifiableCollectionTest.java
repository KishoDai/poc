package org.poc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnModifiableCollectionTest {

    public static void main(String[] args) {
        List<String> studentList = new ArrayList<>();
        studentList.add("kisho");
        studentList.add("jack");
        List<String> newStudentList = Collections.unmodifiableList(studentList);
        System.out.println(newStudentList);
        studentList.add("peter");
        System.out.println(newStudentList);
    }
}
