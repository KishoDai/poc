public class ClassFieldInfo {
    private boolean accPublic;
    private boolean accPrivate;
    private boolean accProtected;
    private boolean accStatic;
    private boolean accFinal;
    private boolean accVolatile;
    private boolean accTransient;
    private boolean accSynthetic;
    private boolean accEnum;

    private int nameIndex;
    private int descriptorIndex;

    private short attributesCount;


    public boolean isAccPublic() {
        return accPublic;
    }

    public void setAccPublic(boolean accPublic) {
        this.accPublic = accPublic;
    }

    public boolean isAccPrivate() {
        return accPrivate;
    }

    public void setAccPrivate(boolean accPrivate) {
        this.accPrivate = accPrivate;
    }

    public boolean isAccProtected() {
        return accProtected;
    }

    public void setAccProtected(boolean accProtected) {
        this.accProtected = accProtected;
    }

    public boolean isAccStatic() {
        return accStatic;
    }

    public void setAccStatic(boolean accStatic) {
        this.accStatic = accStatic;
    }

    public boolean isAccFinal() {
        return accFinal;
    }

    public void setAccFinal(boolean accFinal) {
        this.accFinal = accFinal;
    }

    public boolean isAccVolatile() {
        return accVolatile;
    }

    public void setAccVolatile(boolean accVolatile) {
        this.accVolatile = accVolatile;
    }

    public boolean isAccTransient() {
        return accTransient;
    }

    public void setAccTransient(boolean accTransient) {
        this.accTransient = accTransient;
    }

    public boolean isAccSynthetic() {
        return accSynthetic;
    }

    public void setAccSynthetic(boolean accSynthetic) {
        this.accSynthetic = accSynthetic;
    }

    public boolean isAccEnum() {
        return accEnum;
    }

    public void setAccEnum(boolean accEnum) {
        this.accEnum = accEnum;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public short getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(short attributesCount) {
        this.attributesCount = attributesCount;
    }
}
