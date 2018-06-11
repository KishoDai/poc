package model;

public class ClassConstantStringInfo extends ClassConstantInfo {

    private short stringIndex;

    public ClassConstantStringInfo() {
    }

    public ClassConstantStringInfo(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum, short stringIndex) {
        super(constantPoolIndex, classConstantTagEnum);
        this.stringIndex = stringIndex;
    }

    public short getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(short stringIndex) {
        this.stringIndex = stringIndex;
    }
}
