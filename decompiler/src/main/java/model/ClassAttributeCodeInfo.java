package model;

public class ClassAttributeCodeInfo extends ClassAttributeInfo {


    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private String code;
    private int exceptionTableLength;
    private String exceptionTable;

    private ClassAttributesInfo attributesInfo;

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public void setExceptionTableLength(int exceptionTableLength) {
        this.exceptionTableLength = exceptionTableLength;
    }

    public String getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(String exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public ClassAttributesInfo getAttributesInfo() {
        return attributesInfo;
    }

    public void setAttributesInfo(ClassAttributesInfo attributesInfo) {
        this.attributesInfo = attributesInfo;
    }
}
