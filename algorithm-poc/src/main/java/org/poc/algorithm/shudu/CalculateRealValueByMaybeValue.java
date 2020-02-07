package org.poc.algorithm.shudu;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CalculateRealValueByMaybeValue {

    static final boolean calculate(List<Integer>[][] model) {
        boolean success = false;
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                List<Integer> currentCell = model[row][column];
                if (currentCell.size() == 1) {
                    continue;
                }

                Map<Integer, Integer> containsValueMap = new ConcurrentHashMap<Integer, Integer>();
                for (int otherRow = 0; otherRow < model.length; otherRow++) {
                    if (row == otherRow) {
                        continue;
                    }
                    List<Integer> otherCell = model[otherRow][column];
                    if (otherCell.size() == 1) {
                        continue;
                    }
                    for (Integer cellVal : currentCell) {
                        Integer containsCount = containsValueMap.get(cellVal);
                        if (containsCount == null) {
                            containsCount = 0;
                        }
                        if (otherCell.contains(cellVal)) {
                            containsCount++;
                        }
                        containsValueMap.put(cellVal, containsCount);
                    }
                }

                for (Map.Entry<Integer, Integer> entry : containsValueMap.entrySet()) {
                    if (entry.getValue() > 0) {
                        containsValueMap.remove(entry.getKey());
                    }
                }
                if (containsValueMap.size() == 1) {
                    model[row][column].clear();
                    model[row][column].add(containsValueMap.keySet().iterator().next());
                    System.out.println("by row:row=>" + row + ",column=>" + column + ",containsValueMap=>" + containsValueMap);
                    success = true;
                    continue;
                }

                containsValueMap.clear();

                for (int otherColumn = 0; otherColumn < model[row].length; otherColumn++) {
                    if (column == otherColumn) {
                        continue;
                    }
                    List<Integer> otherCell = model[row][otherColumn];
                    if (otherCell.size() == 1) {
                        continue;
                    }
                    for (Integer cellVal : currentCell) {
                        Integer containsCount = containsValueMap.get(cellVal);
                        if (containsCount == null) {
                            containsCount = 0;
                        }
                        if (otherCell.contains(cellVal)) {
                            containsCount++;
                        }
                        containsValueMap.put(cellVal, containsCount);
                    }
                }

                for (Map.Entry<Integer, Integer> entry : containsValueMap.entrySet()) {
                    if (entry.getValue() > 0) {
                        containsValueMap.remove(entry.getKey());
                    }
                }
                if (containsValueMap.size() == 1) {
                    model[row][column].clear();
                    model[row][column].add(containsValueMap.keySet().iterator().next());
                    System.out.println("by column:row=>" + row + ",column=>" + column + ",containsValueMap=>" + containsValueMap);
                    success = true;
                }
            }
        }
        return success;
    }

}
