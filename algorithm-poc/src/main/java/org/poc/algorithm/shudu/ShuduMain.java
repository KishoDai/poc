package org.poc.algorithm.shudu;

import java.util.List;

public class ShuduMain {

    public static void main(String[] args) {
        int[][] model = new int[][]{
                {0, 5, 0, 8, 0, 1, 0, 9, 0},
                {7, 3, 0, 0, 0, 0, 0, 5, 4},
                {8, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 8, 3, 0, 2, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 6, 7, 0, 4, 5, 0, 0},
                {1, 0, 0, 0, 5, 0, 0, 0, 9},
                {3, 8, 0, 0, 0, 0, 0, 1, 2},
                {0, 4, 0, 6, 0, 8, 0, 3, 0}
        };
        List<Integer>[][] newModel = ModelConverter.to(model);
        int count = 1;
        while (true) {
            System.out.println("第" + count++ + "次处理!");
            boolean success = CalculateMaybeValue.calculate(newModel);
            System.out.println("after calculateMaybeValueListForEveryCell = ");
            Log.log(newModel);

            if (!success) {
                System.out.println("finish!");
                break;
            }

            success = CalculateRealValueByMaybeValue.calculate(newModel);
            System.out.println("after setNewCellValue = ");
            Log.log(newModel);
            if (!success) {
                System.out.println("暂无法计算出!");
                break;
            }
        }
    }

}
