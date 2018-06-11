public class ClassAccessFlags {
    private boolean accPublic;//public类型 所有类型
    private boolean accFinal;//final类型 类
    private boolean accSuper;//使用新的invokespecial语义 类和接口
    private boolean accInterface;//接口类型	接口
    private boolean accAbstract;//抽象类型	类和接口
    private boolean accSynthetic;//该类不由用户代码生成	所有类型
    private boolean accAnnotation;//注解类型	注解
    private boolean accEnum;//枚举类型	枚举

    public boolean isAccPublic() {
        return accPublic;
    }

    public void setAccPublic(boolean accPublic) {
        this.accPublic = accPublic;
    }

    public boolean isAccFinal() {
        return accFinal;
    }

    public void setAccFinal(boolean accFinal) {
        this.accFinal = accFinal;
    }

    public boolean isAccSuper() {
        return accSuper;
    }

    public void setAccSuper(boolean accSuper) {
        this.accSuper = accSuper;
    }

    public boolean isAccInterface() {
        return accInterface;
    }

    public void setAccInterface(boolean accInterface) {
        this.accInterface = accInterface;
    }

    public boolean isAccAbstract() {
        return accAbstract;
    }

    public void setAccAbstract(boolean accAbstract) {
        this.accAbstract = accAbstract;
    }

    public boolean isAccSynthetic() {
        return accSynthetic;
    }

    public void setAccSynthetic(boolean accSynthetic) {
        this.accSynthetic = accSynthetic;
    }

    public boolean isAccAnnotation() {
        return accAnnotation;
    }

    public void setAccAnnotation(boolean accAnnotation) {
        this.accAnnotation = accAnnotation;
    }

    public boolean isAccEnum() {
        return accEnum;
    }

    public void setAccEnum(boolean accEnum) {
        this.accEnum = accEnum;
    }
}
