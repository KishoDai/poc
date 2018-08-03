package org.poc;

import java.util.Arrays;
import java.util.List;

public class SyntacticSugarTest {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        for (int i : intList) {
            System.out.println("i=" + i);
        }


        for (int index = 0; index < intList.size(); index++) {
            System.out.println("i=" + intList.get(index));
        }
    }

}
