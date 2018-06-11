package model;

public enum ClassConstantTagEnum {
    CONSTANT_Utf8(1, ClassConstantStringInfo.class),//UTF-8编码的unicode字符串
    CONSTANT_Integer(3, ClassConstantNumberInfo.class),//int类型的字面量
    CONSTANT_Float(4, ClassConstantNumberInfo.class),//float类型的字面量
    CONSTANT_Long(5, ClassConstantNumberInfo.class),//long类型的字面量
    CONSTANT_Double(6, ClassConstantNumberInfo.class),//double类型的字面量
    CONSTANT_Class(7, ClassConstantNumberInfo.class),//对一个类或者接口的符号引用
    CONSTANT_String(8, ClassConstantNumberInfo.class),//String类型字面值的引用
    CONSTANT_Fieldref(9, ClassConstantReferenceInfo.class),//对一个字段的符号引用
    CONSTANT_Methodref(10, ClassConstantReferenceInfo.class),//对一个类中方法的符号引用
    CONSTANT_InterfaceMethodref(11, ClassConstantReferenceInfo.class),//对一个类中方法的符号引用
    CONSTANT_NameAndType(12, ClassConstantReferenceInfo.class),//对一个字段或者方法的部分符号引用
    ;

    private int tag;
    private Class<? extends ClassConstantInfo> constantInfoClass;

    ClassConstantTagEnum(int tag, Class<? extends ClassConstantInfo> constantInfoClass) {
        this.tag = tag;
        this.constantInfoClass = constantInfoClass;
    }

    public int getTag() {
        return tag;
    }

    public Class<? extends ClassConstantInfo> getConstantInfoClass() {
        return constantInfoClass;
    }

    public static ClassConstantTagEnum getClassConstantTagEnum(int tag) {
        for (ClassConstantTagEnum e : ClassConstantTagEnum.values()) {
            if (e.getTag() == tag) {
                return e;
            }
        }
        return null;
    }

}
