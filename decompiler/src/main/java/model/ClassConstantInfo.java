package model;

public class ClassConstantInfo {

    private int constantPoolIndex;
    private ClassConstantTagEnum classConstantTagEnum;

    public ClassConstantInfo() {
    }

    public ClassConstantInfo(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum) {
        this.constantPoolIndex = constantPoolIndex;
        this.classConstantTagEnum = classConstantTagEnum;
    }

    public int getConstantPoolIndex() {
        return constantPoolIndex;
    }

    public void setConstantPoolIndex(int constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }

    public ClassConstantTagEnum getClassConstantTagEnum() {
        return classConstantTagEnum;
    }

    public void setClassConstantTagEnum(ClassConstantTagEnum classConstantTagEnum) {
        this.classConstantTagEnum = classConstantTagEnum;
    }
}
