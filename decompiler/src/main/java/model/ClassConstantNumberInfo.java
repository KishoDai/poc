package model;

public class ClassConstantNumberInfo extends ClassConstantInfo {

    private Number value;

    public ClassConstantNumberInfo() {

    }

    public ClassConstantNumberInfo(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum, Number value) {
        super(constantPoolIndex, classConstantTagEnum);
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
