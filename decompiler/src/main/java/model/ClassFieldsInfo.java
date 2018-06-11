package model;

public class ClassFieldsInfo {
    private int fieldsCount;
    private ClassFieldInfo[] fieldInfos;

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public ClassFieldInfo[] getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(ClassFieldInfo[] fieldInfos) {
        this.fieldInfos = fieldInfos;
    }
}
