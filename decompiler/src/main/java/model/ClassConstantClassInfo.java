package model;

public class ClassConstantClassInfo extends ClassConstantInfo {

    private short nameIndex;

    public ClassConstantClassInfo() {
    }

    public ClassConstantClassInfo(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum, short nameIndex) {
        super(constantPoolIndex, classConstantTagEnum);
        this.nameIndex = nameIndex;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }
}
