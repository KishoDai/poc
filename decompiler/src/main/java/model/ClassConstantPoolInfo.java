package model;

public class ClassConstantPoolInfo {

    private int constantPoolCount;
    private ClassConstantInfo[] classConstantInfos;


    public ClassConstantPoolInfo() {

    }

    public ClassConstantPoolInfo(int constantPoolCount, ClassConstantInfo[] classConstantInfos) {
        this.constantPoolCount = constantPoolCount;
        this.classConstantInfos = classConstantInfos;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public ClassConstantInfo[] getClassConstantInfos() {
        return classConstantInfos;
    }

    public void setClassConstantInfos(ClassConstantInfo[] classConstantInfos) {
        this.classConstantInfos = classConstantInfos;
    }
}
