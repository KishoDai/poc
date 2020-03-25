package org.poc.algorithm.shudu;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
final class GuessMaybeValue {
    static final boolean guess(ModelLink ml) {
        while (true) {
            if (checkFinish(ml.getModel())) {
                return true;
            }

            if (!checkLegal(ml.getModel())) {
                while (true) {
                    ModelLink last = ml.getLast();
                    if (last == null) {
                        break;
                    } else {
                        ml = last;
                    }
                }
            }
            boolean goon = false;
            ml.setLast(ml.copy());
            List<Integer>[][] model = ml.getModel();
            CalculateMaybeValue.calculate(model);
            for (int row = ml.getRow(); row < model.length; row++) {
                for (int column = ml.getColumn(); column < model[row].length; column++) {
                    List<Integer> cell = model[row][column];
                    if (cell.size() == 1) {
                        continue;
                    }
                    goon = true;
                    ml.setRow(row);
                    ml.setColumn(column);
                    for (int cellValueIndex = ml.getGuessCellValueIndex(); cellValueIndex < cell.size(); cellValueIndex++) {
                        ml.setGuessCellValueIndex(cellValueIndex);
                        ml.setGuessCellValue(cell.get(cellValueIndex));

                        ml.getModel()[row][column].clear();
                        ml.getModel()[row][column].add(ml.getGuessCellValue());
                        boolean success2 = CalculateMaybeValue.calculate(ml.getModel());
                        if (!success2) {
                            continue;
                        }
                        if (!checkLegal(ml.getModel())) {
                            continue;
                        }
                        success2 = CalculateRealValueByMaybeValue.calculate(ml.getModel());
                        if (!success2) {
                            continue;
                        }
                        if (!checkLegal(ml.getModel())) {
                            continue;
                        }
                    }
                }
            }
            if (goon) {
                if (!checkLegal(ml.getModel())) {
                    ml = ml.getLast();
                }
                return guess(ml);
            } else {
                return true;
            }
        }
    }

    private static final boolean checkFinish(List<Integer>[][] model) {
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                if (model[row][column].size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static final boolean checkLegal(List<Integer>[][] model) {
        for (int row = 0; row < model.length; row++) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int column = 0; column < model[row].length; column++) {
                List<Integer> cell = model[row][column];
                if (cell.size() == 1) {
                    if (map.containsKey(cell.get(0))) {
                        return false;
                    } else {
                        map.put(cell.get(0), 1);
                    }
                }
            }
        }
        return true;
    }

}