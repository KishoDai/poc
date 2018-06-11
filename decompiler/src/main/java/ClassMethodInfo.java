public class ClassMethodInfo {


    private int methodIndex;

    private boolean accPublic;
    private boolean accPrivate;
    private boolean accProtected;
    private boolean accStatic;
    private boolean accFinal;
    private boolean accSynchronized;
    private boolean accNative;
    private boolean accAbstract;
    private boolean accStrict;

    private int nameIndex;
    private int descriptorIndex;

    private ClassAttributesInfo attributesInfo;


    public int getMethodIndex() {
        return methodIndex;
    }

    public void setMethodIndex(int methodIndex) {
        this.methodIndex = methodIndex;
    }

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

    public boolean isAccSynchronized() {
        return accSynchronized;
    }

    public void setAccSynchronized(boolean accSynchronized) {
        this.accSynchronized = accSynchronized;
    }

    public boolean isAccNative() {
        return accNative;
    }

    public void setAccNative(boolean accNative) {
        this.accNative = accNative;
    }

    public boolean isAccAbstract() {
        return accAbstract;
    }

    public void setAccAbstract(boolean accAbstract) {
        this.accAbstract = accAbstract;
    }

    public boolean isAccStrict() {
        return accStrict;
    }

    public void setAccStrict(boolean accStrict) {
        this.accStrict = accStrict;
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

    public ClassAttributesInfo getAttributesInfo() {
        return attributesInfo;
    }

    public void setAttributesInfo(ClassAttributesInfo attributeInfos) {
        this.attributesInfo = attributeInfos;
    }
}
