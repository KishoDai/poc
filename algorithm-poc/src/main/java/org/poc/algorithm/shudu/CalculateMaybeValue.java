package org.poc.algorithm.shudu;

import java.util.List;

final class CalculateMaybeValue {
    static final boolean calculate(List<Integer>[][] model) {
        boolean success = false;
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                List<Integer> currentCell = model[row][column];
                if (currentCell.size() == 1) {
                    continue;
                }

                int[] rowAndColumnNumbers = new int[model.length];
                boolean changed = false;
                for (int otherRow = 0; otherRow < model.length; otherRow++) {
                    List<Integer> otherCell = model[otherRow][column];
                    if (row == otherRow) {
                        continue;
                    }
                    if (otherCell.size() == 1) {
                        int otherCellVal = otherCell.get(0);
                        rowAndColumnNumbers[otherCellVal - 1] = otherCellVal;
                        changed = true;
                    }
                }
                for (int otherColumn = 0; otherColumn < model[row].length; otherColumn++) {
                    List<Integer> otherCell = model[row][otherColumn];
                    if (column == otherColumn) {
                        continue;
                    }
                    if (otherCell.size() == 1) {
                        int otherCellVal = otherCell.get(0);
                        rowAndColumnNumbers[otherCellVal - 1] = otherCellVal;
                        changed = true;
                    }
                }

                if (changed) {
                    model[row][column].clear();
                }
                for (int index = 0; index < rowAndColumnNumbers.length; index++) {
                    int number = rowAndColumnNumbers[index];
                    if (number == 0) {
                        model[row][column].add(index + 1);
                        success = true;
                    }
                }
            }
        }
        return success;
    }

}