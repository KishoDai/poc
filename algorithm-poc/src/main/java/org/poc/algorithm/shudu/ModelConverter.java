package org.poc.algorithm.shudu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


final class ModelConverter {
    static final List<Integer>[][] to(int[][] model) {
        List<Integer>[][] newModel = new List[model.length][model[0].length];
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                int cell = model[row][column];
                if (cell == 0) {
                    newModel[row][column] = new ArrayList();
                } else {
                    newModel[row][column] = Arrays.asList(cell);
                }
            }
        }
        return newModel;
    }
}