public class ClassConstantReferenceInfo extends ClassConstantInfo {

    private short classIndex;
    private short nameAndTypeIndex;

    public ClassConstantReferenceInfo() {
    }

    public ClassConstantReferenceInfo(int constantPoolIndex, ClassConstantTagEnum classConstantTagEnum, short classIndex, short nameAndTypeIndex) {
        super(constantPoolIndex, classConstantTagEnum);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public short getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(short classIndex) {
        this.classIndex = classIndex;
    }

    public short getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(short nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
