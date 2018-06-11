package model;

public class ClassConstantUTF8Info extends ClassConstantInfo {

    private String value;

    public ClassConstantUTF8Info() {
    }

    public ClassConstantUTF8Info(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum, String value) {
        super(constantPoolIndex, classConstantTagEnum);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
