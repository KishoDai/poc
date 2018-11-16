package cn.xiangme.fintech.core.capitalmgmt;

public class Context<T> {

    private String type;
    private String state;
    private T data;

    private boolean done = false;

    public Context() {

    }

    public Context(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public Context(String type, String state, T data) {
        this.type = type;
        this.state = state;
        this.data = data;
    }

    public void finish() {
        done = true;
    }

    public boolean done() {
        return done;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Context{" +
                "type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }
}
