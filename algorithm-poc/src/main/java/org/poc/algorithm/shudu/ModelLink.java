package org.poc.algorithm.shudu;

import lombok.Data;

import java.util.List;

@Data
class ModelLink {

    private List<Integer>[][] model;
    private Integer row;
    private Integer column;
    private Integer guessCellValueIndex;
    private Integer guessCellValue;

    private ModelLink last;

    public ModelLink copy() {
        ModelLink ml = new ModelLink();
        ml.setModel(ModelUtil.copy(model));
        ml.setRow(row);
        ml.setColumn(column);
        ml.setGuessCellValueIndex(guessCellValueIndex);
        ml.setGuessCellValue(guessCellValue);
        ml.setLast(last);
        return ml;
    }

}
