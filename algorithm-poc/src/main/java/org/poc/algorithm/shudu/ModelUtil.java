package org.poc.algorithm.shudu;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
final class ModelUtil {

    static void log(List<Integer>[][] model) {
        int[] maxLengthArr = new int[model[0].length];
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                List<Integer> cell = model[row][column];
                int length = cell.toString().replaceAll("\\s*", "").length();
                if (length > maxLengthArr[column]) {
                    maxLengthArr[column] = length;
                }
            }
        }

        StringBuilder builder = new StringBuilder("\n");
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                builder.append(String.format("%" + maxLengthArr[column] + "s", model[row][column].toString().replaceAll("\\s*", "")))
                        .append(",");

            }
            builder.append("\n");
        }
        log.info(builder.toString());
    }

    static final List<Integer>[][] copy(List<Integer>[][] model) {
        List<Integer>[][] copyModel = new List[model.length][model[0].length];
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                List<Integer> cell = model[row][column];
                if (cell.size() == 1) {
                    copyModel[row][column] = cell;
                } else {
                    List<Integer> copyCell = new ArrayList<Integer>();
                    copyCell.addAll(cell);
                    copyModel[row][column] = copyCell;
                }
            }
        }
        return copyModel;
    }

    static final List<Integer>[][] toNew(int[][] model) {
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
