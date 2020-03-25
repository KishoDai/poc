package org.poc.algorithm.shudu;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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
        List<Integer>[][] newModel = ModelUtil.toNew(model);
        int count = 1;
        while (true) {
            log.info("第{}次处理!", count++);
            boolean success = CalculateMaybeValue.calculate(newModel);
            log.info("after calculateMaybeValueListForEveryCell = ");
            ModelUtil.log(newModel);

            if (!success) {
                System.out.println("finish!");
                break;
            }

            success = CalculateRealValueByMaybeValue.calculate(newModel);
            log.info("after setNewCellValue = ");
            ModelUtil.log(newModel);
            if (success) {
                continue;
            }
            log.info("暂无法计算出，下一步进行猜测!");
            ModelLink modelLink = new ModelLink();
            modelLink.setModel(newModel);
            modelLink.setRow(0);
            modelLink.setColumn(0);
            modelLink.setGuessCellValueIndex(0);
            modelLink.setGuessCellValue(newModel[0][0].get(0));
            success = GuessMaybeValue.guess(modelLink);
            if (success) {
                newModel = modelLink.getModel();

                continue;
            } else {
                log.info("无法算出！！！");
                ModelUtil.log(modelLink.getModel());
                break;
            }
        }
    }

}
