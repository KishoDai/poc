package org.poc.algorithm.shudu;

import java.util.List;

final class Log {

    static void log(List<Integer>[][] model) {
        for (int row = 0; row < model.length; row++) {
            for (int column = 0; column < model[row].length; column++) {
                System.out.print(model[row][column]);
                System.out.print(",");
            }
            System.out.println();
        }
    }

}
