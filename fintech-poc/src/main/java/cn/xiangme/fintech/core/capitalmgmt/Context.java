package cn.xiangme.fintech.core.capitalmgmt;

public class Context<T> {

    private String capitalNo;
    private String applyNo;
    private String state;
    private T data;

    public Context() {

    }

    public Context(String capitalNo, String applyNo, String state, T data) {
        this.capitalNo = capitalNo;
        this.applyNo = applyNo;
        this.state = state;
        this.data = data;
    }

    public String getCapitalNo() {
        return capitalNo;
    }

    public void setCapitalNo(String capitalNo) {
        this.capitalNo = capitalNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
